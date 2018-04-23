package de.tub.privacySecurityEvaluator.model;

import java.util.List;

public class Feature {
    private String id;
    private String name;
    private String type;
    private List<Property> properties;


    public Feature(String id, String name, String type, List<Property> properties) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.properties = properties;
    }

    public Feature() {
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

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public void addProperty(Property field) {
        this.properties.add(field);
    }

    public boolean validate(Feature requirement) {
        for (Property req : requirement.getProperties()) {
            boolean fullfied = false;
            for (Property property : properties) {
                if (property.validate(req)) {
                    fullfied = true;
                    break;
                }
            }
            if (!fullfied) {
                return false;
            }
        }
        return true;
    }
}
