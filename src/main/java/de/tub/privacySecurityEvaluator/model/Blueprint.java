package de.tub.privacySecurityEvaluator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Blueprint {

    private String type;
    private String description;
    @JsonProperty(value = "feature")
    private Feature feature;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    public Feature getFeature() {
        return feature;
    }

    @JsonIgnore
    public void setFeature(Feature feature) {
        this.feature = feature;
    }
}
