package de.tub.privacySecurityEvaluator.service;

import de.tub.privacySecurityEvaluator.model.BlueprintRanking;
import de.tub.privacySecurityEvaluator.model.Feature;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * rankingservice that ranks the blueprints with a random score between 1.0 and 0.0
 */
@Service
public class RandomRankingServiceImpl implements RankingService {


    @Override
    public List<BlueprintRanking> rank(Feature requirement, Collection<Feature> blueprints) {
        List<BlueprintRanking> rankings = new LinkedList<>();
        DecimalFormat df = new DecimalFormat("#.#");

        for (Feature b : blueprints) {
            rankings.add(new BlueprintRanking(b, (double) Math.round(Math.random() * 10d) / 10d));
        }
        return rankings;
    }
}
