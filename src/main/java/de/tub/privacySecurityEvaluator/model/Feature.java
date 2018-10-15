/*
 * Copyright 2018 Information Systems Engineering, TU Berlin, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *                       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * This is being developed for the DITAS Project: https://www.ditas-project.eu/
 */

package de.tub.privacySecurityEvaluator.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.tub.privacySecurityEvaluator.util.PropertyDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Data representation of a blueprint/requirement
 * holds data according to the specified json format file
 */

@JsonDeserialize(using = PropertyDeserializer.class)
public class Feature {
    private String id;
    private String type;
    private String description;
    private Map<String, Property> properties;


    private static final Logger logger = LoggerFactory.getLogger(Feature.class);

    public Feature(String id, String type, Map<String, Property> properties, String description) {
        this.id = id;
        this.description = description;

        this.type = type;
        this.properties = properties;
    }

    public Feature() {
        this.properties = new HashMap<>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Property> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Property> properties) {
        this.properties = properties;
    }

    public void addProperty(Property field, String name) {
        this.properties.put(name, field);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feature feature = (Feature) o;

        if (getId() != null ? !getId().equals(feature.getId()) : feature.getId() != null) return false;
        if (getDescription() != null ? !getDescription().equals(feature.getDescription()) : feature.getDescription() != null)
            return false;
        if (getType() != null ? !getType().equals(feature.getType()) : feature.getType() != null) return false;
        return getProperties() != null ? getProperties().equals(feature.getProperties()) : feature.getProperties() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getProperties() != null ? getProperties().hashCode() : 0);
        return result;
    }


}
