package de.tub.privacySecurityEvaluator.model.strategies.validation;

import de.tub.privacySecurityEvaluator.model.strategies.ValidationStrategy;

public class NoValidStrategy implements ValidationStrategy{
    @Override
    public boolean validate(Object req, Object blueprint) {

        return false;
    }
}
