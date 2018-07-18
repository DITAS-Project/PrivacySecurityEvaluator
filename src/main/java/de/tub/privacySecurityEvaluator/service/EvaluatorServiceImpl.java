package de.tub.privacySecurityEvaluator.service;


import de.tub.privacySecurityEvaluator.model.BlueprintRanking;
import de.tub.privacySecurityEvaluator.model.Feature;
import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
        HashSet<Feature> filteredSubset = filter(request.getRequirement(), request.getBlueprintAttributes());

        HashSet<Feature> validSubset = validate(request.getRequirement(), filteredSubset);

        return rankingService.rank(request.getRequirement(), validSubset);
    }


    public HashSet<Feature> validate(Feature requirement, HashSet<Feature> blueprints) {
        HashSet<Feature> validSet = new HashSet<>();

        for (Feature bluePrintMetric : blueprints) {
            if (bluePrintMetric.validate(requirement)) {
                validSet.add(bluePrintMetric);
            }
        }
        return validSet;
    }

    public HashSet<Feature> filter(Feature requirement, List<Feature> blueprints) {
        HashSet<Feature> filteredSubset = new HashSet<>();
        Set<? extends Class<? extends Property>> classes = requirement.getProperties().values().stream().map(Property::getClass).collect(Collectors.toSet());
        for (Feature blueprint : blueprints) {
            boolean valid = false;
            for (Map.Entry<String, Property> property : blueprint.getProperties().entrySet()) {
                if (!classes.contains(property.getValue().getClass())) {
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
