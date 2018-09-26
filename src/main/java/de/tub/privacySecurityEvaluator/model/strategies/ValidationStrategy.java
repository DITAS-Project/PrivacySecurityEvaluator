package de.tub.privacySecurityEvaluator.model.strategies;

import de.tub.privacySecurityEvaluator.model.Property;

public interface ValidationStrategy <P extends Property> {

    boolean validate(P req, P blueprint);
}
