package de.tub.privacySecurityEvaluator.model.fields;

import de.tub.privacySecurityEvaluator.model.Propertiefield;

/**
 * Created by Richard on 18.04.2018.
 */
public class VersionField extends Propertiefield {
    public String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}