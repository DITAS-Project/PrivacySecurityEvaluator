package de.tub.privacySecurityEvaluator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.tub.privacySecurityEvaluator.api.PrivacySecurityEvaluator;
import de.tub.privacySecurityEvaluator.model.BlueprintRanking;
import de.tub.privacySecurityEvaluator.model.Request;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    private static String body;
    private static String output1;
    private static String output2;


    private PrivacySecurityEvaluator privacySecurityEvaluator;


    @BeforeClass
    public static void loadJsons() {
        body = readToString("/SimpleMessage.json");
        output1 = readToString("/SimpleResponse1.json");
        output2 = readToString("/SimpleResponse2.json");

    }

    private static String readToString(String filename) {
        final StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ApplicationTests.class.getResourceAsStream(filename)))) {
            reader.lines().forEach(s -> sb.append(s));
        } catch (IOException ex) {
            Assert.fail("could not load json data");
            ex.printStackTrace();
        }
        return sb.toString();
    }

    @Autowired
    public void setPrivacySecurityEvaluator(PrivacySecurityEvaluator privacySecurityEvaluator) {
        this.privacySecurityEvaluator = privacySecurityEvaluator;
    }

    /**
     * @Test public void contextLoads() {
     * String out = body;
     * out = out.replace("\n", "");
     * out = out.replaceAll("\\s+","");
     * System.out.println(out);
     * <p>
     * }
     */
    @Test
    public void inputOutputFilterTest() {
        System.out.println(body);
        ObjectMapper mapper = new ObjectMapper();
        List<BlueprintRanking> blueprintRanking = null;
        try {
            blueprintRanking = privacySecurityEvaluator.filterPolicies(mapper.readValue(body,Request.class));
        } catch (IOException e) {
            fail();
        }
        try {
            String testOutput1 = mapper.writeValueAsString(blueprintRanking.get(0));
            String testOutput2 = mapper.writeValueAsString(blueprintRanking.get(1));
            assertEquals(testOutput1, output1);
            assertEquals(testOutput2, output2);
        } catch (JsonProcessingException e) {
            fail();
        }
    }


}
