package de.tub.privacySecurityEvaluator.model;

public abstract class Property {

    private String unit;

    public Property() {
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public abstract boolean validate(Property field);
}
