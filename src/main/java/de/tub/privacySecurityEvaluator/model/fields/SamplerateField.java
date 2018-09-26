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
    public int hashCode() {
        return getValue().intValue();
    }
}
