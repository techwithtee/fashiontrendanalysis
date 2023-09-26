package com.wileyedge.fashiontrendanalysis.dao;

import com.wileyedge.fashiontrendanalysis.model.Trend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrendDaoImpl implements TrendDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Trend> rowMapper;

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
    @Override
    public List<Trend> getAllTrends() {
        String query = "SELECT * FROM trend";
        return jdbcTemplate.query(query, rowMapper);
    }

    @Override
    public Trend getTrendById(Long trendId) {
        String query = "SELECT * FROM trend WHERE trend_id=?";
        return jdbcTemplate.queryForObject(query, rowMapper, trendId);
    }

    @Override
    public Long addTrend(Trend trend) {
        String query = "INSERT INTO trend (trend_name, trend_desc, category_id, designer_id, location, season) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, trend.getTrendName(), trend.getTrendDesc(), trend.getCategoryId(), trend.getDesignerId(), trend.getLocation(), trend.getSeason());
        return trend.getTrendId();
    }

    @Override
    public boolean updateTrend(Long trendId, Trend trend) {
        String query = "UPDATE trend SET trend_name=?, trend_desc=?, category_id=?, designer_id=?, location=?, season=? WHERE trend_id=?";
        return jdbcTemplate.update(query, trend.getTrendName(), trend.getTrendDesc(), trend.getCategoryId(), trend.getDesignerId(), trend.getLocation(), trend.getSeason(), trendId) > 0;
    }

    @Override
    public boolean deleteTrend(Long trendId) {
        String query = "DELETE FROM trend WHERE trend_id=?";
        return jdbcTemplate.update(query, trendId) > 0;
    }

    @Override
    public List<Trend> getTrendsByCategory(Long categoryId) {
        String query = "SELECT * FROM trend WHERE category_id=?";
        return jdbcTemplate.query(query, rowMapper, categoryId);
    }

    @Override
    public List<Trend> getTrendsByDesigner(Long designerId) {
        String query = "SELECT * FROM trend WHERE designer_id=?";
        return jdbcTemplate.query(query, rowMapper, designerId);
    }

    @Override
    public List<Trend> getTrendsByLocation(String location) {
        String query = "SELECT * FROM trend WHERE location=?";
        return jdbcTemplate.query(query, rowMapper, location);
    }

    @Override
    public List<Trend> getTrendsBySeason(String season) {
        String query = "SELECT * FROM trend WHERE season=?";
        return jdbcTemplate.query(query, rowMapper, season);
    }

    @Override

    public Integer getTrendPopularityScore(Long trendId) {
        String query = "SELECT popularity_score FROM trend_popularity WHERE trend_id=?";
        return jdbcTemplate.queryForObject(query, Integer.class, trendId);
    }

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

    // Method to record the popularity score for a trend
    @Override
    public void recordPopularityScore(Long trendId, int popularityScore) {
        String insertQuery = "INSERT INTO trend_popularity (trend_id, popularity_score) VALUES (?, ?)";
        jdbcTemplate.update(insertQuery, trendId, popularityScore);
    }

    @Override
    public boolean associateTrendWithCategory(Long trendId, Long categoryId) {
        String query = "INSERT INTO trend_category (trend_id, category_id) VALUES (?, ?)";
        return jdbcTemplate.update(query, trendId, categoryId) > 0;
    }

    @Override
    public boolean dissociateTrendFromCategory(Long trendId, Long categoryId) {
        String query = "DELETE FROM trend_category WHERE trend_id=? AND category_id=?";
        return jdbcTemplate.update(query, trendId, categoryId) > 0;
    }
}
