package de.tub.privacySecurityEvaluator.model.strategies;

import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.ValidationStrategy;
import de.tub.privacySecurityEvaluator.model.fields.AvailablePurposeField;
import de.tub.privacySecurityEvaluator.model.fields.PurposeField;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.HashSet;

public class DefaultGraphStrategy implements ValidationStrategy<Property>{
    @Override
    public boolean validate(Property req, Property blueprint) {
        Graph<String,DefaultEdge> graph=((PurposeField)blueprint).getValue();
        HashSet<String> availablePurpose= ((AvailablePurposeField)req).getValue();
        for(String s: availablePurpose){
            if(graph.containsVertex(s))return true;
        }

        return false;
    }
}
