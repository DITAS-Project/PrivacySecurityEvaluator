package de.tub.privacySecurityEvaluator.model.fields;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.util.GraphDeserializer;
import de.tub.privacySecurityEvaluator.util.PropertyDeserializer;

/**
 * RequiredField
 **/
public class RequiredField extends Property<Boolean> {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequiredField that = (RequiredField) o;

        return getValue() == that.getValue();
    }

    @Override
    public int hashCode() {
        return (getValue() ? 1 : 0);
    }
}
