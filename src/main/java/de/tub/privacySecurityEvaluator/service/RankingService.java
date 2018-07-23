package de.tub.privacySecurityEvaluator.service;

import de.tub.privacySecurityEvaluator.model.BlueprintRanking;
import de.tub.privacySecurityEvaluator.model.Feature;

import java.util.Collection;
import java.util.List;

public interface RankingService {
    /**
     * function to rank blueprints according to the requirement input
     * needs concrete implementation
     *
     * @param requirement
     * @param blueprints
     * @return
     */
    List<BlueprintRanking> rank(Feature requirement, Collection<Feature> blueprints);

}
