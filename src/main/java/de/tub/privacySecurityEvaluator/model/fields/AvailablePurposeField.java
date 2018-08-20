package de.tub.privacySecurityEvaluator.model.fields;

import de.tub.privacySecurityEvaluator.model.Property;

import java.util.Arrays;
import java.util.HashSet;

/**
 * AvailablePurposeField
 **/
public class AvailablePurposeField extends Property {

    private String[] value;

    @Override
    public boolean validate(Property field) {
        if (!(field instanceof AvailablePurposeField)) {
            return false;
        }
        HashSet expected = new HashSet<String>(Arrays.asList(((AvailablePurposeField) field).value));

        for (String guarantor : value) {
            if (expected.contains(guarantor)) {
                return true;
            }
        }
        return false;
    }

    public String[] getValue() {
        return value;
    }

    public void setValue(String[] value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AvailablePurposeField that = (AvailablePurposeField) o;

        return Arrays.equals(value,((AvailablePurposeField) o).value);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(value);
    }
}
