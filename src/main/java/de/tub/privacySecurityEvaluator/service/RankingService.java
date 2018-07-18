package de.tub.privacySecurityEvaluator.service;

import de.tub.privacySecurityEvaluator.model.BlueprintRanking;
import de.tub.privacySecurityEvaluator.model.Feature;

import java.util.Collection;
import java.util.List;

public interface RankingService {

    List<BlueprintRanking> rank(Feature requirement, Collection<Feature> blueprints);

}
