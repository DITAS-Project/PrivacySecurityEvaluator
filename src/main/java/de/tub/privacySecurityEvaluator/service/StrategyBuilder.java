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

import de.tub.privacySecurityEvaluator.model.Feature;
import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.fields.AvailablePurposeListField;
import de.tub.privacySecurityEvaluator.model.fields.datautilityfields.*;
import de.tub.privacySecurityEvaluator.model.strategies.RankingStrategy;
import de.tub.privacySecurityEvaluator.model.strategies.ValidationStrategy;
import de.tub.privacySecurityEvaluator.model.strategies.ranking.GraphRankingStrategy;
import de.tub.privacySecurityEvaluator.model.strategies.ranking.JaccardRankingStrategy;
import de.tub.privacySecurityEvaluator.model.strategies.ranking.MinimumRankingStrategy;
import de.tub.privacySecurityEvaluator.model.strategies.ranking.NoRankingStrategy;
import de.tub.privacySecurityEvaluator.model.strategies.validation.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.Collection;
import java.util.Map;

public class StrategyBuilder {

    @Value("${filtering.datautil}")
    private static boolean filterDatautil;

    public static void setDefaultValidation(Collection<Feature> blueprints) {
        DefaultStringStrategy stringStrategy = new DefaultStringStrategy();
        ContainsAllStrategy containsAllStrategy = new ContainsAllStrategy();
        DefaultGraphStrategy graphStrategy = new DefaultGraphStrategy();
        ContainsOnceStrategy containsOnceStrategy = new ContainsOnceStrategy();
        DefaultIntegerStrategy integerStrategy = new DefaultIntegerStrategy();
        NoValidStrategy noValidStrategy = new NoValidStrategy();
        ValidationStrategy minStrategy= new MinimumStrategy();
        ValidationStrategy maxStrategy= new MaximumStrategy();
        ListContainsOnceStrategy listOnceStrategy= new ListContainsOnceStrategy();

        if(filterDatautil){
            AlwaysTrueStrategy trueStrategy= new AlwaysTrueStrategy();
            minStrategy=trueStrategy;
            maxStrategy=trueStrategy;

        }
        for (Feature f : blueprints) {
            for (Map.Entry<String, Property> entry : f.getProperties().entrySet()) {

                String key = entry.getKey().toLowerCase();
                ValidationStrategy val = null;
                switch (key) {
                    case "protocol":
                        val = stringStrategy;
                        break;
                    case "version":
                        val = stringStrategy;
                        break;
                    case "algorithm":
                        val = listOnceStrategy;
                        break;
                    case "keylength":
                        val = integerStrategy;
                        break;
                    case "level":
                        val = stringStrategy;
                        break;
                    case "samplerate":
                        val = integerStrategy;
                        break;
                    case "instrumentation":
                        val = stringStrategy;
                        break;
                    case "credentials":
                        val = containsAllStrategy;
                        break;
                    case "announcementaddress":
                        val = stringStrategy;
                        break;
                    case "required":
                        val = noValidStrategy;
                        break;
                    case "guarantor":
                        val = noValidStrategy;
                        break;
                    case "availablepurpose":
                        if(entry.getValue() instanceof AvailablePurposeListField){
                            val= containsOnceStrategy;
                            break;
                        }
                        val = graphStrategy;//need to think of this later because no validation method needed
                        break;
                    case "allowedguarantor":
                        val = containsOnceStrategy;
                        break;
                    case "purpose":
                        val = graphStrategy;
                        break;
                  /*  case "volume":
                        val = graphStrategy;
                        break;*/ //dont know how to validate yet
                    case "accuracy":
                        val = minStrategy;
                        break;
                    case "ramGain":
                        val = maxStrategy; //just a guess for now
                        break;
                    case "ramLimit":
                        val = maxStrategy; //just a guess
                        break;
                    case "spaceGain":
                        val = maxStrategy; //just a guess
                        break;
                    case "spaceLimit":
                        val = maxStrategy; //just aguess
                        break;
                    case "completeness":
                        val = minStrategy;
                        break;
                    case "timeliness":
                        val = maxStrategy;
                        break;
                    case "precision":
                        val = minStrategy;
                        break;
                /*case "availability":
                      val = graphStrategy;
                        break;*/ //dont know how to validate yet



                }
                if (val != null) entry.getValue().setValStrategy(val);
                else entry.getValue().setValStrategy(noValidStrategy);
            }
        }
    }

    public static void setDefaultRanking(Collection<Feature> blueprints) {
        GraphRankingStrategy graphStrategy = new GraphRankingStrategy();
        JaccardRankingStrategy jaccardStrategy = new JaccardRankingStrategy();
        MinimumRankingStrategy minimumStrategy = new MinimumRankingStrategy();
        NoRankingStrategy noStrategy = new NoRankingStrategy();


        for (Feature f : blueprints) {
            for (Map.Entry<String, Property> entry : f.getProperties().entrySet()) {

                String key = entry.getKey().toLowerCase();
                RankingStrategy rank = null;
                switch (key) {
                    case "keylength":
                        rank = (minimumStrategy);
                        break;
                    case "samplerate":
                        rank = (minimumStrategy);
                        break;
                    case "credentials":
                        rank = (jaccardStrategy);
                        break;
                    case "availablepurpose":
                        rank = (graphStrategy);//does not work like this
                        break;
                    case "allowedguarantor":
                        rank = (jaccardStrategy);
                        break;
                    case "purpose":
                        rank = (graphStrategy);
                        break;
                    default:
                        rank = noStrategy;


                }
                entry.getValue().setRankStrategy(rank);
            }
        }
    }

}
