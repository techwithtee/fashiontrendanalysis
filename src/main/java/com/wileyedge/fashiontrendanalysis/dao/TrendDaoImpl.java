package com.wileyedge.fashiontrendanalysis.dao;

import com.wileyedge.fashiontrendanalysis.model.Trend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of the TrendDao interface, providing CRUD operations
 * for the Trend entity using JDBC.
 */
@Repository
public class TrendDaoImpl implements TrendDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Trend> rowMapper;

    /**
     * Constructor to autowire and initialize the JdbcTemplate dependency.
     *
     * @param jdbcTemplate Object to interact with the database.
     */
    @Autowired
    public TrendDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = (rs, rowNum) -> {
            Trend trend = new Trend();
            trend.setTrendId(rs.getLong("trend_id"));
            trend.setTrendName(rs.getString("trend_name"));
            trend.setTrendDesc(rs.getString("trend_desc"));
            trend.setCategoryId(rs.getLong("category_id"));
            trend.setDesignerId(rs.getLong("designer_id"));
            trend.setLocation(rs.getString("location"));
            trend.setSeason(rs.getString("season"));
            return trend;
        };
    }

    /**
     * Retrieves all trends from the database.
     *
     * @return a list of all trends, or an empty list if no trends are found
     */
    @Override
    public List<Trend> getAllTrends() {
        String query = "SELECT * FROM trend";
        return jdbcTemplate.query(query, rowMapper);
    }

    /**
     * Retrieves a specific trend by its ID.
     *
     * @param trendId the ID of the trend to retrieve
     * @return the trend with the specified ID, or null if not found
     */
    @Override
    public Trend getTrendById(Long trendId) {
        String query = "SELECT * FROM trend WHERE trend_id=?";
        return jdbcTemplate.queryForObject(query, new Object[]{trendId}, rowMapper);
    }

    /**
     * Adds a new trend to the database and returns its ID.
     *
     * @param trend the trend object to add
     * @return the ID of the newly created trend
     */
    @Override
    public Long addTrend(Trend trend) {
        String query = "INSERT INTO trend (trend_name, trend_desc, category_id, designer_id, location, season) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, trend.getTrendName(), trend.getTrendDesc(), trend.getCategoryId(), trend.getDesignerId(), trend.getLocation(), trend.getSeason());
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
    }

    /**
     * Updates details of an existing trend in the database.
     *
     * @param trendId the ID of the trend to update
     * @param trend the updated trend object
     * @return true if the update was successful, false otherwise
     */
    @Override
    public boolean updateTrend(Long trendId, Trend trend) {
        String query = "UPDATE trend SET trend_name=?, trend_desc=?, category_id=?, designer_id=?, location=?, season=? WHERE trend_id=?";
        int updated =  jdbcTemplate.update(query, trend.getTrendName(), trend.getTrendDesc(), trend.getCategoryId(), trend.getDesignerId(), trend.getLocation(), trend.getSeason(), trendId);
        return updated > 0;
    }

    /**
     * Deletes a trend from the database.
     *
     * @param trendId the ID of the trend to delete
     * @return true if the deletion was successful, false otherwise
     */
    @Override
    public boolean deleteTrend(Long trendId) {
        String query = "DELETE FROM trend WHERE trend_id=?";
        return jdbcTemplate.update(query, trendId) > 0;
    }

    /**
     * Retrieves trends associated with a specific category.
     *
     * @param categoryId the ID of the category for which to retrieve trends
     * @return a list of trends associated with the category, or an empty list if none are found
     */

    @Override
    public List<Trend> getTrendsByCategory(Long categoryId) {
        String query = "SELECT * FROM trend WHERE category_id=?";
        return jdbcTemplate.query(query, rowMapper, categoryId);
    }

    /**
     * Retrieves trends designed by a specific designer.
     *
     * @param designerId the ID of the designer for which to retrieve trends
     * @return a list of trends associated with the designer, or an empty list if none are found
     */
    @Override
    public List<Trend> getTrendsByDesigner(Long designerId) {
        String query = "SELECT * FROM trend WHERE designer_id=?";
        return jdbcTemplate.query(query, rowMapper, designerId);
    }

    /**
     * Retrieves trends based on a specific location.
     *
     * @param location the location for which to retrieve trends
     * @return a list of trends associated with the location, or an empty list if none are found
     */
    @Override
    public List<Trend> getTrendsByLocation(String location) {
        String query = "SELECT * FROM trend WHERE location=?";
        return jdbcTemplate.query(query, rowMapper, location);
    }

    /**
     * Retrieves trends based on a specific season.
     *
     * @param season the season for which to retrieve trends
     * @return a list of trends associated with the season, or an empty list if none are found
     */
    @Override
    public List<Trend> getTrendsBySeason(String season) {
        String query = "SELECT * FROM trend WHERE season=?";
        return jdbcTemplate.query(query, rowMapper, season);
    }

    /**
     * Retrieves the popularity score of a specific trend.
     *
     * @param trendId the ID of the trend for which to retrieve the popularity score
     * @return the popularity score of the trend
     */
    @Override

    public Integer getTrendPopularityScore(Long trendId) {
        String query = "SELECT popularity_score FROM trend_popularity WHERE trend_id=?";
        return jdbcTemplate.queryForObject(query, Integer.class, trendId);
    }

    /**
     * Calculates and returns the popularity score of a specific trend.
     *
     * @param trendId the ID of the trend for which to calculate the popularity score
     * @return the calculated popularity score of the trend
     */
    @Override
    public double calculatePopularityScore(Long trendId) {
        // Query to get the popularity score for the specified trend from the trend_popularity table
        String query = "SELECT popularity_score FROM trend_popularity WHERE trend_id = ?";

        try {
            // Execute the query and retrieve the popularity score as a Double
            Double popularityScore = jdbcTemplate.queryForObject(query, Double.class, trendId);

            if (popularityScore != null) {
                return popularityScore; // Return the retrieved popularity score
            } else {
                return 0.0; // Return 0 if no popularity score is found
            }
        } catch (DataAccessException e) {
            // Handle any exceptions that may occur during database query
            // You can log the error or throw a custom exception as needed
            e.printStackTrace();
            return 0.0; // Return 0 in case of an error
        }
    }

    /**
     * Records the popularity score for a specific trend.
     *
     * @param trendId the ID of the trend for which to record the popularity score
     * @param popularityScore the popularity score to record
     */
    @Override
    public void recordPopularityScore(Long trendId, int popularityScore) {
        String insertQuery = "INSERT INTO trend_popularity (trend_id, popularity_score) VALUES (?, ?)";
        jdbcTemplate.update(insertQuery, trendId, popularityScore);
    }

    /**
     * Associates a trend with a specific category.
     *
     * @param trendId the ID of the trend to associate
     * @param categoryId the ID of the category with which to associate the trend
     * @return true if the association was successful, false otherwise
     */
    @Override
    public boolean associateTrendWithCategory(Long trendId, Long categoryId) {
        String query = "INSERT INTO trend_category (trend_id, category_id) VALUES (?, ?)";
        return jdbcTemplate.update(query, trendId, categoryId) > 0;
    }

    /**
     * Dissociates a trend from a specific category.
     *
     * @param trendId the ID of the trend to dissociate
     * @param categoryId the ID of the category from which to dissociate the trend
     * @return true if the dissociation was successful, false otherwise
     */
    @Override
    public boolean dissociateTrendFromCategory(Long trendId, Long categoryId) {
        String query = "DELETE FROM trend_category WHERE trend_id=? AND category_id=?";
        return jdbcTemplate.update(query, trendId, categoryId) > 0;
    }
}
