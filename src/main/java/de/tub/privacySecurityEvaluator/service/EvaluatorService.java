package de.tub.privacySecurityEvaluator.service;

import de.tub.privacySecurityEvaluator.model.BlueprintRanking;
import de.tub.privacySecurityEvaluator.model.Request;

import java.util.List;

/**
 * filters all blueprints that do not fit the requirement and ranks the remaining ones
 */
public interface EvaluatorService {

    List<BlueprintRanking> evaluateRequest(Request request);


}
