package de.tub.privacySecurityEvaluator.model.fields;

import de.tub.privacySecurityEvaluator.model.Property;

/**
 * Created by Richard on 18.04.2018.
 */
public class AlgorithmField extends Property {
    public String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean validate(Property field) {
        if (!(field instanceof AlgorithmField)) {
            return false;
        }
        return value.equals(((AlgorithmField) field).getValue());
    }
}
