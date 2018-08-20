package de.tub.privacySecurityEvaluator.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import de.tub.privacySecurityEvaluator.model.Feature;
import de.tub.privacySecurityEvaluator.model.fields.*;

import java.io.IOException;
import java.util.Map;

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
    public Feature deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec codec = jsonParser.getCodec();
        FakeProperty fakeProperty = codec.readValue(jsonParser, FakeProperty.class);
        Feature result = fakeProperty.toProperty();
        Map<String, JsonNode> properties = fakeProperty.properties;
        for (Map.Entry<String, JsonNode> s : properties.entrySet()) {
            //pattern matching to determine which impl of property should be used
            switch (s.getKey().toLowerCase()) {
                case "protocol":
                    result.addProperty(mapper.treeToValue(s.getValue(), ProtocolField.class), s.getKey());
                    break;
                case "version":
                    result.addProperty(mapper.treeToValue(s.getValue(), VersionField.class), s.getKey());
                    break;
                case "algorithm":
                    result.addProperty(mapper.treeToValue(s.getValue(), AlgorithmField.class), s.getKey());
                    break;
                case "keylength":
                    result.addProperty(mapper.treeToValue(s.getValue(), KeylengthField.class), s.getKey());
                    break;
                case "level":
                    result.addProperty(mapper.treeToValue(s.getValue(), LevelField.class), s.getKey());
                    break;
                case "samplerate":
                    result.addProperty(mapper.treeToValue(s.getValue(), SamplerateField.class), s.getKey());
                    break;
                case "instrumentation":
                    result.addProperty(mapper.treeToValue(s.getValue(), InstrumentationField.class), s.getKey());
                    break;
                case "credentials":
                    result.addProperty(mapper.treeToValue(s.getValue(), CredentialsField.class), s.getKey());
                    break;
                case "announcementaddress":
                    result.addProperty(mapper.treeToValue(s.getValue(), AnnouncementAddressField.class), s.getKey());
                    break;
                case "required":
                    result.addProperty(mapper.treeToValue(s.getValue(), RequiredField.class), s.getKey());
                    break;
                case "guarantor":
                    result.addProperty(mapper.treeToValue(s.getValue(), GuarantorField.class), s.getKey());
                    break;
                case "availablepurpose":
                    result.addProperty(mapper.treeToValue(s.getValue(),AvailablePurposeField.class),s.getKey());
                    break;
                case "allowedguarantor":
                    result.addProperty(mapper.treeToValue(s.getValue(),AllowedGuarantorField.class),s.getKey());
                    break;
            }
        }

        return result;
    }
}
