package de.tub.privacySecurityEvaluator.service;


import de.tub.privacySecurityEvaluator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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
        HashSet<Blueprint> filteredSubset = new HashSet<>();
        final List<? extends Class<? extends Property>> clases = requirement.getProperties().stream().map(Property::getClass).collect(Collectors.toList());
        for (Blueprint blueprint : blueprints) {
            boolean valid = false;
            for (Property property : blueprint.getFeature().getProperties()) {
                if (!clases.contains(property.getClass())) {
                    valid = false;
                    break;
                }
                valid = true;
            }
            if (valid) filteredSubset.add(blueprint);

        }

        return filteredSubset;
    }
}
