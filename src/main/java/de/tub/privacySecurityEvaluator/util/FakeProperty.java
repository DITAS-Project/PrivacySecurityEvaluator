package de.tub.privacySecurityEvaluator.util;

import com.fasterxml.jackson.databind.JsonNode;
import de.tub.privacySecurityEvaluator.model.Feature;

import java.util.HashMap;
import java.util.Map;

/**
 * helperclass to deserialize the Properties
 */
public class FakeProperty {
    public String id;
    public String type;
    public Map<String, JsonNode> properties;
    public String description;

    public Feature toProperty() {
        return new Feature(id, type, new HashMap<>(), description);
    }
}
