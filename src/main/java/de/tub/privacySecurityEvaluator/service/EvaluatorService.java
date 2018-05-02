package de.tub.privacySecurityEvaluator.service;

import de.tub.privacySecurityEvaluator.model.BlueprintRanking;
import de.tub.privacySecurityEvaluator.model.Request;

import java.util.List;

public interface EvaluatorService {

    List<BlueprintRanking> evaluateRequest(Request request);


}
