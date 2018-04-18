package de.tub.privacySecurityEvaluator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.tub.privacySecurityEvaluator.model.Propertiefield;
import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.fields.ProtocolField;
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
        module.addDeserializer(Property.class, new PropertyDeserializer());
        mapper.registerModule(module);
    }

    @Test
    public void testTransportEncryption() {
        String propertyJson = readToString("/model/transport.json");

        try {
            Property property = mapper.readValue(propertyJson, Property.class);

            Assert.assertEquals(property.getName(), "transport encryption");
            Assert.assertTrue(contains(property.getProperties(), ProtocolField.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private boolean contains(List<Propertiefield> list, Class<?> type) {
        for (Propertiefield f : list) {
            if (type.isInstance(f)) {
                return true;
            }
        }
        return false;
    }
}
