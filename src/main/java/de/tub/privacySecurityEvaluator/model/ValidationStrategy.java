package de.tub.privacySecurityEvaluator.model;

public interface ValidationStrategy <Property> {

    boolean validate(Property req, Property blueprint);
}
