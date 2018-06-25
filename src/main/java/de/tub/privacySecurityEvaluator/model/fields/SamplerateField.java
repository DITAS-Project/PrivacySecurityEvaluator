package de.tub.privacySecurityEvaluator.model.fields;

import de.tub.privacySecurityEvaluator.model.Property;

/**
 * Created by Richard on 18.04.2018.
 */
public class SamplerateField extends Property {
    public int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
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