package de.tub.privacySecurityEvaluator.model.fields;

import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.Rankabale;

import java.util.Arrays;
import java.util.HashSet;

/**
 * AllowedGuarantorField
 **/
public class AllowedGuarantorField extends Property implements Rankabale {

    private HashSet<String> value;

    @Override
    public boolean validate(Property field) {
        if (!(field instanceof AllowedGuarantorField)) {
            return false;
        }
        HashSet expected = ((AllowedGuarantorField) field).value;

        for (String guarantor : value) {
            if (expected.contains(guarantor)) {
                return true;
            }
        }

        return false;
    }

    public HashSet<String> getValue() {
        return value;
    }

    public void setValue(HashSet<String> value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;


        return value.equals(((AllowedGuarantorField) o).value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public double rank(Property requirement) {
        if (!(requirement instanceof AllowedGuarantorField)) {
            return 0;
        }
        HashSet<String> req = ((AllowedGuarantorField) requirement).value;

        HashSet<String> intersection = new HashSet<>(value);
        HashSet<String> union = new HashSet<>(value);
        union.addAll(req);
        intersection.retainAll(req);
        return (double) intersection.size()/union.size();
    }
}
