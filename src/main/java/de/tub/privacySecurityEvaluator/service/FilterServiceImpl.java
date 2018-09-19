package de.tub.privacySecurityEvaluator.service;

import de.tub.privacySecurityEvaluator.model.Feature;
import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.Request;
import de.tub.privacySecurityEvaluator.model.strategies.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;

@Service
public class FilterServiceImpl implements FilterService {
    @Override
    public HashSet<Feature> filter(Request request) {
        setStrategies(new HashSet<>(request.getBlueprintAttributes()));
        return null;
    }


    public void setStrategies(HashSet<Feature> blueprints) {
        DefaultStringStrategy stringStrategy = new DefaultStringStrategy();
        ContainsAllStrategy containsAllStrategy = new ContainsAllStrategy();
        DefaultGraphStrategy graphStrategy = new DefaultGraphStrategy();
        ContainsOnceStrategy containsOnceStrategy = new ContainsOnceStrategy();
        DefaultIntegerStrategy integerStrategy = new DefaultIntegerStrategy();
        NoValidStrategy noValidStrategy = new NoValidStrategy();

        for (Feature f : blueprints) {
            for (Map.Entry<String, Property> entry : f.getProperties().entrySet()) {

                String key = entry.getKey().toLowerCase();
                switch (key) {
                    case "protocol":
                        entry.getValue().setValStrategy(stringStrategy);
                        break;
                    case "version":
                        entry.getValue().setValStrategy(stringStrategy);
                        break;
                    case "algorithm":
                        entry.getValue().setValStrategy(stringStrategy);
                        break;
                    case "keylength":
                        entry.getValue().setValStrategy(integerStrategy);
                        break;
                    case "level":
                        entry.getValue().setValStrategy(stringStrategy);
                        break;
                    case "samplerate":
                        entry.getValue().setValStrategy(integerStrategy);
                        break;
                    case "instrumentation":
                        entry.getValue().setValStrategy(stringStrategy);
                        break;
                    case "credentials":
                        entry.getValue().setValStrategy(containsAllStrategy);
                        break;
                    case "announcementaddress":
                        entry.getValue().setValStrategy(stringStrategy);
                        break;
                    case "required":
                        entry.getValue().setValStrategy(noValidStrategy);
                        break;
                    case "guarantor":
                        entry.getValue().setValStrategy(noValidStrategy);
                        break;
                    case "availablepurpose":
                        entry.getValue().setValStrategy(containsOnceStrategy);//need to think of this later because no validation method needed
                        break;
                    case "allowedguarantor":
                        entry.getValue().setValStrategy(containsOnceStrategy);
                        break;
                    case "purpose":
                        entry.getValue().setValStrategy(graphStrategy);
                        break;
                }
            }
        }
    }
}
