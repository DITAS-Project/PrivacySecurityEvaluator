package de.tub.privacySecurityEvaluator.service;


import de.tub.privacySecurityEvaluator.model.Blueprint;
import de.tub.privacySecurityEvaluator.model.BlueprintRanking;
import de.tub.privacySecurityEvaluator.model.Feature;
import de.tub.privacySecurityEvaluator.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class EvaluatorServiceImpl implements EvaluatorService {


    private RankingService rankingService;

    @Autowired
    public void setRankingService(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @Override
    public List<BlueprintRanking> evaluateRequest(Request request) {
        HashSet<Blueprint> filteredSubset = filter(request.getRequirement(), request.getBlueprintMetrics());

        HashSet<Blueprint> validSubset = validate(request.getRequirement(), filteredSubset);

        List<BlueprintRanking> ret = rankingService.rank(request.getRequirement(), validSubset);

        return ret;
    }


    private HashSet<Blueprint> validate(Feature requirement, HashSet<Blueprint> blueprints) {
        HashSet<Blueprint> validSet = new HashSet<>();

        for (Blueprint bluePrintMetric : blueprints) {
            if (bluePrintMetric.getFeature().validate(requirement)) {
                validSet.add(bluePrintMetric);
            }
        }
        return validSet;
    }

    private HashSet<Blueprint> filter(Feature requirement, List<Blueprint> blueprints) {
        return null;
    }
}
