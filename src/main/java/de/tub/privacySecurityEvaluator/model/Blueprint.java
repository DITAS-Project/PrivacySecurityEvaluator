package de.tub.privacySecurityEvaluator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Blueprint {

    private String type;
    private String description;

    @JsonProperty("properties")
    private Feature feature;

    public Blueprint() {
    }

    public Blueprint(String type, String description, Feature feature) {
        this.type = type;
        this.description = description;
        this.feature = feature;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Blueprint blueprint = (Blueprint) o;

        if (!getType().equals(blueprint.getType())) return false;
        if (!getDescription().equals(blueprint.getDescription())) return false;
        return getFeature() != null ? getFeature().equals(blueprint.getFeature()) : blueprint.getFeature() == null;
    }

    @Override
    public int hashCode() {
        int result = getType().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + (getFeature() != null ? getFeature().hashCode() : 0);
        return result;
    }
}
