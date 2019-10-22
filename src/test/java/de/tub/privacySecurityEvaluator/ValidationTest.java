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
import de.tub.privacySecurityEvaluator.model.Request;
import de.tub.privacySecurityEvaluator.service.EvaluatorServiceImpl;
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

import java.io.IOException;
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
    private final TestHelper testHelper = new TestHelper();


    EvaluatorServiceImpl evaluator;


    FilterServiceImpl filterService;
    ObjectMapper mapper;


    @Autowired
    public void setEvaluator(EvaluatorServiceImpl evaluator) {
        this.evaluator = evaluator;
    }

    @Autowired
    public void setFilterService(FilterServiceImpl filterService) {
        this.filterService = filterService;
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
    public void testAllowedGuarantor() {
        fieldTest("/validation/allowedGuarantor.json", "/validation/requirementAllowedGuarantor.json");
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
            List<Feature> start = Arrays.asList(mapper.readValue(readToString("/validation/filterTest.json"), Feature[].class));
            Feature requirement = mapper.readValue(readToString("/validation/requirementAlgorithm.json"), Feature.class);
            HashSet<Feature> filterd = filterService.filter(new Request(requirement, start));
            Assert.assertTrue(filterd.contains(start.get(1))); //could cause problems when unordered
            Assert.assertTrue(filterd.size() < start.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readToString(String filename) {
        return testHelper.readToString(filename);
    }

    private void fieldTest(String inputFile, String requirementFile) {
        try {
            List<Feature> start = Arrays.asList(mapper.readValue(readToString(inputFile), Feature[].class));
            Feature requirement = mapper.readValue(readToString(requirementFile), Feature.class);
            List<BlueprintRanking> rankings = evaluator.evaluateRequest(new Request(requirement, start));
            List<Feature> filteredList = rankings.stream().map(BlueprintRanking::getBlueprint).collect(Collectors.toList());
            Assert.assertTrue(filteredList.contains(start.get(0))); //could cause problems when unordered
            Assert.assertTrue(filteredList.size() < start.size());

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }


}
