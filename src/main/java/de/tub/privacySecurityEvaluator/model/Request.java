package de.tub.privacySecurityEvaluator.model;

import java.util.List;

public class Request {
    private Feature requirement;
    private List<Blueprint> blueprintMetrics;

    public Feature getRequirement() {
        return requirement;
    }

    public void setRequirement(Feature requirement) {
        this.requirement = requirement;
    }

    public List<Blueprint> getBlueprintMetrics() {
        return blueprintMetrics;
    }

    public void setBlueprintMetrics(List<Blueprint> bluePrintMetrics) {
        this.blueprintMetrics = bluePrintMetrics;
    }



}

