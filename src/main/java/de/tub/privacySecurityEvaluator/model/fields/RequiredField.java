package de.tub.privacySecurityEvaluator.model.fields;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.util.GraphDeserializer;
import de.tub.privacySecurityEvaluator.util.PropertyDeserializer;

/**
 * RequiredField
 **/
public class RequiredField extends Property {

    private boolean value;

    @Override
    public boolean validate(Property field) {
        return false;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequiredField that = (RequiredField) o;

        return value == that.value;
    }

    @Override
    public int hashCode() {
        return (value ? 1 : 0);
    }
}
