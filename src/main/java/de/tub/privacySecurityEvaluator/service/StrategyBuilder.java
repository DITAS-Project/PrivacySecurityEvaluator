package de.tub.privacySecurityEvaluator.service;

import de.tub.privacySecurityEvaluator.model.Feature;
import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.strategies.RankingStrategy;
import de.tub.privacySecurityEvaluator.model.strategies.ValidationStrategy;
import de.tub.privacySecurityEvaluator.model.strategies.ranking.GraphRankingStrategy;
import de.tub.privacySecurityEvaluator.model.strategies.ranking.JaccardRankingStrategy;
import de.tub.privacySecurityEvaluator.model.strategies.ranking.MinimumRankingStrategy;
import de.tub.privacySecurityEvaluator.model.strategies.ranking.NoRankingStrategy;
import de.tub.privacySecurityEvaluator.model.strategies.validation.*;

import java.util.Collection;
import java.util.Map;

public class StrategyBuilder {


    public static void setDefaultValidation(Collection<Feature> blueprints) {
        DefaultStringStrategy stringStrategy = new DefaultStringStrategy();
        ContainsAllStrategy containsAllStrategy = new ContainsAllStrategy();
        DefaultGraphStrategy graphStrategy = new DefaultGraphStrategy();
        ContainsOnceStrategy containsOnceStrategy = new ContainsOnceStrategy();
        DefaultIntegerStrategy integerStrategy = new DefaultIntegerStrategy();
        NoValidStrategy noValidStrategy = new NoValidStrategy();

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
                        val = stringStrategy;
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
                        val = containsOnceStrategy;//need to think of this later because no validation method needed
                        break;
                    case "allowedguarantor":
                        val = containsOnceStrategy;
                        break;
                    case "purpose":
                        val = graphStrategy;
                        break;


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