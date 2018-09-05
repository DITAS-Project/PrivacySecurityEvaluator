package de.tub.privacySecurityEvaluator.model.fields;

import com.fasterxml.jackson.annotation.JsonSetter;
import de.tub.privacySecurityEvaluator.model.Property;

/**
 * SamplerateField
 */
public class SamplerateField extends Property {

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    @JsonSetter("minimum")
    public void setMinimum(int value) {
        this.value = value;
    }

    @Override
    public boolean validate(Property field) {
        if (!(field instanceof SamplerateField)) return false;
        return ((SamplerateField) field).getValue() <= value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SamplerateField that = (SamplerateField) o;

        return value == that.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
