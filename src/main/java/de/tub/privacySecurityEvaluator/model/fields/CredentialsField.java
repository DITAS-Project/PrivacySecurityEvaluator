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
}
