package de.tub.privacySecurityEvaluator.model.strategies.validation;

import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.strategies.ValidationStrategy;

public class NoValidStrategy implements ValidationStrategy<Property>{

    @Override
    public boolean validate(Property req, Property blueprint) {
        return false;
    }
}
