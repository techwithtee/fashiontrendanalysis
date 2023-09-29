package com.wileyedge.fashiontrendanalysis.dao;

import com.wileyedge.fashiontrendanalysis.model.Trend;

import java.util.List;

/**
 * Interface for operations related to the Trend entity in the database.
 */
public interface TrendDao {

    /**
     * Retrieves all trends from the database.
     *
     * @return a list of all trends
     */
    List<Trend> getAllTrends();

    /**
     * Retrieves a specific trend by its ID.
     *
     * @param trendId the ID of the trend to retrieve
     * @return the trend with the specified ID, or null if not found
     */
    Trend getTrendById(Long trendId);

    /**
     * Adds a new trend to the database.
     *
     * @param trend the trend to add
     * @return the ID of the newly created trend
     */
    Long addTrend(Trend trend);

    /**
     * Updates an existing trend in the database by its ID.
     *
     * @param trendId the ID of the trend to update
     * @param trend   the trend object with updated details
     * @return true if the trend was updated successfully, false otherwise
     */
    boolean updateTrend(Long trendId, Trend trend);

    /**
     * Removes a specific trend by its ID from the database.
     *
     * @param trendId the ID of the trend to delete
     * @return true if the trend was deleted successfully, false otherwise
     */
    boolean deleteTrend(Long trendId);

    /**
     * Associates a trend with a specific category.
     *
     * @param trendId    the ID of the trend
     * @param categoryId the ID of the category
     * @return true if the association was successful, false otherwise
     */
    boolean associateTrendWithCategory(Long trendId, Long categoryId);

    /**
     * Removes the association of a trend from a specific category.
     *
     * @param trendId    the ID of the trend
     * @param categoryId the ID of the category
     * @return true if the dissociation was successful, false otherwise
     */
    boolean dissociateTrendFromCategory(Long trendId, Long categoryId);

    /**
     * Retrieves trends based on a specific category.
     *
     * @param categoryId the ID of the category
     * @return a list of trends associated with the category
     */
    List<Trend> getTrendsByCategory(Long categoryId);

    /**
     * Retrieves trends based on a specific designer.
     *
     * @param designerId the ID of the designer
     * @return a list of trends associated with the designer
     */
    List<Trend> getTrendsByDesigner(Long designerId);

    /**
     * Retrieves trends based on a specific location.
     *
     * @param location the location of the trend
     * @return a list of trends associated with the location
     */
    List<Trend> getTrendsByLocation(String location);

    /**
     * Retrieves trends based on a specific season.
     *
     * @param season the season of the trend
     * @return a list of trends associated with the season
     */
    List<Trend> getTrendsBySeason(String season);

    /**
     * Sets the popularity score for a specific trend.
     *
     * @param trendId the ID of the trend
     * @param score   the popularity score to set
     * @return true if the popularity was set successfully, false otherwise
     */
    boolean setTrendPopularity(Long trendId, int score);

    /**
     * Retrieves the popularity score of a specific trend.
     *
     * @param trendId the ID of the trend
     * @return the popularity score of the trend
     */
    int getTrendPopularity(Long trendId);
}
