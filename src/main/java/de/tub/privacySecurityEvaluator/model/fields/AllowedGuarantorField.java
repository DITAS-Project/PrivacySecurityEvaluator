package de.tub.privacySecurityEvaluator.model.fields;

import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.Rankabale;

import java.util.Arrays;
import java.util.HashSet;

/**
 * AllowedGuarantorField
 **/
public class AllowedGuarantorField extends Property<HashSet<String>>{


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;


        return getValue().equals(((AllowedGuarantorField) o).getValue());
    }

    @Override
    public int hashCode() {
        return getValue().hashCode();
    }


}
