package de.tub.privacySecurityEvaluator.service;


import com.sun.deploy.cache.CachedJarFile14;
import de.tub.privacySecurityEvaluator.model.BlueprintRanking;
import de.tub.privacySecurityEvaluator.model.Feature;
import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.Request;
import de.tub.privacySecurityEvaluator.model.fields.PurposeField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EvaluatorServiceImpl implements EvaluatorService {
    private static final Logger logger = LoggerFactory.getLogger(EvaluatorServiceImpl.class);

    private RankingService rankingService;

    private FilterService filterService;

    @Autowired
    public void setFilterService(FilterService filterService) { this.filterService = filterService; }

    @Autowired
    public void setRankingService(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @Override
    public List<BlueprintRanking> evaluateRequest(Request request) {

        HashSet<Feature> filteredSubset = filterService.filter(request);

        HashSet<Feature> validSubset = validate(request.getRequirement(), filteredSubset);

        return rankingService.rank(request.getRequirement(), validSubset);
    }

    /**
     * filters the featurelist
     * filtered list only holds blueprints that have all required fields
     *
     * @param requirement
     * @param blueprints
     * @return
     */
    public HashSet<Feature> filter(Feature requirement, List<Feature> blueprints) {
        logger.debug("begin filtering of {}",requirement);
        HashSet<Feature> filteredSubset = new HashSet<>();
        Set<? extends Class<? extends Property>> classes = requirement.getProperties().values().stream().map(Property::getClass).collect(Collectors.toSet());
        logger.debug("match set of {}",classes.stream().map(f->((Class) f).getCanonicalName()).toArray());

        for (Feature blueprint : blueprints) {
            boolean valid = false;
            for (Map.Entry<String, Property> property : blueprint.getProperties().entrySet()) {
                if(property.getValue() instanceof PurposeField){
                    valid= true;
                    break;
                }
                if (!classes.contains(property.getValue().getClass())) {
                    valid = false;
                    logger.debug("mismatch due to {} - {}",property.getKey(),property.getValue().getClass().getName());
                    break;
                }
                valid = true;
            }
            if (valid) filteredSubset.add(blueprint);

        }

        return filteredSubset;
    }

    /**
     * validates the filtered subset of the blueprints
     *
     * @param requirement
     * @param blueprints
     * @return
     */
    public HashSet<Feature> validate(Feature requirement, HashSet<Feature> blueprints) {
        HashSet<Feature> validSet = new HashSet<>();

        for (Feature bluePrintMetric : blueprints) {
            if (bluePrintMetric.validate(requirement)) {
                validSet.add(bluePrintMetric);
            } else {
                logger.debug("feature {} was invalid",bluePrintMetric.getId());
            }
        }
        return validSet;
    }


}
