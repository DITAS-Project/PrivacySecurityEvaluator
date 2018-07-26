package de.tub.privacySecurityEvaluator.model.fields;

import de.tub.privacySecurityEvaluator.model.Property;

import java.util.ArrayList;
import java.util.List;

/**
 * CredentialsField
 */
public class CredentialsField extends Property {
    private List<String> value;

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }

    @Override
    public boolean validate(Property field) {
        if (!(field instanceof CredentialsField)) return false;
        List<String> workingList = new ArrayList<String>();
        workingList.addAll(value);
        workingList.retainAll(((CredentialsField) field).getValue());
        return !workingList.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CredentialsField that = (CredentialsField) o;

        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
