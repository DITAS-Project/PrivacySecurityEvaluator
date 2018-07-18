package de.tub.privacySecurityEvaluator.model;

public class BlueprintRanking  {
    private Feature blueprint;
    private double score;

    public BlueprintRanking(Feature blueprint, double score) {
        this.blueprint = blueprint;
        this.score = score;
    }

    public BlueprintRanking() {
    }

    public Feature getBlueprint() {
        return blueprint;
    }

    public void setBlueprint(Feature blueprint) {
        this.blueprint = blueprint;
    }



    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
