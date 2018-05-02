package de.tub.privacySecurityEvaluator.service;

import de.tub.privacySecurityEvaluator.model.Blueprint;
import de.tub.privacySecurityEvaluator.model.BlueprintRanking;
import de.tub.privacySecurityEvaluator.model.Feature;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Richard on 23.04.2018.
 */
@Service
public class RankingServiceImpl implements RankingService {
    @Override
    public List<BlueprintRanking> rank(Feature requirement, Collection<Blueprint> blueprints) {
        List<BlueprintRanking> rankings = new LinkedList<>();
        DecimalFormat df = new DecimalFormat("#.#");

        for (Blueprint b : blueprints) {
            rankings.add(new BlueprintRanking(b, (double) Math.round(Math.random() * 10d) / 10d));
        }
        return rankings;
    }
}
