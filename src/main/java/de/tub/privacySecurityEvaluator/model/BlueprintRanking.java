package de.tub.privacySecurityEvaluator.model;

public class BlueprintRanking  {
    private Blueprint blueprint;
    private double score;

    public BlueprintRanking(Blueprint blueprint, double score) {
        this.blueprint = blueprint;
        this.score = score;
    }

    public BlueprintRanking() {
    }

    public Blueprint getBlueprint() {
        return blueprint;
    }

    public void setBlueprint(Blueprint blueprint) {
        this.blueprint = blueprint;
    }



    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
