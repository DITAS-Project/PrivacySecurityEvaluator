package de.tub.privacySecurityEvaluator.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.tub.privacySecurityEvaluator.service.EvaluatorServiceImpl;
import de.tub.privacySecurityEvaluator.util.PropertyDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Data representation of a blueprint/requirement
 * holds data according to the specified json format file
 */

@JsonDeserialize(using = PropertyDeserializer.class)
public class Feature {
    private String id;
    private String type;
    private String description;
    private Map<String, Property> properties;


    private static final Logger logger = LoggerFactory.getLogger(Feature.class);

    public Feature(String id, String type, Map<String, Property> properties, String description) {
        this.id = id;
        this.description = description;

        this.type = type;
        this.properties = properties;
    }

    public Feature() {
        this.properties = new HashMap<>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Property> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Property> properties) {
        this.properties = properties;
    }

    public void addProperty(Property field, String name) {
        this.properties.put(name, field);
    }

    /**
     * Validation method to determine if a blueprint fullfills the requirement
     *

    public boolean validate(Feature requirement) {
        for (Map.Entry<String, Property> req : requirement.getProperties().entrySet()) {
            boolean fullfilled = false;

            //search for the right fieldtype and validate
            for (Map.Entry<String, Property> property : properties.entrySet()) {
                if (property.getValue().getClass().equals(req.getValue().getClass())) {
                    if (property.getValue().validate(req.getValue())) {
                        fullfilled = true;
                        break;
                    }
                }
            }
            if (!fullfilled) {
                return false;
            }
        }
        return true;
    }
*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feature feature = (Feature) o;

        if (getId() != null ? !getId().equals(feature.getId()) : feature.getId() != null) return false;
        if (getDescription() != null ? !getDescription().equals(feature.getDescription()) : feature.getDescription() != null)
            return false;
        if (getType() != null ? !getType().equals(feature.getType()) : feature.getType() != null) return false;
        return getProperties() != null ? getProperties().equals(feature.getProperties()) : feature.getProperties() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getProperties() != null ? getProperties().hashCode() : 0);
        return result;
    }


}
