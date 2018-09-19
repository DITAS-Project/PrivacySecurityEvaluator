package de.tub.privacySecurityEvaluator.model.strategies;

import de.tub.privacySecurityEvaluator.model.ValidationStrategy;

public class NoValidStrategy implements ValidationStrategy{
    @Override
    public boolean validate(Object req, Object blueprint) {

        return false;
    }
}
