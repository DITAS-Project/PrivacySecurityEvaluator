package de.tub.privacySecurityEvaluator.model.strategies;

import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.ValidationStrategy;

public class DefaultStringStrategy implements ValidationStrategy<Property<String>>{


    @Override
    public boolean validate(Property<String> req, Property<String> blueprint) {
        return req.getValue().equals(blueprint.getValue());
    }
}
