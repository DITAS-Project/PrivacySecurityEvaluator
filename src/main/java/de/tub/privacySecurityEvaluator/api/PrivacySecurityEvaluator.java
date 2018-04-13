package de.tub.privacySecurityEvaluator.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tub.privacySecurityEvaluator.model.BlueprintRanking;
import de.tub.privacySecurityEvaluator.model.Request;
import de.tub.privacySecurityEvaluator.service.EvaluatorService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@Api(value = "pse",description = "DITAS privacy security blueprint evaluator")
@RequestMapping(value = "/")
public class PrivacySecurityEvaluator {

    private EvaluatorService evaluatorService;


    @Autowired
    public void setEvaluatorService(EvaluatorService evaluatorService) {
        this.evaluatorService = evaluatorService;
    }


    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    @ApiOperation(value = "evaluates a given blueprint request",response = BlueprintRanking.class,produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "ranked blueprint metrics"),
            @ApiResponse(code = 400, message = "Request input did not match expected format or could not be ranked")
    })
    public List<BlueprintRanking> filterPolicies(@RequestBody  Request input) {
        return evaluatorService.evaluateRequest(input);
    }


}
