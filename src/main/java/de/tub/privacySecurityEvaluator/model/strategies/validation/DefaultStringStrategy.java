package de.tub.privacySecurityEvaluator.model.strategies.validation;

import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.strategies.ValidationStrategy;

public class DefaultStringStrategy implements ValidationStrategy<Property<String>>{


    @Override
    public boolean validate(Property<String> req, Property<String> blueprint) {
        return req.getValue().equals(blueprint.getValue());
    }
}
