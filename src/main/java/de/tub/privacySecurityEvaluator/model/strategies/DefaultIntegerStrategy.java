package de.tub.privacySecurityEvaluator.model.strategies;

import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.ValidationStrategy;

public class DefaultIntegerStrategy implements ValidationStrategy<Property<? extends Integer>> {
    @Override
    public boolean validate(Property<? extends Integer> req, Property<? extends Integer> blueprint) {
        return req.getValue()<=blueprint.getValue();
    }
}
