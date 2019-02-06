package de.tub.privacySecurityEvaluator.api;

import de.tub.privacySecurityEvaluator.model.BlueprintRanking;
import de.tub.privacySecurityEvaluator.model.Request;
import de.tub.privacySecurityEvaluator.service.EvaluatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@Api(value = "pse",description = "DITAS privacy security blueprint evaluator",tags = "deprecated")
@RequestMapping(value = "/")
public class LegacyAPISupport extends PrivacySecurityEvaluator {

    @Autowired
    @Override
    public void setEvaluatorService(EvaluatorService evaluatorService) {
        super.setEvaluatorService(evaluatorService);
    }

    @Override
    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    @ApiOperation(value = "evaluates a given blueprint request",response = BlueprintRanking.class,produces = "application/json",tags = "deprectadeds")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "ranked blueprint metrics"),
            @ApiResponse(code = 400, message = "Request input did not match expected format or could not be ranked")
    })
    public ResponseEntity<List<BlueprintRanking>> filterPolicies(@RequestBody Request input) {
        return ResponseEntity.ok().header("X-WARNING","deprecated api").body(super.filterPolicies(input).getBody());
    }
}
