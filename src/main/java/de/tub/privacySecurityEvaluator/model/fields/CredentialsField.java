package de.tub.privacySecurityEvaluator.model.fields;

import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.Rankabale;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * CredentialsField
 */
public class CredentialsField extends Property<HashSet<String>> {
    /**
    @Override
    public boolean validate(Property field) {
        if (!(field instanceof CredentialsField)) return false;
        List<String> workingList = new ArrayList<String>();
        workingList.addAll(value);
        workingList.retainAll(((CredentialsField) field).getValue());
        return !workingList.isEmpty();
    }
*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CredentialsField that = (CredentialsField) o;

        return getValue() != null ? getValue().equals(that.getValue()) : that.getValue() == null;
    }

    @Override
    public int hashCode() {
        return getValue() != null ? getValue().hashCode() : 0;
    }
/**
    @Override
    public double rank(Property requirement) {
        if (!(requirement instanceof CredentialsField)) {
            return 0;
        }
        HashSet<String> req = new HashSet<>(((CredentialsField) requirement).value);

        HashSet<String> intersection = new HashSet<>(value);
        HashSet<String> union = new HashSet<>(value);
        union.addAll(req);
        intersection.retainAll(req);

        return (double) intersection.size()/union.size();    } */
}
