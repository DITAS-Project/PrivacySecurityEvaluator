package de.tub.privacySecurityEvaluator.model;

import java.util.List;

public class Request {
    private Property requirement;
    private List<BluePrintMetric> blueprintMetrics;

    public Property getRequirement() {
        return requirement;
    }

    public void setRequirement(Property requirement) {
        this.requirement = requirement;
    }

    public List<BluePrintMetric> getBlueprintMetrics() {
        return blueprintMetrics;
    }

    public void setBlueprintMetrics(List<BluePrintMetric> bluePrintMetrics) {
        this.blueprintMetrics = bluePrintMetrics;
    }



}

