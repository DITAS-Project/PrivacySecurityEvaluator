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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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


    private boolean contains(Map<String, Property> list, Class<?> type) {
        for (Property f : list.values()) {
            if (type.isInstance(f)) {
                return true;
            }
        }
        return false;
    }
}
