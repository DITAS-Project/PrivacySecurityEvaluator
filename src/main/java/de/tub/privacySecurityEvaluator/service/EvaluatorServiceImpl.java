package de.tub.privacySecurityEvaluator.service;


import com.sun.deploy.cache.CachedJarFile14;
import de.tub.privacySecurityEvaluator.model.BlueprintRanking;
import de.tub.privacySecurityEvaluator.model.Feature;
import de.tub.privacySecurityEvaluator.model.Property;
import de.tub.privacySecurityEvaluator.model.Request;
import de.tub.privacySecurityEvaluator.model.fields.PurposeField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EvaluatorServiceImpl implements EvaluatorService {
    private static final Logger logger = LoggerFactory.getLogger(EvaluatorServiceImpl.class);

    private RankingService rankingService;

    private FilterService filterService;

    @Autowired
    public void setFilterService(FilterService filterService) { this.filterService = filterService; }

    @Autowired
    public void setRankingService(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @Override
    public List<BlueprintRanking> evaluateRequest(Request request) {

        HashSet<Feature> validSubset = filterService.filter(request);

        return rankingService.rank(request.getRequirement(), validSubset);
    }

}
