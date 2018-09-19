package de.tub.privacySecurityEvaluator.model.strategies;

import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.ValidationStrategy;
import de.tub.privacySecurityEvaluator.model.fields.CredentialsField;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ContainsAllStrategy implements ValidationStrategy<Property<HashSet<String>>> {


    @Override
    public boolean validate(Property<HashSet<String>> req, Property<HashSet<String>> blueprint) {
        List<String> workingList = new ArrayList<String>();
        workingList.addAll(blueprint.getValue());
        workingList.retainAll(req.getValue());
        return !workingList.isEmpty();
    }
}
