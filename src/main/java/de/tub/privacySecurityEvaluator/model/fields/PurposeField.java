package de.tub.privacySecurityEvaluator.model.fields;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.Rankabale;
import de.tub.privacySecurityEvaluator.util.GraphDeserializer;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;

import java.util.HashMap;
import java.util.HashSet;


@JsonDeserialize(using = GraphDeserializer.class)
public class PurposeField extends Property<Graph<String,DefaultEdge>> {
    @JsonIgnore
    private Graph<String, DefaultEdge> value;
    @JsonIgnore
    private String root;
@JsonSerialize
@JsonProperty("value")
private final HashMap<String, String []> purpose;

    public PurposeField (Graph<String, DefaultEdge> value, HashMap<String,String[]> purpose){
        this.value=value;
        this.purpose=purpose;
    }

   /** @Override
    public boolean validate(Property field) {
        System.out.println(field);
        if(!(field instanceof AvailablePurposeField))return false;

        HashSet<String> availablePurpose= ((AvailablePurposeField)field).getValue();
        for(String s: availablePurpose){
            if(value.containsVertex(s))return true;
        }

        return false;

    }
*/
    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public Graph<String, DefaultEdge> getValue() {
        return value;
    }

    public void setValue(Graph<String, DefaultEdge> value) {
        this.value = value;
    }
/**
    public double rank(Property requirement) {
        if(!(requirement instanceof AvailablePurposeField))return 0;
        if(root==null)return 0;
        DijkstraShortestPath<String, DefaultEdge> dijkstraAlg= new DijkstraShortestPath<>(value);
        HashSet<String> availablePurpose= ((AvailablePurposeField)requirement).getValue();
        double rank= 0;
        for(String s: availablePurpose){
            if(value.containsVertex(s)){
                int pathLen = dijkstraAlg.getPath(root, s).getLength();
                if(rank< pathLen)rank= pathLen;
            }
        }

      return rank;
    }*/
}
