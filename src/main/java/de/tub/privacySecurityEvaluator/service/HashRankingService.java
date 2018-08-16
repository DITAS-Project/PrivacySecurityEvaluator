package de.tub.privacySecurityEvaluator.service;

import de.tub.privacySecurityEvaluator.model.BlueprintRanking;
import de.tub.privacySecurityEvaluator.model.Feature;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Rankingservice implementation to produce deterministic ranking by their hash values
 */

@Service
@Profile("hashBased")
public class HashRankingService implements RankingService {

    HashRankingService(){

    }

    @Override
    public List<BlueprintRanking> rank(Feature requirement, Collection<Feature> blueprints) {
        LinkedList<BlueprintRanking> ret = new LinkedList<>();
        for (Feature blueprint : blueprints) {
            ret.add(new BlueprintRanking(blueprint, 1+(blueprint.hashCode() % 157) / 100.0));
        }

        return ret;
    }
}
