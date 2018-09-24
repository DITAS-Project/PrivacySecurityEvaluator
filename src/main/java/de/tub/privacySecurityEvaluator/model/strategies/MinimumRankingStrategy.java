package de.tub.privacySecurityEvaluator.model.strategies;

import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.RankingStrategy;

public class MinimumRankingStrategy<T extends Number> implements RankingStrategy<Property<T >>{




    @Override
    public double rank(Property<T> req, Property<T> blueprint) {
        Number bp= blueprint.getValue();
        Number rq= req.getValue();
        return bp.doubleValue()/rq.doubleValue();
    }
}
