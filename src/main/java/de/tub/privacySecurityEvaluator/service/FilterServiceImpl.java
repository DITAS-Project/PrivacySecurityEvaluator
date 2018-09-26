package de.tub.privacySecurityEvaluator.service;

import de.tub.privacySecurityEvaluator.model.Feature;
import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.Request;
import de.tub.privacySecurityEvaluator.model.fields.AvailablePurposeField;
import de.tub.privacySecurityEvaluator.model.fields.PurposeField;
import de.tub.privacySecurityEvaluator.model.strategies.ValidationStrategy;
import de.tub.privacySecurityEvaluator.model.strategies.validation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FilterServiceImpl implements FilterService {

    private static final Logger logger = LoggerFactory.getLogger(FilterServiceImpl.class);


    @Override
    public HashSet<Feature> filter(Request request) {

        StrategyBuilder.setDefaultValidation(request.getBlueprintAttributes());

        Feature requirement= request.getRequirement();
        List<Feature> blueprints= request.getBlueprintAttributes();

        logger.debug("begin filtering of {}",requirement);
        HashSet<Feature> filteredSubset = new HashSet<>();
        Set<? extends Class<? extends Property>> classes = requirement.getProperties().values().stream().map(Property::getClass).collect(Collectors.toSet());
        logger.debug("match set of {}",classes.stream().map(f->((Class) f).getCanonicalName()).toArray());

        for (Feature blueprint : blueprints) {
            boolean valid = false;
            for (Map.Entry<String, Property> property : blueprint.getProperties().entrySet()) {
                if(property.getValue().getClass().equals(PurposeField.class) && classes.contains(AvailablePurposeField.class)){
                    valid = true;
                    break;
                }
                else if (!classes.contains(property.getValue().getClass())) {
                    valid = false;
                    logger.debug("mismatch due to {} - {}",property.getKey(),property.getValue().getClass().getName());
                    break;
                }
                valid = true;
            }
            if(valid&&validate(requirement,blueprint)) {
                filteredSubset.add(blueprint);
            }else{
                logger.debug("feature {} was invalid",blueprint.getId());
            }

        }

        return filteredSubset;
    }


    private boolean validate(Feature requirement, Feature blueprint){
        for (Map.Entry<String, Property> req : requirement.getProperties().entrySet()) {
            boolean fullfilled = false;

            //search for the right fieldtype and validate
            for (Map.Entry<String, Property> property : blueprint.getProperties().entrySet()) {

                Class<? extends Property> propClass = property.getValue().getClass();
                Class<? extends Property> reqClass = req.getValue().getClass();
                if (propClass.equals(reqClass) ||
                        (reqClass.equals(AvailablePurposeField.class)&&propClass.equals(PurposeField.class))) {
                    if (property.getValue().validate(req.getValue())) {
                        fullfilled = true;
                        break;
                    }
                }
            }
            if (!fullfilled) {
                return false;
            }
        }
        return true;


}


}
