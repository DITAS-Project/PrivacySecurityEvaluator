package de.tub.privacySecurityEvaluator.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import de.tub.privacySecurityEvaluator.model.Feature;
import de.tub.privacySecurityEvaluator.model.fields.*;

import java.io.IOException;

public class PropertyDeserializer extends StdDeserializer<Feature> {
    private ObjectMapper mapper;

    public PropertyDeserializer() {
        this(null);
    }

    protected PropertyDeserializer(Class<?> vc) {
        super(vc);
        mapper = new ObjectMapper();
    }

    @Override
    public Feature deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec codec = jsonParser.getCodec();
        FakeProperty fakeProperty = codec.readValue(jsonParser, FakeProperty.class);
        Feature result = fakeProperty.toProperty();

        for (JsonNode n : fakeProperty.properties) {
            switch (n.get("name").textValue()) {
                case "protocol":
                    result.addProperty(mapper.readValue(n.toString(), ProtocolField.class));
                    break;
                case "version":
                    result.addProperty(mapper.readValue(n.toString(), VersionField.class));
                    break;
                case "algorithm":
                    result.addProperty(mapper.readValue(n.toString(), AlgorithmField.class));
                    break;
                case "keylength":
                    result.addProperty(mapper.readValue(n.toString(), KeylengthField.class));
                    break;
                case "level":
                    result.addProperty(mapper.readValue(n.toString(), LevelField.class));
                    break;
                case "samplerate":
                    result.addProperty(mapper.readValue(n.toString(), SamplerateField.class));
                    break;
                case "instrumentation":
                    result.addProperty(mapper.readValue(n.toString(), InstrumentationField.class));
                    break;
                case "credentials":
                    result.addProperty(mapper.readValue(n.toString(), CredentialsField.class));
                    break;
                case "announcementAddress":
                    result.addProperty(mapper.readValue(n.toString(), AnnouncementAddressField.class));
                    break;
                case "required":
                    result.addProperty(mapper.readValue(n.toString(), RequiredField.class));
                    break;
                case "guarantor":
                    result.addProperty(mapper.readValue(n.toString(), GuarantorField.class));
                    break;





            }
        }

        return result;
    }
}