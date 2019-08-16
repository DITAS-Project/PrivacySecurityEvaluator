package de.tub.privacySecurityEvaluator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.tub.privacySecurityEvaluator.model.BlueprintRanking;
import de.tub.privacySecurityEvaluator.model.Feature;
import de.tub.privacySecurityEvaluator.util.PropertyDeserializer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTestIssue0028 {

    @Autowired
    private MockMvc mvc;

    private TestHelper helper = new TestHelper();

    private ObjectMapper mapper;

    @Before
    public void prepare() {
        mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Feature.class, new PropertyDeserializer());
        mapper.registerModule(module);

    }


    @BeforeClass
    public static void checkIfFilesPresent() {
        Assert.assertNotNull(ApplicationTests.class.getResourceAsStream("/integration/issue28.json"));
        Assert.assertNotNull(ApplicationTests.class.getResourceAsStream("/integration/issue28b.json"));

    }

    @Test
    public void test() throws Exception{
        String request= helper.readToString("/integration/issue28.json");
        mvc.perform(post("/v1/filter").content(request).contentType("application/json")).andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    BlueprintRanking[] results= mapper.readValue(mvcResult.getResponse().getContentAsString(), BlueprintRanking[].class);
                    Assert.assertEquals(results.length,1);
                });
    }

    @Test
    public void testB() throws Exception{
        String request= helper.readToString("/integration/issue28b.json");
        mvc.perform(post("/v1/filter").content(request).contentType("application/json")).andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    BlueprintRanking[] results= mapper.readValue(mvcResult.getResponse().getContentAsString(), BlueprintRanking[].class);
                    Assert.assertEquals(results.length,1);
                });
    }

}
