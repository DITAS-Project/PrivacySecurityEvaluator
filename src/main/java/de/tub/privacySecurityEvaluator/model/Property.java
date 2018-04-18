package de.tub.privacySecurityEvaluator.model;

import java.util.List;

public class Property {
    private String id;
    private String name;
    private String type;
    private List<Propertiefield> properties;


    public Property(String id, String name, String type, List<Propertiefield> properties) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.properties = properties;
    }

    public Property() {
    }

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

    public List<Propertiefield> getProperties() {
        return properties;
    }

    public void setProperties(List<Propertiefield> properties) {
        this.properties = properties;
    }

    public void addProperty(Propertiefield field) {
        this.properties.add(field);
    }
}
