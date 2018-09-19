package de.tub.privacySecurityEvaluator.model.fields;

import com.fasterxml.jackson.annotation.JsonSetter;
import de.tub.privacySecurityEvaluator.model.Property;

/**
 * SamplerateField
 */
public class SamplerateField extends Property <Integer>{




    @JsonSetter("minimum")
    public void setMinimum(int value) {
        setValue(value);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SamplerateField that = (SamplerateField) o;

        return this.getValue().equals( that.getValue());
    }

    @Override
    public int hashCode() {
        return getValue().intValue();
    }
}
