package de.tub.privacySecurityEvaluator.model;

public interface RankingStrategy<P extends Property>{
    double rank(P req, P blueprint);
}
