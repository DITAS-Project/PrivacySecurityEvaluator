package de.tub.privacySecurityEvaluator.model.fields;

import com.fasterxml.jackson.annotation.JsonSetter;
import de.tub.privacySecurityEvaluator.model.Property;

/**
 * KeylengthField
 */
public class KeylengthField extends Property {


    public KeylengthField() {
    }

    public KeylengthField(String unit, int value) {
        super(unit);
        this.value = value;
    }

    private int value;

    public int getValue() {
        return value;
    }

    @JsonSetter("value")
    public void setValue(int value) {
        this.value = value;
    }

    @JsonSetter("minimum")
    public void setMinimum(int value) {
        this.value = value;
    }

    @Override
    public boolean validate(Property field) {
        return field instanceof KeylengthField && ((KeylengthField) field).getValue() <= value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeylengthField that = (KeylengthField) o;

        return value == that.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
