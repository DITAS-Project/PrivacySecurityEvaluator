package de.tub.privacySecurityEvaluator.model.fields;

import de.tub.privacySecurityEvaluator.model.Property;

import java.util.HashSet;

/**
 * AvailablePurposeField
 **/
public class AvailablePurposeField extends Property<HashSet<String>> {


    public AvailablePurposeField(){
        super();
    }

    public AvailablePurposeField(HashSet<String> value){
        setValue(value);
    }

}
