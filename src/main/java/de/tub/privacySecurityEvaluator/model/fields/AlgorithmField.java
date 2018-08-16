package de.tub.privacySecurityEvaluator.model.fields;

import de.tub.privacySecurityEvaluator.model.Property;

/**
 * Algorithmfield
 */
public class AlgorithmField extends Property {
    private String value;


    public AlgorithmField(){

    }

    public AlgorithmField(String unit,String value) {
        super(unit);
        this.value = value;
    }

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
