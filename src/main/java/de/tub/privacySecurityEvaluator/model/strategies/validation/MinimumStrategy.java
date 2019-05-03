package de.tub.privacySecurityEvaluator.model.strategies.validation;

import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.strategies.ValidationStrategy;

public class MinimumStrategy implements ValidationStrategy<Property<? extends Number>> {
    @Override
    public boolean validate(Property<? extends Number> req, Property<? extends Number> blueprint) {
        return req.getValue().doubleValue()<=blueprint.getValue().doubleValue();
    }
}
