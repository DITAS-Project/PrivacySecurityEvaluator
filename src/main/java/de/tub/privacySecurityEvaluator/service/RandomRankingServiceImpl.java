/*
 * Copyright 2018 Information Systems Engineering, TU Berlin, Germany
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at    http://www.apache.org/licenses/LICENSE-2.0Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.This is being developed for the DITAS Project: https://www.ditas-project.eu/
 */

package de.tub.privacySecurityEvaluator.service;

import de.tub.privacySecurityEvaluator.model.BlueprintRanking;
import de.tub.privacySecurityEvaluator.model.Feature;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * rankingservice that ranks the blueprints with a random score between 1.0 and 0.0
 */
@Service
@Profile("random")
public class RandomRankingServiceImpl implements RankingService {


    @Override
    public List<BlueprintRanking> rank(Feature requirement, Collection<Feature> blueprints) {
        List<BlueprintRanking> rankings = new LinkedList<>();

        for (Feature b : blueprints) {
            rankings.add(new BlueprintRanking(b, (double) 1.0+Math.round(Math.random() * 10d) / 10d));
        }
        return rankings;
    }
}
