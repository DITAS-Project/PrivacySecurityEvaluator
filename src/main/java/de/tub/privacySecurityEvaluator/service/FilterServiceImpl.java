package de.tub.privacySecurityEvaluator.service;

import de.tub.privacySecurityEvaluator.model.Feature;
import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.Request;
import de.tub.privacySecurityEvaluator.model.fields.PurposeField;
import de.tub.privacySecurityEvaluator.model.strategies.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FilterServiceImpl implements FilterService {

    private static final Logger logger = LoggerFactory.getLogger(FilterServiceImpl.class);


    @Override
    public HashSet<Feature> filter(Request request) {
        setStrategies(new HashSet<>(request.getBlueprintAttributes()));
        Feature requirement= request.getRequirement();
        List<Feature> blueprints= request.getBlueprintAttributes();

        logger.debug("begin filtering of {}",requirement);
        HashSet<Feature> filteredSubset = new HashSet<>();
        Set<? extends Class<? extends Property>> classes = requirement.getProperties().values().stream().map(Property::getClass).collect(Collectors.toSet());
        logger.debug("match set of {}",classes.stream().map(f->((Class) f).getCanonicalName()).toArray());

        for (Feature blueprint : blueprints) {
            boolean valid = false;
            for (Map.Entry<String, Property> property : blueprint.getProperties().entrySet()) {

                if (!classes.contains(property.getValue().getClass())) {
                    valid = false;
                    logger.debug("mismatch due to {} - {}",property.getKey(),property.getValue().getClass().getName());
                    break;
                }
                valid = true;
            }
            if (valid) filteredSubset.add(blueprint);

        }

        return filteredSubset;
    }

    /**
     * TODO move to separate StrategyBuilder Class
     * @param blueprints
     */
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
