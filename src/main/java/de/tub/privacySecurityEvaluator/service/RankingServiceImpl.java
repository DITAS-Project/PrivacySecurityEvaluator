package de.tub.privacySecurityEvaluator.service;

import de.tub.privacySecurityEvaluator.model.BlueprintRanking;
import de.tub.privacySecurityEvaluator.model.Feature;
import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.Rankabale;
import de.tub.privacySecurityEvaluator.model.strategies.ranking.GraphRankingStrategy;
import de.tub.privacySecurityEvaluator.model.strategies.ranking.JaccardRankingStrategy;
import de.tub.privacySecurityEvaluator.model.strategies.ranking.MinimumRankingStrategy;
import de.tub.privacySecurityEvaluator.model.strategies.ranking.NoRankingStrategy;
import de.tub.privacySecurityEvaluator.model.strategies.validation.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

@Profile("real")
@Service
public class RankingServiceImpl implements RankingService {

    @Override
    public List<BlueprintRanking> rank(Feature requirement, Collection<Feature> blueprints) {
        StrategyBuilder.setDefaultRanking(blueprints);
        List<BlueprintRanking> rankings = new LinkedList<>();

        for (Feature b : blueprints) {
            rankings.add(new BlueprintRanking(b, rank(requirement, b)));
        }
        return rankings;
    }


    /**
     * internal method to rank the blueprints based on their ranking method
     * priorities can be changed for now all have the same priority and add up
     *
     * @param requirement
     * @param blueprint
     * @return
     */
    private double rank(Feature requirement, Feature blueprint) {

        double score = 0.0;
        for (Map.Entry<String, Property> prop : blueprint.getProperties().entrySet()) {
            for (Map.Entry<String, Property> req : requirement.getProperties().entrySet()) {
                if(req.getValue().getClass().equals(prop.getValue().getClass()))
                score += prop.getValue().rank(req.getValue());
            }
        }
        return score;

    }


}
