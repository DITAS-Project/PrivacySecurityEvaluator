package de.tub.privacySecurityEvaluator.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.tub.privacySecurityEvaluator.util.PropertyDeserializer;

import java.util.List;

@JsonDeserialize(using = PropertyDeserializer.class)
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
            boolean fullfilled = false;
            for (Property property : properties) {
                if (property.validate(req)) {
                    fullfilled = true;
                    break;
                }
            }
            if (!fullfilled) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feature feature = (Feature) o;

        if (getId() != null ? !getId().equals(feature.getId()) : feature.getId() != null) return false;
        if (getName() != null ? !getName().equals(feature.getName()) : feature.getName() != null) return false;
        if (getType() != null ? !getType().equals(feature.getType()) : feature.getType() != null) return false;
        return getProperties() != null ? getProperties().equals(feature.getProperties()) : feature.getProperties() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getProperties() != null ? getProperties().hashCode() : 0);
        return result;
    }
}
