/*
 * Copyright 2018 Information Systems Engineering, TU Berlin, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *                       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * This is being developed for the DITAS Project: https://www.ditas-project.eu/
 */

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
