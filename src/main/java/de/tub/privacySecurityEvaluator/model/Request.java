/*
 * Copyright 2018 Information Systems Engineering, TU Berlin, Germany
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at    http://www.apache.org/licenses/LICENSE-2.0Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.This is being developed for the DITAS Project: https://www.ditas-project.eu/
 */

package de.tub.privacySecurityEvaluator.model;

import java.util.List;

/**
 * Input data format
 */
public class Request {
    private Feature requirement;
    private List<Feature> blueprintAttributes;

    public Request(Feature requirement, List<Feature> blueprintAttributes) {
        this.requirement = requirement;
        this.blueprintAttributes = blueprintAttributes;
    }

    public Request() {
    }

    public Feature getRequirement() {
        return requirement;
    }

    public void setRequirement(Feature requirement) {
        this.requirement = requirement;
    }

    public List<Feature> getBlueprintAttributes() {
        return blueprintAttributes;
    }

    public void setBlueprintAttributes(List<Feature> bluePrintMetrics) {
        this.blueprintAttributes = bluePrintMetrics;
    }


}

