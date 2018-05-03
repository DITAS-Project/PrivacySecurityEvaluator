package de.tub.privacySecurityEvaluator.model.fields;

import de.tub.privacySecurityEvaluator.model.Property;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richard on 02.05.2018.
 */
public class GuarantorField extends Property {
    public List<String> value;

    @Override
    public boolean validate(Property field) {
        if (!(field instanceof GuarantorField)) return false;
        List<String> workingList = new ArrayList<String>();
        workingList.addAll(value);
        workingList.retainAll(((GuarantorField) field).getValue());
        return !workingList.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GuarantorField that = (GuarantorField) o;

        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }
}
