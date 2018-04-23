package de.tub.privacySecurityEvaluator.model;

public abstract class Property {
    private String name;
    private String unit;

    public Property() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public abstract boolean validate(Property field);
}
