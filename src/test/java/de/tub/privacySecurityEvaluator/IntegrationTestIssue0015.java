package de.tub.privacySecurityEvaluator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
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

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTestIssue0015 {

    private final TestHelper testHelper = new TestHelper();
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
        Assert.assertNotNull(ApplicationTests.class.getResourceAsStream("/integration/issue15.json"));
    }

    @Test
    public void test() {
        try {

            Request request = mapper.readValue(testHelper.readToString("/integration/issue15.json"), Request.class);

            System.out.println(request);


        } catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }



    private String readToString(String filename) {
        return testHelper.readToString(filename);
    }

}
