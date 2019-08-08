package de.tub.privacySecurityEvaluator.model.strategies.validation;

import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.strategies.ValidationStrategy;


import java.util.List;

public class ListContainsOnceStrategy implements ValidationStrategy<Property<List<String>>> {
    @Override
    public boolean validate(Property<List<String>> req, Property<List<String>> blueprint) {
        List<String> expected= req.getValue();

        for(String s: blueprint.getValue()){
            if(expected.contains(s)){
                return true;
            }
        }
        return false;
    }
}
