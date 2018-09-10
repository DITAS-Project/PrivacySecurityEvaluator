package de.tub.privacySecurityEvaluator.model.fields;

import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.Rankabale;

import java.util.Arrays;
import java.util.HashSet;

/**
 * AvailablePurposeField
 **/
public class AvailablePurposeField extends Property implements Rankabale{

    private HashSet<String> value;

    public AvailablePurposeField(){
        super();
    }

    public AvailablePurposeField(String [] value){
        this.value= value;
    }

    @Override
    public boolean validate(Property field) {
        if (!(field instanceof AvailablePurposeField)) {
            return false;
        }
        HashSet expected = ((AvailablePurposeField) field).value;

        for (String purpose : value) {
            if (expected.contains(purpose)) {
                return true;
            }
        }
        return false;
    }

    public  HashSet<String> getValue() {
        return value;
    }

    public void setValue(HashSet<String>value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AvailablePurposeField that = (AvailablePurposeField) o;

        return value.equals(((AvailablePurposeField) o).value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public double rank(Property requirement) {
        if (!(requirement instanceof AvailablePurposeField)) {
            return 0;
        }
        HashSet<String> req = ((AvailablePurposeField) requirement).value;

        HashSet<String> intersection = new HashSet<>(value);
        HashSet<String> union = new HashSet<>(value);
        union.addAll(req);
        intersection.retainAll(req);

        return (double) intersection.size()/union.size();
    }
}
