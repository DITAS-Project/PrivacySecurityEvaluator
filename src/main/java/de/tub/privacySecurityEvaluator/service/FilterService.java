package de.tub.privacySecurityEvaluator.service;

import de.tub.privacySecurityEvaluator.model.Feature;
import de.tub.privacySecurityEvaluator.model.Request;

import java.util.HashSet;
import java.util.List;

public interface FilterService {

    HashSet<Feature> filter(Request request);
}
