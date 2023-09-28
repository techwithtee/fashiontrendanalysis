package com.wileyedge.fashiontrendanalysis.service;

import com.wileyedge.fashiontrendanalysis.dao.TrendDao;
import com.wileyedge.fashiontrendanalysis.model.Trend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
/**
 * Implementation of the TrendService interface.
 * This class provides concrete implementations for managing trends in the application.
 */
public class TrendServiceImpl implements TrendService {

    private final TrendDao trendDao;

    /**
     * Constructs a new TrendServiceImpl instance and injects the associated TrendDao.
     *
     * @param trendDao DAO responsible for Trend entity CRUD operations.
     */
    @Autowired
    public TrendServiceImpl(TrendDao trendDao) {
        this.trendDao = trendDao;
    }

    /**
     * Fetches all trends from the database.
     *
     * @return A list containing all trends or an empty list if no trends are found.
     */
    @Override
    public List<Trend> getAllTrends() {
        return trendDao.getAllTrends();
    }

    /**
     * Retrieves a specific trend from the database based on its unique identifier.
     *
     * @param trendId The unique identifier of the desired trend.
     * @return The trend corresponding to the provided ID or null if it doesn't exist.
     */
    @Override
    public Trend getTrendById(Long trendId) {
        return trendDao.getTrendById(trendId);
    }

    /**
     * Adds a new trend to the database.
     *
     * @param trend The trend entity to be stored.
     * @return The ID assigned to the newly added trend or null if the addition failed.
     */
    @Override
    public Long addTrend(Trend trend) {
        return trendDao.addTrend(trend);
    }

    /**
     * Modifies an existing trend's details in the database.
     *
     * @param trendId The ID of the trend to be updated.
     * @param trend The trend entity containing the updated details.
     * @return A boolean indicating if the update was successful.
     */
    @Override
    public boolean updateTrend(Long trendId, Trend trend) {
        return trendDao.updateTrend(trendId, trend);
    }

    /**
     * Deletes a trend from the database based on its unique identifier.
     *
     * @param trendId The unique identifier of the trend to be removed.
     * @return A boolean indicating if the deletion was successful.
     */
    @Override
    public boolean deleteTrend(Long trendId) {
        return trendDao.deleteTrend(trendId);
    }

    /**
     * Fetches trends associated with a specific category.
     *
     * @param categoryId The unique identifier of the category.
     * @return A list of trends associated with the given category.
     */
    @Override
    public List<Trend> getTrendsByCategory(Long categoryId) {
        return trendDao.getTrendsByCategory(categoryId);
    }

    /**
     * Retrieves trends associated with a specific designer.
     *
     * @param designerId The unique identifier of the designer.
     * @return A list of trends associated with the provided designer.
     */
    @Override
    public List<Trend> getTrendsByDesigner(Long designerId) {
        return trendDao.getTrendsByDesigner(designerId);
    }

    /**
     * Fetches trends originating or popular in a specific location.
     *
     * @param location The location (city, country, etc.) to filter by.
     * @return A list of trends popular or originating from the provided location.
     */
    @Override
    public List<Trend> getTrendsByLocation(String location) {
        return trendDao.getTrendsByLocation(location);
    }

    /**
     * Retrieves trends based on their popularity in a given season.
     *
     * @param season The season (e.g., "Summer", "Winter", etc.) to filter by.
     * @return A list of trends popular during the provided season.
     */
    @Override
    public List<Trend> getTrendsBySeason(String season) {
        return trendDao.getTrendsBySeason(season);
    }

    /**
     * Associates a trend with a specific category in the database.
     *
     * @param trendId The unique identifier of the trend.
     * @param categoryId The unique identifier of the category.
     * @return A boolean indicating if the association was successful.
     */
    @Override
    public boolean associateTrendWithCategory(Long trendId, Long categoryId) {
        return trendDao.associateTrendWithCategory(trendId, categoryId);
    }

    /**
     * Dissociates a trend from a specific category in the database.
     *
     * @param trendId The unique identifier of the trend.
     * @param categoryId The unique identifier of the category.
     * @return A boolean indicating if the dissociation was successful.
     */
    @Override
    public boolean dissociateTrendFromCategory(Long trendId, Long categoryId) {
        return trendDao.dissociateTrendFromCategory(trendId, categoryId);
    }

    /**
     * Sets the popularity score of a specific trend in the database within a transaction.
     *
     * @param trendId The unique identifier of the trend.
     * @param score The popularity score to be set.
     * @return A boolean indicating if the score setting was successful.
     */
    @Override
    @Transactional
    public boolean setTrendPopularity(Long trendId, int score) {
        return trendDao.setTrendPopularity(trendId, score);
    }

    /**
     * Fetches the popularity score of a specific trend from the database.
     *
     * @param trendId The unique identifier of the trend.
     * @return The popularity score of the provided trend.
     */
    @Override
    public int getTrendPopularity(Long trendId) {
        return trendDao.getTrendPopularity(trendId);
    }

}


