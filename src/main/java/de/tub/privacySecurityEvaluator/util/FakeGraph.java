package de.tub.privacySecurityEvaluator.util;

import java.util.HashMap;

public class FakeGraph {
    String unit;
    HashMap<String, String[]> value;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public HashMap<String, String[]> getValue() { return value;    }

    public void setValue(HashMap<String, String[]> value) {
        this.value = value;
    }
}
