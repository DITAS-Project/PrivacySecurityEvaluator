package de.tub.privacySecurityEvaluator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.tub.privacySecurityEvaluator.model.BlueprintRanking;
import de.tub.privacySecurityEvaluator.model.Feature;
import de.tub.privacySecurityEvaluator.model.Request;
import de.tub.privacySecurityEvaluator.model.fields.AlgorithmField;
import de.tub.privacySecurityEvaluator.model.fields.KeylengthField;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richard on 02.05.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTestIssue0010 {

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
        Assert.assertNotNull(ApplicationTests.class.getResourceAsStream("/integration/issue10.json"));
        Assert.assertNotNull(ApplicationTests.class.getResourceAsStream("/integration/issue10Requirement.json"));
    }

    @Test
    public void testPrecondition(){
        try {

            Request request = new Request();

            Feature requirement = new Feature();
            requirement.setId("encryptionAES_192");
            requirement.setType("Encryption");
            requirement.addProperty(new AlgorithmField("enum","AES"),"algorithm");
            requirement.addProperty(new KeylengthField("number",192),"keyLength");
            request.setRequirement(requirement);


            List<Feature> blueprintAttributes = new ArrayList<>();

            Feature en128 = new Feature();
            en128.setId("encryptionAES_128");
            en128.setType("Encryption");
            en128.addProperty(new AlgorithmField("enum","AES"),"algorithm");
            en128.addProperty(new KeylengthField("number",128),"keyLength");
            blueprintAttributes.add(en128);

            Feature en256 = new Feature();
            en256.setId("encryptionAES_256");
            en256.setType("Encryption");
            en256.addProperty(new AlgorithmField("enum","AES"),"algorithm");
            en256.addProperty(new KeylengthField("number",256),"keyLength");
            blueprintAttributes.add(en256);

            request.setBlueprintAttributes(blueprintAttributes);


            testRequest(request);


        } catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testParsingProblem() {
        try {

            Feature requiremenet = mapper.readValue(readToString("/integration/issue10Requirement.json"), Feature.class);

            Assert.assertEquals(2,requiremenet.getProperties().size());


        } catch (Exception e){
            Assert.fail();
        }
    }

    @Test
    public void test() {
        try {

            Request request = mapper.readValue(readToString("/integration/issue10.json"), Request.class);

            testRequest(request);


        } catch (Exception e){
            Assert.fail();
        }
    }

    private void testRequest(Request request) {
        List<BlueprintRanking> blueprintRankingList = this.evaluator.evaluateRequest(request);


        Assert.assertEquals(1, blueprintRankingList.size());

        for (BlueprintRanking result : blueprintRankingList){
            int keyLenght = ((KeylengthField) result.getBlueprint().getProperties().get("keyLength")).getValue();

            Assert.assertTrue(keyLenght > 128);
        }
    }

    private String readToString(String filename) {
        return new TestHelper().readToString(filename);
    }

}
