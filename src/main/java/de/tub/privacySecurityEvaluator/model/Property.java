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
