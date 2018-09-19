package de.tub.privacySecurityEvaluator.model.fields;

import de.tub.privacySecurityEvaluator.model.Property;

/**
 * Algorithmfield
 */
public class AlgorithmField extends Property<String> {

    public AlgorithmField(){

    }

    public AlgorithmField(String unit,String value) {
        super(unit);
        setValue(value);
    }

}
