package com.wileyedge.fashiontrendanalysis.service;

import com.wileyedge.fashiontrendanalysis.model.Trend;

import java.util.List;

public interface TrendService {

    /**
     * Retrieves all trends from the database.
     *
     * @return A list of all trends or an empty list if none are found.
     */
    List<Trend> getAllTrends();

    /**
     * Fetches a specific trend based on its unique identifier.
     *
     * @param trendId The unique identifier of the desired trend.
     * @return The trend matching the provided ID or null if not found.
     */
    Trend getTrendById(Long trendId);

    /**
     * Adds a new trend to the database.
     *
     * @param trend The trend entity to be stored.
     * @return The ID of the newly added trend or null if the addition failed.
     */
    Long addTrend(Trend trend);

    /**
     * Updates an existing trend's details in the database.
     *
     * @param trendId The ID of the trend to be updated.
     * @param trend The trend entity containing the updated details.
     * @return A boolean indicating if the update was successful.
     */
    boolean updateTrend(Long trendId, Trend trend);

    /**
     * Removes a trend from the database based on its unique identifier.
     *
     * @param trendId The unique identifier of the trend to be deleted.
     * @return A boolean indicating if the deletion was successful.
     */
    boolean deleteTrend(Long trendId);

    /**
     * Associates a trend with a specific category in the database.
     *
     * @param trendId The unique identifier of the trend.
     * @param categoryId The unique identifier of the category.
     * @return A boolean indicating if the association was successful.
     */
    boolean associateTrendWithCategory(Long trendId, Long categoryId);

    /**
     * Dissociates a trend from a specific category in the database.
     *
     * @param trendId The unique identifier of the trend.
     * @param categoryId The unique identifier of the category.
     * @return A boolean indicating if the dissociation was successful.
     */
    boolean dissociateTrendFromCategory(Long trendId, Long categoryId);

    /**
     * Retrieves trends associated with a specific category.
     *
     * @param categoryId The unique identifier of the category.
     * @return A list of trends associated with the provided category.
     */
    List<Trend> getTrendsByCategory(Long categoryId);

    /**
     * Fetches trends associated with a specific designer.
     *
     * @param designerId The unique identifier of the designer.
     * @return A list of trends associated with the given designer.
     */
    List<Trend> getTrendsByDesigner(Long designerId);

    /**
     * Retrieves trends originating or popular in a specific location.
     *
     * @param location The location (city, country, etc.) to filter by.
     * @return A list of trends popular or originating from the provided location.
     */
    List<Trend> getTrendsByLocation(String location);

    /**
     * Fetches trends based on their popularity in a given season.
     *
     * @param season The season (e.g., "Summer", "Winter", etc.) to filter by.
     * @return A list of trends popular during the provided season.
     */
    List<Trend> getTrendsBySeason(String season);

    /**
     * Sets the popularity score of a specific trend.
     *
     * @param trendId The unique identifier of the trend.
     * @param score The popularity score to be set.
     * @return A boolean indicating if the score setting was successful.
     */
    boolean setTrendPopularity(Long trendId, int score);

    /**
     * Fetches the popularity score of a specific trend.
     *
     * @param trendId The unique identifier of the trend.
     * @return The popularity score of the provided trend.
     */
    int getTrendPopularity(Long trendId);
}

