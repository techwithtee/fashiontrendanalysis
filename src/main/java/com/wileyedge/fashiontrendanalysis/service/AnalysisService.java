package com.wileyedge.fashiontrendanalysis.service;

import java.util.List;
import java.util.Map;

public interface AnalysisService {

    /**
     * Calculates the popularity of categories for a given season.
     *
     * @param season The season for which the popularity is calculated.
     * @return A map where the key is the category name and the value is the calculated popularity score.
     */
    Map<String, Double> calculateCategoryPopularityBySeason(String season);

    /**
     * Calculates the overall popularity of designers.
     *
     * @return A map where the key is the designer name and the value is the calculated popularity score.
     */
    Map<String, Double> calculateDesignerPopularity();

    /**
     * Calculates the overall popularity of products.
     *
     * @return A map where the key is the product name and the value is the calculated popularity score.
     */
    Map<String, Double> calculateProductPopularity();

    /**
     * Calculates the overall popularity of trends.
     *
     * @return A map where the key is the trend name and the value is the calculated popularity score.
     */
    Map<String, Double> calculateTrendPopularity();

    /**
     * Calculates the product popularity based on categories.
     *
     * @return A map where the key is the category name and the value is the calculated popularity score for products.
     */
    Map<String, Double> calculateProductPopularityByCategory();

    /**
     * Calculates the trend popularity based on categories.
     *
     * @return A map where the key is the category name and the value is the calculated popularity score for trends.
     */
    Map<String, Double> calculateTrendPopularityByCategory();

    /**
     * Calculates the popularity of trends for a given season.
     *
     * @param season The season for which the popularity is calculated.
     * @return A map where the key is the trend name and the value is the calculated popularity score.
     */
    Map<String, Double> calculateTrendPopularityBySeason(String season);

    // More analysis methods can be added here as needed
}
