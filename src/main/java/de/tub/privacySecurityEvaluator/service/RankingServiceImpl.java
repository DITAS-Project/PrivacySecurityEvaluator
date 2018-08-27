package de.tub.privacySecurityEvaluator.service;

import de.tub.privacySecurityEvaluator.model.BlueprintRanking;
import de.tub.privacySecurityEvaluator.model.Feature;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
@Profile("real")
@Service
public class RankingServiceImpl implements RankingService {

    @Override
    public List<BlueprintRanking> rank(Feature requirement, Collection<Feature> blueprints) {
        List<BlueprintRanking> rankings = new LinkedList<>();

        for (Feature b : blueprints) {
            rankings.add(new BlueprintRanking(b, b.rank(requirement)));
        }
        return rankings;
    }
}
