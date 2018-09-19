package de.tub.privacySecurityEvaluator.model.strategies;

import de.tub.privacySecurityEvaluator.model.RankingStrategy;

public class NoRankingStrategy implements RankingStrategy{
    @Override
    public double rank(Object req, Object blueprint) {
        return 0;
    }
}
