package com.wileyedge.fashiontrendanalysis.dao;

import com.wileyedge.fashiontrendanalysis.model.Trend;
import java.util.List;

/**
 * This interface defines CRUD operations and other relevant methods for managing trends in the database.
 */
public interface TrendDao {

    /**
     * Retrieves all trends from the database.
     *
     * @return a list of all trends, or an empty list if no trends exist
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
     * @return the ID of the newly created trend, or null if the addition failed
     */
    Long addTrend(Trend trend);

    /**
     * Updates an existing trend in the database by its ID.
     *
     * @param trendId the ID of the trend to update
     * @param trend the trend object with updated details
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
     * Associates a specific trend with a category in the database.
     *
     * @param trendId the unique identifier for the trend
     * @param categoryId the unique identifier for the category
     * @return true if the association was successful, false otherwise
     */
    boolean associateTrendWithCategory(Long trendId, Long categoryId);

    /**
     * Removes the association of a specific trend with a category in the database.
     *
     * @param trendId the unique identifier for the trend
     * @param categoryId the unique identifier for the category
     * @return true if the dissociation was successful, false otherwise
     */
    boolean dissociateTrendFromCategory(Long trendId, Long categoryId);

    /**
     * Retrieves trends associated with a specific category.
     *
     * @param categoryId the ID of the category whose trends to retrieve
     * @return a list of trends associated with the category, or an empty list if none are found
     */
    List<Trend> getTrendsByCategory(Long categoryId);

    /**
     * Retrieves trends associated with a specific designer.
     *
     * @param designerId the ID of the designer whose trends to retrieve
     * @return a list of trends associated with the designer, or an empty list if none are found
     */
    List<Trend> getTrendsByDesigner(Long designerId);

    /**
     * Retrieves trends based on a specific location.
     *
     * @param location the location whose trends to retrieve
     * @return a list of trends from the specified location, or an empty list if none are found
     */
    List<Trend> getTrendsByLocation(String location);

    /**
     * Retrieves trends based on a specific season.
     *
     * @param season the season whose trends to retrieve
     * @return a list of trends from the specified season, or an empty list if none are found
     */
    List<Trend> getTrendsBySeason(String season);

    /**
     * Retrieves the popularity score of a specific trend.
     *
     * @param trendId the ID of the trend whose popularity score to retrieve
     * @return the popularity score of the trend
     */
    Integer getTrendPopularityScore(Long trendId);

    /**
     * Calculates the popularity score for a specific trend based on some internal metrics.
     *
     * @param trendId the ID of the trend for which the popularity score is to be calculated
     * @return the calculated popularity score for the trend
     */
    double calculatePopularityScore(Long trendId);

    /**
     * Records the popularity score for a specific trend.
     *
     * @param trendId the ID of the trend for which the popularity score is to be recorded
     * @param popularityScore the popularity score to be recorded for the trend
     */
    void recordPopularityScore(Long trendId, int popularityScore);
}
