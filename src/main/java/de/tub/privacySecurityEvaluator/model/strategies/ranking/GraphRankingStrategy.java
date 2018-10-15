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

package de.tub.privacySecurityEvaluator.model.strategies.ranking;

import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.fields.AvailablePurposeField;
import de.tub.privacySecurityEvaluator.model.fields.PurposeField;
import de.tub.privacySecurityEvaluator.model.strategies.RankingStrategy;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;

import java.util.HashSet;

public class GraphRankingStrategy implements RankingStrategy<Property> {
    @Override
    public double rank(Property req, Property blueprint) {

        if(!(req instanceof AvailablePurposeField)||!(blueprint instanceof PurposeField))return 0;

        String root = ((PurposeField) blueprint).getRoot();
        if(root ==null)return 0;
        Graph<String, DefaultEdge> value = ((PurposeField)blueprint).getValue();
        DijkstraShortestPath<String, DefaultEdge> dijkstraAlg= new DijkstraShortestPath<>(value);
        HashSet<String> availablePurpose= ((AvailablePurposeField)req).getValue();
        double rank= 0;
        for(String s: availablePurpose){
            if(value.containsVertex(s)){
                int pathLen = dijkstraAlg.getPath(root, s).getLength();
                if(rank< pathLen)rank= pathLen;

            }
        }
        return rank;
    }

}
