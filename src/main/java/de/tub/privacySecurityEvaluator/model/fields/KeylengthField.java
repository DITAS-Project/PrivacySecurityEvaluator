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
    public int hashCode() {
        return getValue();
    }
}
