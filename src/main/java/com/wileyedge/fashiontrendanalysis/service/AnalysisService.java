package com.wileyedge.fashiontrendanalysis.service;

import java.util.List;
import java.util.Map;

public interface AnalysisService {
    Map<String, Double> calculateCategoryPopularityBySeason(String season);

    Map<String, Double> calculateDesignerPopularity();

    Map<String, Double> calculateProductPopularity();

    Map<String, Double> calculateTrendPopularity();

    Map<String, Double> calculateProductPopularityByCategory();

    Map<String, Double> calculateTrendPopularityByCategory();

    Map<String, Double> calculateTrendPopularityBySeason(String season);

    // Add more analysis methods as needed
}
