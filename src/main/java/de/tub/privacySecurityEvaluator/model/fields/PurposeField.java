package de.tub.privacySecurityEvaluator.model.fields;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.util.GraphDeserializer;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;


@JsonDeserialize(using = GraphDeserializer.class)
public class PurposeField extends Property{

    private Graph<String, DefaultEdge> value;

    @Override
    public boolean validate(Property field) {
        if(!(field instanceof AvailablePurposeField))return false;

        String[] availablePurpose= ((AvailablePurposeField)field).getValue();
        for(String s: availablePurpose){
            if(value.containsVertex(s))return true;
        }

        return false;

    }

    public Graph<String, DefaultEdge> getValue() {
        return value;
    }

    public void setValue(Graph<String, DefaultEdge> value) {
        this.value = value;
    }
}
