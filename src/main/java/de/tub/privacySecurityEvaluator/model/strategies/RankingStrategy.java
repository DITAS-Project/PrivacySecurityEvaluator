package de.tub.privacySecurityEvaluator.model.strategies;

import de.tub.privacySecurityEvaluator.model.Property;

public interface RankingStrategy<P extends Property>{
    double rank(P req, P blueprint);
}
