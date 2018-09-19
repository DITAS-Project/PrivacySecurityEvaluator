package de.tub.privacySecurityEvaluator.model.strategies;

import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.ValidationStrategy;

import java.util.HashSet;

public class ContainsOnceStrategy implements ValidationStrategy<Property<HashSet<String>>> {
    @Override
    public boolean validate(Property<HashSet<String>> req, Property<HashSet<String>> blueprint) {
        HashSet expected = req.getValue();

        for (String guarantor : blueprint.getValue()) {
            if (expected.contains(guarantor)) {
                return true;
            }
        }

        return false;
    }
}
