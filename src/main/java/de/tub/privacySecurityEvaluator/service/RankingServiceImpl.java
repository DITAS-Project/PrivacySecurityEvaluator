package de.tub.privacySecurityEvaluator.service;

import de.tub.privacySecurityEvaluator.model.BlueprintRanking;
import de.tub.privacySecurityEvaluator.model.Feature;
import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.Rankabale;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

@Profile("real")
@Service
public class RankingServiceImpl implements RankingService {

    @Override
    public List<BlueprintRanking> rank(Feature requirement, Collection<Feature> blueprints) {
        List<BlueprintRanking> rankings = new LinkedList<>();

        for (Feature b : blueprints) {
            rankings.add(new BlueprintRanking(b, rank(requirement,b)));
        }
        return rankings;
    }


    /**
     * internal method to rank the blueprints based on their ranking method
     * priorities can be changed for now all have the same priority and add up
     * @param requirement
     * @param blueprint
     * @return
     */
    private double rank(Feature requirement, Feature blueprint) {
        Map<String,Property> properties= blueprint.getProperties();
        Set<Map.Entry<String, Property>> rankProperties = new HashSet<Map.Entry<String, Property>>(properties.entrySet());
        rankProperties.removeIf((Map.Entry<String, Property> c) -> !(c.getValue() instanceof Rankabale));
        double score = 0.0;
        for (Map.Entry<String, Property> prop : rankProperties) {
            for (Map.Entry<String, Property> req : requirement.getProperties().entrySet()) {
                score += ((Rankabale) prop.getValue()).rank(req.getValue());
            }
        }
        return score;

    }
}
