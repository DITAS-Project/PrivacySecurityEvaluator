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
import java.util.List;

public class ModelTesting {


    private String readToString(String filename) {
        final StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ApplicationTests.class.getResourceAsStream(filename)))) {
            reader.lines().forEach(s -> sb.append(s));
        } catch (IOException ex) {
            Assert.fail("could not load json data");
            ex.printStackTrace();
        }
        return sb.toString();
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

            Assert.assertEquals(property.getName(), "transport encryption");
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

            Assert.assertEquals(property.getName(), "encryption");
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

            Assert.assertEquals(property.getName(), "tracing");
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

            Assert.assertEquals(property.getName(), "acl");
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

            Assert.assertEquals(property.getName(), "mutation control");
            Assert.assertTrue(contains(property.getProperties(), AnnouncmentAdressField.class));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private boolean contains(List<Property> list, Class<?> type) {
        for (Property f : list) {
            if (type.isInstance(f)) {
                return true;
            }
        }
        return false;
    }
}
