package de.tub.privacySecurityEvaluator.model.strategies;

import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.RankingStrategy;

import java.util.HashSet;

public class JaccardRankingStrategy<T> implements RankingStrategy<Property<HashSet<T>>> {






    @Override
    public double rank(Property<HashSet<T>> req, Property<HashSet<T>> blueprint) {
        HashSet<T> reqSet = req.getValue();
        HashSet<T> value= blueprint.getValue();
        HashSet<T> intersection = new HashSet<>(value);
        HashSet<T> union = new HashSet<>(value);
        union.addAll(reqSet);
        intersection.retainAll(reqSet);
        return (double) intersection.size()/union.size();    }
}
