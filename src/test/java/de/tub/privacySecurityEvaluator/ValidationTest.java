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
import org.junit.BeforeClass;
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

    @BeforeClass
    public static void checkIfFilesPresent() {
        Assert.assertNotNull(ApplicationTests.class.getResourceAsStream("/validation/keylength.json"));
        Assert.assertNotNull(ApplicationTests.class.getResourceAsStream("/validation/requirementKeylength.json"));
        Assert.assertNotNull(ApplicationTests.class.getResourceAsStream("/validation/announcementAddress.json"));
        Assert.assertNotNull(ApplicationTests.class.getResourceAsStream("/validation/requirementAnnouncementAddress.json"));
        Assert.assertNotNull(ApplicationTests.class.getResourceAsStream("/validation/algorithms.json"));
        Assert.assertNotNull(ApplicationTests.class.getResourceAsStream("/validation/requirementAlgorithm.json"));
        Assert.assertNotNull(ApplicationTests.class.getResourceAsStream("/validation/credentials.json"));
        Assert.assertNotNull(ApplicationTests.class.getResourceAsStream("/validation/requirementCredentials.json"));
        Assert.assertNotNull(ApplicationTests.class.getResourceAsStream("/validation/guarantor.json"));
        Assert.assertNotNull(ApplicationTests.class.getResourceAsStream("/validation/requirementGuarantor.json"));
        Assert.assertNotNull(ApplicationTests.class.getResourceAsStream("/validation/instrumentation.json"));
        Assert.assertNotNull(ApplicationTests.class.getResourceAsStream("/validation/requirementInstrumentation.json"));
        Assert.assertNotNull(ApplicationTests.class.getResourceAsStream("/validation/level.json"));
        Assert.assertNotNull(ApplicationTests.class.getResourceAsStream("/validation/requirementLevel.json"));
        Assert.assertNotNull(ApplicationTests.class.getResourceAsStream("/validation/protocol.json"));
        Assert.assertNotNull(ApplicationTests.class.getResourceAsStream("/validation/requirementProtocol.json"));
        Assert.assertNotNull(ApplicationTests.class.getResourceAsStream("/validation/samplerate.json"));
        Assert.assertNotNull(ApplicationTests.class.getResourceAsStream("/validation/requirementSamplerate.json"));
        Assert.assertNotNull(ApplicationTests.class.getResourceAsStream("/validation/version.json"));
        Assert.assertNotNull(ApplicationTests.class.getResourceAsStream("/validation/requirementVersion.json"));

    }


    @Test
    public void testAlgorithm() {
        fieldTest("/validation/algorithms.json", "/validation/requirementAlgorithm.json");
    }

    @Test
    public void testAnnouncementAddress() {
        fieldTest("/validation/announcementAddress.json", "/validation/requirementAnnouncementAddress.json");
    }

    @Test
    public void testCredentials() {
        fieldTest("/validation/credentials.json", "/validation/requirementCredentials.json");
    }

    @Test
    public void testGuarantor() {
        fieldTest("/validation/guarantor.json", "/validation/requirementGuarantor.json");
    }

    @Test
    public void testInstrumentation() {
        fieldTest("/validation/instrumentation.json", "/validation/requirementInstrumentation.json");
    }

    @Test
    public void testKeylength() {
        fieldTest("/validation/keylength.json", "/validation/requirementKeylength.json");
    }

    @Test
    public void testLevel() {
        fieldTest("/validation/level.json", "/validation/requirementLevel.json");
    }

    @Test
    public void testProtocol() {
        fieldTest("/validation/protocol.json", "/validation/requirementProtocol.json");
    }

    @Test
    public void testSamplerate() {
        fieldTest("/validation/samplerate.json", "/validation/requirementSamplerate.json");
    }

    @Test
    public void testVersion() {
        fieldTest("/validation/version.json", "/validation/requirementVersion.json");
    }

    @Test
    public void testFilter() {
        try {
            List<Blueprint> start = Arrays.asList(mapper.readValue(readToString("/validation/filterTest.json"), Blueprint[].class));
            Feature requirement = mapper.readValue(readToString("/validation/requirementAlgorithm.json"), Feature.class);
            HashSet<Blueprint> filterd = evaluator.filter(requirement, start);
            Assert.assertTrue(filterd.contains(start.get(1))); //could cause problems when unordered
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

    private void fieldTest(String inputFile, String requirementFile) {
        try {
            List<Blueprint> start = Arrays.asList(mapper.readValue(readToString(inputFile), Blueprint[].class));
            Feature requirement = mapper.readValue(readToString(requirementFile), Feature.class);
            List<BlueprintRanking> rankings = evaluator.evaluateRequest(new Request(requirement, start));
            List<Blueprint> filteredList = rankings.stream().map(BlueprintRanking::getBlueprint).collect(Collectors.toList());
            Assert.assertTrue(filteredList.contains(start.get(0))); //could cause problems when unordered
            Assert.assertTrue(filteredList.size() < start.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
