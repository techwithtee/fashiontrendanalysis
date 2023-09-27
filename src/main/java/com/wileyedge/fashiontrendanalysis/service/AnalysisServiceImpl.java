package com.wileyedge.fashiontrendanalysis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnalysisServiceImpl implements AnalysisService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AnalysisServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Map<String, Double> calculateCategoryPopularityBySeason(String season) {
        String sql = "SELECT c.category_name, AVG(cp.popularity_score) AS avg_score " +
                "FROM category c " +
                "LEFT JOIN category_popularity cp ON c.category_id = cp.category_id " +
                "WHERE cp.season = ? " +
                "GROUP BY c.category_name";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, season);

        return rows.stream()
                .collect(Collectors.toMap(
                        row -> (String) row.get("category_name"),
                        row -> ((Number) row.get("avg_score")).doubleValue()
                ));
    }

    @Override
    public Map<String, Double> calculateDesignerPopularity() {
        String sql = "SELECT d.designer_name, AVG(d.popularity_score) AS avg_score " +
                "FROM designer d " +
                "GROUP BY d.designer_name";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        return rows.stream()
                .collect(Collectors.toMap(
                        row -> (String) row.get("designer_name"),
                        row -> ((Number) row.get("avg_score")).doubleValue()
                ));
    }

    @Override
    public Map<String, Double> calculateProductPopularity() {
        String sql = "SELECT p.product_name, AVG(pp.popularity_score) AS avg_score " +
                "FROM product p " +
                "LEFT JOIN product_popularity pp ON p.product_id = pp.product_id " +
                "GROUP BY p.product_name";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        return rows.stream()
                .collect(Collectors.toMap(
                        row -> (String) row.get("product_name"),
                        row -> ((Number) row.get("avg_score")).doubleValue()
                ));
    }

    @Override
    public Map<String, Double> calculateTrendPopularity() {
        String sql = "SELECT t.trend_name, AVG(tp.popularity_score) AS avg_score " +
                "FROM trend t " +
                "LEFT JOIN trend_popularity tp ON t.trend_id = tp.trend_id " +
                "GROUP BY t.trend_name";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        return rows.stream()
                .collect(Collectors.toMap(
                        row -> (String) row.get("trend_name"),
                        row -> ((Number) row.get("avg_score")).doubleValue()
                ));
    }

    @Override
    public Map<String, Double> calculateProductPopularityByCategory() {
        String sql = "SELECT c.category_name, AVG(pp.popularity_score) AS avg_score " +
                "FROM category c " +
                "LEFT JOIN product p ON c.category_id = p.category_id " +
                "LEFT JOIN product_popularity pp ON p.product_id = pp.product_id " +
                "GROUP BY c.category_name";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        // Process the query result and convert it into a map
        Map<String, Double> productPopularityByCategoryMap = new HashMap<>();
        for (Map<String, Object> row : rows) {
            String categoryName = (String) row.get("category_name");
            Double avgScore = (Double) row.get("avg_score");
            productPopularityByCategoryMap.put(categoryName, avgScore);
        }

        return productPopularityByCategoryMap;
    }

    @Override
    public Map<String, Double> calculateTrendPopularityByCategory() {
        String sql = "SELECT c.category_name, AVG(tp.popularity_score) AS avg_score " +
                "FROM category c " +
                "LEFT JOIN trend_category tc ON c.category_id = tc.category_id " +
                "LEFT JOIN trend_popularity tp ON tc.trend_id = tp.trend_id " +
                "GROUP BY c.category_name";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        // Process the query result and convert it into a map
        Map<String, Double> trendPopularityByCategoryMap = new HashMap<>();
        for (Map<String, Object> row : rows) {
            String categoryName = (String) row.get("category_name");
            Double avgScore = (Double) row.get("avg_score");
            trendPopularityByCategoryMap.put(categoryName, avgScore);
        }

        return trendPopularityByCategoryMap;
    }

    @Override
    public Map<String, Double> calculateTrendPopularityBySeason(String season) {
        String sql = "SELECT t.trend_name, AVG(tp.popularity_score) AS avg_score " +
                "FROM trend t " +
                "LEFT JOIN trend_popularity tp ON t.trend_id = tp.trend_id " +
                "WHERE t.season = ? " +
                "GROUP BY t.trend_name";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, season);

        // Process the query result and convert it into a map
        Map<String, Double> trendPopularityBySeasonMap = new HashMap<>();
        for (Map<String, Object> row : rows) {
            String trendName = (String) row.get("trend_name");
            Double avgScore = (Double) row.get("avg_score");
            trendPopularityBySeasonMap.put(trendName, avgScore);
        }

        return trendPopularityBySeasonMap;
    }

}
