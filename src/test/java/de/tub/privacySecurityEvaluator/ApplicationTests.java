/*
 * Copyright 2018 Information Systems Engineering, TU Berlin, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *                       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * This is being developed for the DITAS Project: https://www.ditas-project.eu/
 */

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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.validation.constraints.AssertTrue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {

    private final static TestHelper testHelper = new TestHelper();

    private static String body;
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper;


    @BeforeClass
    public static void loadJsons() {
        body = readToString("/newInput.json");


    }

    @Before
    public void prepare() {
        mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Feature.class, new PropertyDeserializer());
        mapper.registerModule(module);

    }


    private static String readToString(String filename) {
        return testHelper.readToString(filename);
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
    public void inputOutputFilterTest() throws Exception {

        ResultActions perform = mockMvc.perform(post("/filter")
                .content(body)
                .contentType(contentType));
        perform.andExpect(status().isOk());

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
    public void rankingTest() throws Exception {

        ResultActions perform = mockMvc.perform(post("/filter")
                .content(body)
                .contentType(contentType));
        perform.andExpect(status().isOk()).andExpect(result ->
        {

            BlueprintRanking[] rankings = mapper.readValue(result.getResponse().getContentAsString(), BlueprintRanking[].class);

            for (BlueprintRanking rank : rankings){
                Assert.assertTrue(rank.getScore() >= 0);
            }
        });

    }
}
