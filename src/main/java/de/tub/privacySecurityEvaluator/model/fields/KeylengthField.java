package de.tub.privacySecurityEvaluator.model.fields;

import de.tub.privacySecurityEvaluator.model.Propertiefield;

/**
 * Created by Richard on 18.04.2018.
 */
public class KeylengthField extends Propertiefield {
    public int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
