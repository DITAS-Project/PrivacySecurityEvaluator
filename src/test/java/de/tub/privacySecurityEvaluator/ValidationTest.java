package de.tub.privacySecurityEvaluator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.tub.privacySecurityEvaluator.model.Blueprint;
import de.tub.privacySecurityEvaluator.model.BlueprintRanking;
import de.tub.privacySecurityEvaluator.model.Feature;
import de.tub.privacySecurityEvaluator.model.Request;
import de.tub.privacySecurityEvaluator.service.EvaluatorServiceImpl;
import de.tub.privacySecurityEvaluator.util.PropertyDeserializer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Richard on 02.05.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidationTest {

    EvaluatorServiceImpl evaluator;
    ObjectMapper mapper;


    @Autowired
    public void setEvaluator(EvaluatorServiceImpl evaluator) {
        this.evaluator = evaluator;
    }

    @Before
    public void prepare() {
        mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Feature.class, new PropertyDeserializer());
        mapper.registerModule(module);
    }

    @Test
    public void testCredentials() {
        String message = readToString("/SimpleMessage.json");
        try {
            Request request = mapper.readValue(message, Request.class);
            List<BlueprintRanking> rankings = evaluator.evaluateRequest(request);
            List<Blueprint> valid = Arrays.asList(mapper.readValue(readToString("/validation/SimpleTest.json"), Blueprint[].class));
            for (BlueprintRanking ranking : rankings) {
                Blueprint found = ranking.getBlueprint();
                boolean got = found.equals(valid.get(0));
                // Assert.assertTrue(valid.contains(ranking.getBlueprint()));
            }

            System.out.println("ready");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAlgorithm() {
        try {
            List<Blueprint> start = Arrays.asList(mapper.readValue(readToString("/validation/algorithms.json"), Blueprint[].class));
            Feature requirement = mapper.readValue(readToString("/validation/requirementAlgorithm.json"), Feature.class);
            List<BlueprintRanking> rankings = evaluator.evaluateRequest(new Request(requirement, start));
            List<Blueprint> filteredList = rankings.stream().map(BlueprintRanking::getBlueprint).collect(Collectors.toList());
            Assert.assertTrue(filteredList.contains(start.get(0)));
            Assert.assertTrue(filteredList.contains(start.get(1)));
            Assert.assertTrue(!filteredList.contains(start.get(2)));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testKeylength() {
        try {
            List<Blueprint> start = Arrays.asList(mapper.readValue(readToString("/validation/keylength.json"), Blueprint[].class));
            Feature requirement = mapper.readValue(readToString("/validation/requirementKeylength.json"), Feature.class);
            List<BlueprintRanking> rankings = evaluator.evaluateRequest(new Request(requirement, start));
            List<Blueprint> filteredList = rankings.stream().map(BlueprintRanking::getBlueprint).collect(Collectors.toList());
            Assert.assertTrue(filteredList.contains(start.get(0)));
            Assert.assertTrue(filteredList.contains(start.get(1)));
            Assert.assertTrue(!filteredList.contains(start.get(2)));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Test
    public void testFilter() {
        try {
            List<Blueprint> start = Arrays.asList(mapper.readValue(readToString("/validation/filterTest.json"), Blueprint[].class));
            Feature requirement = mapper.readValue(readToString("/validation/requirementAlgorithm.json"), Feature.class);
            HashSet<Blueprint> filterd = evaluator.filter(requirement, start);
            Assert.assertTrue(filterd.contains(start.get(1)));
            Assert.assertTrue(filterd.size() < start.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readToString(String filename) {
        final StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ApplicationTests.class.getResourceAsStream(filename)))) {
            reader.lines().forEach(s -> sb.append(s));
        } catch (IOException ex) {
            Assert.fail("could not load json data");
            ex.printStackTrace();
        }
        return sb.toString();
    }


}
