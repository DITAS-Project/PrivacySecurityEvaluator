package de.tub.privacySecurityEvaluator.model.fields;

import de.tub.privacySecurityEvaluator.model.Property;

import java.util.List;

/**
 * Created by Richard on 23.04.2018.
 */
public class CredentialsField extends Property {
    public List<String> value;

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }

    @Override
    public boolean validate(Property field) {
        if (!(field instanceof CredentialsField)) return false;
        return true;
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
