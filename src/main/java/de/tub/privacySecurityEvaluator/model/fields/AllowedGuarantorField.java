package de.tub.privacySecurityEvaluator.model.fields;

import de.tub.privacySecurityEvaluator.model.Property;

import java.util.Arrays;
import java.util.HashSet;

/**
 * AllowedGuarantorField
 **/
public class AllowedGuarantorField extends Property {

    private String[] value;

    @Override
    public boolean validate(Property field) {
        if (!(field instanceof AllowedGuarantorField)) {
            return false;
        }
        HashSet expected = new HashSet<String>(Arrays.asList(((AllowedGuarantorField) field).value));

        for (String guarantor : value) {
            if(expected.contains(guarantor)){
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

        AllowedGuarantorField that = (AllowedGuarantorField) o;

        return Arrays.equals(value,((AllowedGuarantorField) o).value);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(value);
    }
}
