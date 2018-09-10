package de.tub.privacySecurityEvaluator.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import de.tub.privacySecurityEvaluator.model.fields.PurposeField;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GraphDeserializer extends StdDeserializer<PurposeField>{

    private ObjectMapper mapper;

    public GraphDeserializer() { this(null);}

    protected GraphDeserializer(Class<?> vc){
        super(vc);
        mapper=new ObjectMapper();
    }


    @Override
    public PurposeField deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec codec = jsonParser.getCodec();
        TypeReference<HashMap<String, String[]>> typeReference= new TypeReference<HashMap<String, String[]>>() {};
        HashMap<String, String[]> fakeGraph= codec.readValue(jsonParser,typeReference);
        Graph<String,DefaultEdge> purposeGraph= new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);

        //add all vertexes
        for(String s:fakeGraph.keySet())purposeGraph.addVertex(s);

        //add all edges
        for(Map.Entry<String, String[]> entry:fakeGraph.entrySet()){
            int length = entry.getValue().length;
            if(length <1)break;
            for(int i = 0; i< length; i++){
                purposeGraph.addEdge(entry.getKey(),entry.getValue()[i]);
            }
        }
        PurposeField ret= new PurposeField(purposeGraph);
        String root = findRoot(purposeGraph);
        ret.setRoot(root);
        return ret;

    }

    private String findRoot(Graph<String, DefaultEdge> graph){
        graph.vertexSet();
        for(String s: graph.vertexSet()){
            if(graph.incomingEdgesOf(s).size()==0) return s;
        }
        return null;
    }
}
