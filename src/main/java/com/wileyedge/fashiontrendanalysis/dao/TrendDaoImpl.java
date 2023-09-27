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
        return jdbcTemplate.queryForObject(query, new Object[]{trendId}, rowMapper);
    }

    @Override
    public Long addTrend(Trend trend) {
        String query = "INSERT INTO trend (trend_name, trend_desc, category_id, designer_id, location, season) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, trend.getTrendName(), trend.getTrendDesc(), trend.getCategoryId(), trend.getDesignerId(), trend.getLocation(), trend.getSeason());
        //return trend.getTrendId();
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
    }

    @Override
    public boolean updateTrend(Long trendId, Trend trend) {
        String query = "UPDATE trend SET trend_name=?, trend_desc=?, category_id=?, designer_id=?, location=?, season=? WHERE trend_id=?";
        int updated =  jdbcTemplate.update(query, trend.getTrendName(), trend.getTrendDesc(), trend.getCategoryId(), trend.getDesignerId(), trend.getLocation(), trend.getSeason(), trendId);
        return updated > 0;
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
    public boolean associateTrendWithCategory(Long trendId, Long categoryId) {
        String query = "INSERT INTO trend_category (trend_id, category_id) VALUES (?, ?)";
        return jdbcTemplate.update(query, trendId, categoryId) > 0;
    }

    @Override
    public boolean dissociateTrendFromCategory(Long trendId, Long categoryId) {
        String query = "DELETE FROM trend_category WHERE trend_id=? AND category_id=?";
        return jdbcTemplate.update(query, trendId, categoryId) > 0;
    }

    @Override
    public boolean setTrendPopularity(Long trendId, int score) {
        String query = "UPDATE trend_popularity SET popularity_score = ? WHERE trend_id = ?";
        int updated = jdbcTemplate.update(query, score, trendId);
        return updated > 0;
    }

    @Override
    public int getTrendPopularity(Long trendId) {
        String query = "SELECT popularity_score FROM trend_popularity WHERE trend_id = ?";
        try {
            Integer popularityScore = jdbcTemplate.queryForObject(query, Integer.class, trendId);
            return popularityScore != null ? popularityScore : 0;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return 0;
        }
    }




}
