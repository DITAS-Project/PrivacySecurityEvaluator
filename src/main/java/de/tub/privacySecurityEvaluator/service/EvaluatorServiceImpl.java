package de.tub.privacySecurityEvaluator.service;


import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.tub.privacySecurityEvaluator.model.BlueprintRanking;
import de.tub.privacySecurityEvaluator.model.Request;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluatorServiceImpl implements EvaluatorService {

    @Override
    public List<BlueprintRanking> evaluateRequest(Request request) {
        List<BlueprintRanking> ret = new ArrayList<>();
        BlueprintRanking br1 = new BlueprintRanking();
        BlueprintRanking br2 = new BlueprintRanking();
//        br2.setScore(0.7);
//        br1.setScore(0.9);
//        br1.setBlueprint(new Property("1", "Encryption AES 128", "Encryption", Arrays.asList(createPropertyObject("Algorithm", "enum").put("value", "AES"),
//                createPropertyObject("Keylength", "number").put("minimum", 128))));
//        br2.setBlueprint(new Property("2", "Encryption AES 256", "Encryption", Arrays.asList(createPropertyObject("Algorithm", "enum").put("value", "AES"),
//                createPropertyObject("Keylength", "number").put("minimum", 256))));
//        ret.add(br1);
//        ret.add(br2);
        return ret;
    }

    private ObjectNode createPropertyObject(String name, String unit) {
        JsonNodeFactory factory = JsonNodeFactory.instance;
        return factory.objectNode().put("name", name).put("unit", unit);
    }
}
