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
import de.tub.privacySecurityEvaluator.model.Feature;
import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.fields.*;
import de.tub.privacySecurityEvaluator.util.PropertyDeserializer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class ModelTesting {
    private final TestHelper testHelper = new TestHelper();

    private String readToString(String filename) {
        return testHelper.readToString(filename);
    }


    ObjectMapper mapper;

    @Before
    public void prepare() {
        mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Feature.class, new PropertyDeserializer());
        mapper.registerModule(module);
    }

    @Test
    public void testTransportEncryption() {
        String propertyJson = readToString("/model/transport.json");

        try {
            Feature property = mapper.readValue(propertyJson, Feature.class);

            Assert.assertEquals(property.getDescription(), "transport encryption");
            Assert.assertTrue(contains(property.getProperties(), ProtocolField.class));
            Assert.assertTrue(contains(property.getProperties(), VersionField.class));
            Assert.assertTrue(contains(property.getProperties(), KeylengthField.class));
            Assert.assertTrue(contains(property.getProperties(), AlgorithmField.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testStorageEncryption() {
        String propertyJson = readToString("/model/storage.json");

        try {
            Feature property = mapper.readValue(propertyJson, Feature.class);

            Assert.assertEquals(property.getDescription(), "encryption");
            Assert.assertTrue(contains(property.getProperties(), KeylengthField.class));
            Assert.assertTrue(contains(property.getProperties(), AlgorithmField.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testTracingAndAccessMonitoring() {
        String propertyJson = readToString("/model/tracing.json");

        try {
            Feature property = mapper.readValue(propertyJson, Feature.class);

            Assert.assertEquals(property.getDescription(), "tracing");
            Assert.assertTrue(contains(property.getProperties(), LevelField.class));
            Assert.assertTrue(contains(property.getProperties(), SamplerateField.class));
            Assert.assertTrue(contains(property.getProperties(), InstrumentationField.class));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAccessControl() {
        String propertyJson = readToString("/model/acl.json");

        try {
            Feature property = mapper.readValue(propertyJson, Feature.class);

            Assert.assertEquals(property.getDescription(), "acl");
            Assert.assertTrue(contains(property.getProperties(), Property.class));
            Assert.assertTrue(contains(property.getProperties(), CredentialsField.class));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMutationControl() {
        String propertyJson = readToString("/model/mutationControl.json");

        try {
            Feature property = mapper.readValue(propertyJson, Feature.class);

            Assert.assertEquals(property.getDescription(), "mutation control");
            Assert.assertTrue(contains(property.getProperties(), AnnouncementAddressField.class));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPurpose(){
        String propertyJson = readToString("/model/purpose.json");

        try {
            Feature property = mapper.readValue(propertyJson, Feature.class);

            Assert.assertEquals(property.getDescription(), "purpose");
            Assert.assertTrue(contains(property.getProperties(), PurposeField.class));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private boolean contains(Map<String, Property> list, Class<?> type) {
        for (Property f : list.values()) {
            if (type.isInstance(f)) {
                return true;
            }
        }
        return false;
    }
}
