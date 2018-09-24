package de.tub.privacySecurityEvaluator.model.fields;

import com.fasterxml.jackson.annotation.JsonSetter;
import de.tub.privacySecurityEvaluator.model.Property;

/**
 * KeylengthField
 */
public class KeylengthField extends Property<Integer> {

public KeylengthField(){
    super();
}

    public KeylengthField(String unit, int value) {
        super(unit);
      setValue(value);
    }


    @JsonSetter("minimum")
    public void setMinimum(int value) {
        setValue(value);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeylengthField that = (KeylengthField) o;

        return getValue() == that.getValue();
    }

    @Override
    public int hashCode() {
        return getValue();
    }
}
