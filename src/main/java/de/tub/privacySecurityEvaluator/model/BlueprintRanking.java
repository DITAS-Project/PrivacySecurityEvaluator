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

/**
 * class to hold blueprint and a score between 0.0 and 1.0
 */

public class BlueprintRanking {
    private Feature blueprint;
    private double score;

    public BlueprintRanking(Feature blueprint, double score) {
        this.blueprint = blueprint;
        this.score = score;
    }

    public BlueprintRanking() {
    }

    public Feature getBlueprint() {
        return blueprint;
    }

    public void setBlueprint(Feature blueprint) {
        this.blueprint = blueprint;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
