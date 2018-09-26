package de.tub.privacySecurityEvaluator.model.strategies.ranking;

import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.strategies.RankingStrategy;

public class NoRankingStrategy implements RankingStrategy{

    @Override
    public double rank(Property req, Property blueprint) {
        return 0;
    }
}
