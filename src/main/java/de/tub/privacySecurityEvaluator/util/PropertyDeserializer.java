package de.tub.privacySecurityEvaluator.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import de.tub.privacySecurityEvaluator.model.Feature;
import de.tub.privacySecurityEvaluator.model.Property;
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
            Property property = null;

            String key = s.getKey().toLowerCase();
            switch (key) {
                case "protocol":
                    property = mapper.treeToValue(s.getValue(), ProtocolField.class);
                    break;
                case "version":
                    property = mapper.treeToValue(s.getValue(), VersionField.class);
                    break;
                case "algorithm":
                    property = mapper.treeToValue(s.getValue(), AlgorithmField.class);
                    break;
                case "keylength":
                    property = mapper.treeToValue(s.getValue(), KeylengthField.class);
                    break;
                case "level":
                    property = mapper.treeToValue(s.getValue(), LevelField.class);
                    break;
                case "samplerate":
                    property = mapper.treeToValue(s.getValue(), SamplerateField.class);
                    break;
                case "instrumentation":
                    property = mapper.treeToValue(s.getValue(), InstrumentationField.class);
                    break;
                case "credentials":
                    property = mapper.treeToValue(s.getValue(), CredentialsField.class);
                    break;
                case "announcementaddress":
                    property = mapper.treeToValue(s.getValue(), AnnouncementAddressField.class);
                    break;
                case "required":
                    property = mapper.treeToValue(s.getValue(), RequiredField.class);
                    break;
                case "guarantor":
                    property = mapper.treeToValue(s.getValue(), GuarantorField.class);
                    break;
                case "availablepurpose":
                    property = mapper.treeToValue(s.getValue(),AvailablePurposeField.class);
                    break;
                case "allowedguarantor":
                    property = mapper.treeToValue(s.getValue(),AllowedGuarantorField.class);
                    break;
                case "purpose":
                    System.out.println(s.getValue());
                    property=mapper.treeToValue(s.getValue(), PurposeField.class);
                    break;
            }
            if (property != null){
                result.addProperty(property, key);
            }
        }

        return result;
    }
}
