package de.tub.privacySecurityEvaluator.model;

import java.util.List;

public class Request {
    private Feature requirement;
    //  @JsonIgnore()
    private List<Feature> blueprintAttributes;

    public Request(Feature requirement, List<Feature> blueprintAttributes) {
        this.requirement = requirement;
        this.blueprintAttributes = blueprintAttributes;
    }

    public Request() {
    }

    public Feature getRequirement() {
        return requirement;
    }

    public void setRequirement(Feature requirement) {
        this.requirement = requirement;
    }

    //@JsonIgnore()
    public List<Feature> getBlueprintAttributes() {
        return blueprintAttributes;
    }

    //@JsonIgnore()
    public void setBlueprintAttributes(List<Feature> bluePrintMetrics) {
        this.blueprintAttributes = bluePrintMetrics;
    }



}

