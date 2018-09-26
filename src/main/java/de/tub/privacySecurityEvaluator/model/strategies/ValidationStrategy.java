package de.tub.privacySecurityEvaluator.model.strategies;

public interface ValidationStrategy <Property> {

    boolean validate(Property req, Property blueprint);
}
