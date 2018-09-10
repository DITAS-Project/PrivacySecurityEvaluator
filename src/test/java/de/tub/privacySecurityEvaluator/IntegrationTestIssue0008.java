package de.tub.privacySecurityEvaluator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.tub.privacySecurityEvaluator.model.BlueprintRanking;
import de.tub.privacySecurityEvaluator.model.Feature;
import de.tub.privacySecurityEvaluator.model.Request;
import de.tub.privacySecurityEvaluator.model.fields.AlgorithmField;
import de.tub.privacySecurityEvaluator.model.fields.AvailablePurposeField;
import de.tub.privacySecurityEvaluator.model.fields.KeylengthField;
import de.tub.privacySecurityEvaluator.model.fields.PurposeField;
import de.tub.privacySecurityEvaluator.service.EvaluatorServiceImpl;
import de.tub.privacySecurityEvaluator.util.GraphDeserializer;
import de.tub.privacySecurityEvaluator.util.PropertyDeserializer;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTestIssue0008 {

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
        module.addDeserializer(PurposeField.class, new GraphDeserializer());
        mapper.registerModule(module);

    }

    @BeforeClass
    public static void checkIfFilesPresent() {
        Assert.assertNotNull(ApplicationTests.class.getResourceAsStream("/integration/issue08.json"));
    }

    @Test
    public void testPrecondition() {
        try {

            Request request = new Request();

            Feature requirement = new Feature();
            requirement.setId("purpose requirement");
            requirement.setType("availablePurpose");
            requirement.setDescription("stuff");
            String[] availablePurpose = {"research", "medical research", "nutritional research"};
            requirement.addProperty(new AvailablePurposeField(availablePurpose), "availablePurpose");
            request.setRequirement(requirement);


            List<Feature> blueprintAttributes = new ArrayList<>();

            Feature purpose1 = new Feature();
            purpose1.setId("purpose1");
            purpose1.setDescription("purposeTest1");
            purpose1.setType("graph");
            Graph<String, DefaultEdge> graph1 = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
            graph1.addVertex("nutritional research");
            purpose1.addProperty(new PurposeField(graph1),"purpose");
            blueprintAttributes.add(purpose1);


            Feature purpose2 = new Feature();
            purpose2.setId("purpose2");
            purpose2.setDescription("purposeTest2");
            purpose2.setType("graph");
            Graph<String, DefaultEdge> graph2 = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
            graph2.addVertex("resch");
            purpose2.addProperty(new PurposeField(graph2),"purpose");
            blueprintAttributes.add(purpose2);

            request.setBlueprintAttributes(blueprintAttributes);


            testRequest(request);


        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }



    @Test
    public void test() {
        try {

            Request request = mapper.readValue(readToString("/integration/issue08.json"), Request.class);

            testRequest(request);


        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    private void testRequest(Request request) {
        List<BlueprintRanking> blueprintRankingList = this.evaluator.evaluateRequest(request);


        Assert.assertEquals(1, blueprintRankingList.size());


    }

    private String readToString(String filename) {
        return new TestHelper().readToString(filename);
    }

}