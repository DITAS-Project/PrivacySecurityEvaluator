package de.tub.privacySecurityEvaluator.model;

/**
 * Interface to show a Property is rankabale
 * rank strategy must be determined in the class
 */
@Deprecated
public interface Rankabale {

    double rank(Property requirement);
}
