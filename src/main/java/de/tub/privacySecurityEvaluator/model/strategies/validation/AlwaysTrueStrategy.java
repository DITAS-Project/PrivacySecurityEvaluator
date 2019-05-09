package de.tub.privacySecurityEvaluator.model.strategies.validation;

import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.strategies.ValidationStrategy;

public class AlwaysTrueStrategy implements ValidationStrategy{
    @Override
    public boolean validate(Property req, Property blueprint) {
        return true;
    }
}
