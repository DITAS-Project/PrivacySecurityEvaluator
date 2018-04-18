package de.tub.privacySecurityEvaluator.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.fields.ProtocolField;

import java.io.IOException;

public class PropertyDeserializer extends StdDeserializer<Property> {
    private ObjectMapper mapper;

    public PropertyDeserializer() {
        this(null);
    }

    protected PropertyDeserializer(Class<?> vc) {
        super(vc);
        mapper = new ObjectMapper();
    }

    @Override
    public Property deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec codec = jsonParser.getCodec();
        FakeProperty fakeProperty = codec.readValue(jsonParser, FakeProperty.class);
        Property result = fakeProperty.toProperty();

        for (JsonNode n : fakeProperty.properties) {
            switch (n.get("name").textValue()) {
                case "Protocol":
                    result.addProperty(mapper.readValue(n.toString(), ProtocolField.class));
            }
        }

        return result;
    }
}
