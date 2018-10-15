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
import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.Request;
import de.tub.privacySecurityEvaluator.model.fields.AllowedGuarantorField;
import de.tub.privacySecurityEvaluator.service.EvaluatorServiceImpl;
import de.tub.privacySecurityEvaluator.service.FilterService;
import de.tub.privacySecurityEvaluator.service.FilterServiceImpl;
import de.tub.privacySecurityEvaluator.util.PropertyDeserializer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RankingTest {

    private final TestHelper testHelper = new TestHelper();
    EvaluatorServiceImpl evaluator;
    FilterServiceImpl filterService;
    ObjectMapper mapper;

    @BeforeClass
    public static void checkIfFilesPresent() {
        Assert.assertNotNull(ApplicationTests.class.getResourceAsStream("/ranking/allowedGuarantor.json"));

    }


    @Autowired
    public void setFilterService(FilterServiceImpl filterService){
        this.filterService= filterService;
    }

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
    public void test() {
        try {

            Request request = mapper.readValue(testHelper.readToString("/ranking/allowedGuarantor.json"), Request.class);

            Feature required = request.getRequirement();
            HashSet<Feature> valid = filterService.filter(request);


            List<Feature> allowedGuarantors = new LinkedList<>();
            for (Feature f : valid) {
                AllowedGuarantorField property = (AllowedGuarantorField) f.getProperties().get("allowedGuarantor");

                if (property != null) {
                    double score = 0;
                    for (Property p : required.getProperties().values()) {
                        score += property.rank(p);
                    }
                    Assert.assertEquals(0.5, score, 0);
                }

            }
            List<BlueprintRanking> rankings = evaluator.evaluateRequest(request);


        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
