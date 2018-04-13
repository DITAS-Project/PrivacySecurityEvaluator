package de.tub.privacySecurityEvaluator.model;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class Property {
    private String id;
    private String name;
    private String type;
    private List<JsonNode> properties;

    public Property(String id, String name, String type, List<JsonNode> properties) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.properties = properties;
    }

    public Property(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<JsonNode> getProperties() {
        return properties;
    }

    public void setProperties(List<JsonNode> properties) {
        this.properties = properties;
    }


}
