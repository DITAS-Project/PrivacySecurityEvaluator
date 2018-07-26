package de.tub.privacySecurityEvaluator.model.fields;

import de.tub.privacySecurityEvaluator.model.Property;

/**
 * AnnouncementAddressField
 */
public class AnnouncementAddressField extends Property {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean validate(Property field) {
        if (!(field instanceof AnnouncementAddressField)) return false;
        return ((AnnouncementAddressField) field).getValue().equals(value);

    }
}
