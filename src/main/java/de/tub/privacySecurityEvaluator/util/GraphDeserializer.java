/*
 * Copyright 2018 Information Systems Engineering, TU Berlin, Germany
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at    http://www.apache.org/licenses/LICENSE-2.0Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.This is being developed for the DITAS Project: https://www.ditas-project.eu/
 */

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
        FakeGraph fakeGraphObject= codec.readValue(jsonParser,FakeGraph.class);
        HashMap<String, String[]> fakeGraph= fakeGraphObject.value;
        Graph<String,DefaultEdge> purposeGraph= new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);

        //add all vertexes
        for(String s:fakeGraph.keySet())purposeGraph.addVertex(s);

        //add all edges
        for(Map.Entry<String, String[]> entry:fakeGraph.entrySet()){
            int length = entry.getValue().length;
            if(length <1)continue;
            for(int i = 0; i< length; i++){
               purposeGraph.addEdge(entry.getKey(),entry.getValue()[i] );
            }
        }

        PurposeField ret= new PurposeField(purposeGraph, fakeGraph);
        String root = findRoot(purposeGraph);
        ret.setRoot(root);
        ret.setUnit(fakeGraphObject.unit);
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
