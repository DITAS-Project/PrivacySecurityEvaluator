/*
 * Copyright 2018 Information Systems Engineering, TU Berlin, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *                       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * This is being developed for the DITAS Project: https://www.ditas-project.eu/
 */

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

import java.util.List;

@RestController
@Api(value = "pse",description = "DITAS privacy security blueprint evaluator")
@RequestMapping(value = "/v1/")
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
    public ResponseEntity<List<BlueprintRanking>> filterPolicies(@RequestBody  Request input) {
        return ResponseEntity.ok(evaluatorService.evaluateRequest(input));
    }


}
