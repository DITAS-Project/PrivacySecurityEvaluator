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

package de.tub.privacySecurityEvaluator.model.strategies.validation;

import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.strategies.ValidationStrategy;
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
