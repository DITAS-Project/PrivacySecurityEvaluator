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

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.tub.privacySecurityEvaluator.model.fields.AllowedGuarantorField;
import de.tub.privacySecurityEvaluator.model.strategies.RankingStrategy;
import de.tub.privacySecurityEvaluator.model.strategies.ValidationStrategy;

/**
 * Superclass for all fields
 * to implement add the value field and add validate
 */
public abstract class Property<T> {

    private String unit;

    private T value;
    @JsonIgnore
    private ValidationStrategy<Property<T>> valStrategy;
    @JsonIgnore
    private RankingStrategy<Property<T>> rankStrategy;


    public Property() {
    }

    public Property(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public ValidationStrategy<Property<T>> getValStrategy() {
        return valStrategy;
    }

    public void setValStrategy(ValidationStrategy<Property<T>> valStrategy) {
        this.valStrategy = valStrategy;
    }

    public RankingStrategy<Property<T>> getRankStrategy() {
        return rankStrategy;
    }

    public void setRankStrategy(RankingStrategy<Property<T>> rankStrategy) {
        this.rankStrategy = rankStrategy;
    }


    public boolean validate(Property req) {
        return valStrategy.validate(req, this);
    }

    public double rank(Property req) {
        return rankStrategy.rank(req, this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property that = (Property) o;

        return getValue() != null ? getValue().equals(that.getValue()) : that.getValue() == null;
    }


    @Override
    public int hashCode() {
        return getValue() != null ? getValue().hashCode() : 0;
    }

}
