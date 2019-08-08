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
import de.tub.privacySecurityEvaluator.model.fields.datautilityfields.*;

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
    public Feature deserialize(JsonParser jsonParser,
                               DeserializationContext deserializationContext) throws IOException {
        ObjectCodec codec = jsonParser.getCodec();
        FakeProperty fakeProperty =
                codec.readValue(jsonParser, FakeProperty.class);
        Feature result = fakeProperty.toProperty();
        Map<String, JsonNode> properties = fakeProperty.properties;
        for (Map.Entry<String, JsonNode> s : properties.entrySet()) {
            //pattern matching to determine which impl of property should be used
            Property property = null;

            String key = s.getKey().toLowerCase();
            switch (key) {
                case "protocol":
                    property =
                            mapper.treeToValue(s.getValue(), ProtocolField.class);
                    break;
                case "version":
                    property = mapper.treeToValue(s.getValue(), VersionField.class);
                    break;
                case "algorithm":
                    property =
                            mapper.treeToValue(s.getValue(), AlgorithmField.class);
                    break;
                case "keylength":
                    property =
                            mapper.treeToValue(s.getValue(), KeylengthField.class);
                    break;
                case "level":
                    property = mapper.treeToValue(s.getValue(), LevelField.class);
                    break;
                case "samplerate":
                    property =
                            mapper.treeToValue(s.getValue(), SamplerateField.class);
                    break;
                case "instrumentation":
                    property = mapper.treeToValue(s.getValue(),
                            InstrumentationField.class);
                    break;
                case "credentials":
                    property = mapper.treeToValue(s.getValue(),
                            CredentialsField.class);
                    break;
                case "announcementaddress":
                    property = mapper.treeToValue(s.getValue(),
                            AnnouncementAddressField.class);
                    break;
                case "required":
                    property =
                            mapper.treeToValue(s.getValue(), RequiredField.class);
                    break;
                case "guarantor":
                    property =
                            mapper.treeToValue(s.getValue(), GuarantorField.class);
                    break;
                case "availablepurpose":
                    property = mapper.treeToValue(s.getValue(), AvailablePurposeField.class);
                    break;
                case "allowedguarantor":
                    property = mapper.treeToValue(s.getValue(),
                            AllowedGuarantorField.class);
                    break;
                case "purpose":
                    property = mapper.treeToValue(s.getValue(), PurposeField.class);
                    break;
                case "volume":
                    property = mapper.treeToValue(s.getValue(), VolumeField.class);
                    break;
                case "accuracy":
                    property = mapper.treeToValue(s.getValue(), AccuracyField.class);
                    break;
                case "ramGain":
                    property = mapper.treeToValue(s.getValue(), RamGainField.class);
                    break;
                case "ramLimit":
                    property = mapper.treeToValue(s.getValue(), RamLimitField.class);
                    break;
                case "spaceGain":
                    property = mapper.treeToValue(s.getValue(), SpaceGainField.class);
                    break;
                case "spaceLimit":
                    property = mapper.treeToValue(s.getValue(), SpaceLimitField.class);
                    break;
                case "completeness":
                    property = mapper.treeToValue(s.getValue(), CompletenessField.class);
                    break;
                case "timeliness":
                    property = mapper.treeToValue(s.getValue(), TimelinessField.class);
                    break;
                case "precision":
                    property = mapper.treeToValue(s.getValue(), PrecisionField.class);
                    break;
                /*case "availability":
                    property = mapper.treeToValue(s.getValue(), AvailabilityField.class);
                    break;
*/

            }
            if (property != null) {
                result.addProperty(property, key);
            }
        }

        return result;
    }
}
