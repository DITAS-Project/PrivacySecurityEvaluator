package de.tub.privacySecurityEvaluator.service;

import de.tub.privacySecurityEvaluator.model.BlueprintRanking;
import de.tub.privacySecurityEvaluator.model.Feature;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Rankingservice implementation to produce deterministic ranking by their hash values
 */

@Service
public class HashRankingService implements RankingService {
    @Override
    public List<BlueprintRanking> rank(Feature requirement, Collection<Feature> blueprints) {
        LinkedList<BlueprintRanking> ret = new LinkedList<>();
        for (Feature blueprint : blueprints) {
            double i = blueprint.hashCode() % 10 / 10;
            ret.add(new BlueprintRanking(blueprint, i));
        }

        return ret;
    }
}
