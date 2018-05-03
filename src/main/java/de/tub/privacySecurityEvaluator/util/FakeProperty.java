package de.tub.privacySecurityEvaluator.util;

import com.fasterxml.jackson.databind.JsonNode;
import de.tub.privacySecurityEvaluator.model.Feature;

import java.util.LinkedList;
import java.util.List;

public class FakeProperty {
    public String id;
    public String name;
    public String type;
    public List<JsonNode> properties;

    public Feature toProperty() {
        return new Feature(id, name, type, new LinkedList<>());
    }
}
