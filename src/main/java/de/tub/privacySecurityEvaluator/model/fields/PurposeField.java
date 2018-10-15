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
public class PurposeField extends Property<Graph<String, DefaultEdge>> {
    @JsonIgnore
    private Graph<String, DefaultEdge> value;
    @JsonIgnore
    private String root;
    @JsonSerialize
    @JsonProperty("value")
    private final HashMap<String, String[]> purpose;

    public PurposeField(Graph<String, DefaultEdge> value, HashMap<String, String[]> purpose) {
        this.value = value;
        this.purpose = purpose;
    }

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

}
