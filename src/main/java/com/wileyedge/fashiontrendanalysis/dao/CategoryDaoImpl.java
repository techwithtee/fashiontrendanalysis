package com.wileyedge.fashiontrendanalysis.dao;

import com.wileyedge.fashiontrendanalysis.exceptions.CustomUncheckedException;
import com.wileyedge.fashiontrendanalysis.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the CategoryDAO interface, providing CRUD operations
 * for the Category entity using JDBC.
 */
@Repository
public class CategoryDaoImpl implements CategoryDao {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor that takes a JdbcTemplate as a parameter.
     *
     * @param jdbcTemplate the JdbcTemplate to be used for JDBC operations
     */
    @Autowired
    public CategoryDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * RowMapper implementation for the Category entity.
     * This mapper maps a row of the result set to a Category object.
     *
     * @param rs the result set from which the current row will be mapped
     * @param rowNum the number of the current row being mapped
     * @return a Category object with its fields set to the values from the current row of the result set
     */
    private final RowMapper<Category> categoryRowMapper = (rs, rowNum) -> {
        Category category = new Category();
        category.setCategoryId(rs.getLong("category_id"));
        category.setCategoryName(rs.getString("category_name"));
        return category;
    };

    /**
     * Retrieves all categories from the database.
     *
     * @return a list of all categories, or an empty list if none are found
     */
    @Override
    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM category";
        return jdbcTemplate.query(sql, categoryRowMapper);
    }

    /**
     * Retrieves a specific category by its ID.
     *
     * @param categoryId the ID of the category to retrieve
     * @return the category with the specified ID, or null if not found
     */
    @Override
    public Category getCategoryById(Long categoryId) {
        String sql = "SELECT * FROM category WHERE category_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{categoryId}, categoryRowMapper);
    }

    /**
     * Adds a new category to the database.
     *
     * @param category the category to be added
     * @return the ID of the newly added category, or null if the operation failed
     */
    @Override
    public Long addCategory(Category category) {
        String sql = "INSERT INTO category (category_name) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"category_id"});
                ps.setString(1, category.getCategoryName());
                return ps;
            }, keyHolder);
        } catch (DataAccessException e) {
            throw new CustomUncheckedException("Failed to add category", e);
        }
        return (Long) keyHolder.getKey();
    }

    /**
     * Updates an existing category in the database.
     *
     * @param categoryId the category with updated details
     * @param category
     * @return true if the update was successful, false otherwise
     */
    @Override
    public boolean updateCategory(Long categoryId, Category category) {
        String sql = "UPDATE category SET category_name = ? WHERE category_id = ?";
        int updated = jdbcTemplate.update(sql, category.getCategoryName(), categoryId);
        return updated > 0;
    }

    /**
     * Deletes a specific category from the database using its ID.
     *
     * @param categoryId the ID of the category to delete
     * @return true if the deletion was successful, false otherwise
     */
    @Override
    public boolean deleteCategory(Long categoryId) {
        String sql = "DELETE FROM category WHERE category_id = ?";
        int deleted = jdbcTemplate.update(sql, categoryId);
        return deleted > 0;
    }


    /**
     * Retrieves categories associated with a specific trend.
     *
     * @param trendId the ID of the trend
     * @return a list of categories associated with the specified trend, or an empty list if none are found
     */
    @Override
    public List<Category> getCategoriesByTrend(Long trendId) {
        String sql = "SELECT c.* FROM category c " +
                "JOIN trend_category tc ON c.category_id = tc.category_id " +
                "WHERE tc.trend_id = ?";
        return jdbcTemplate.query(sql, new Object[]{trendId}, categoryRowMapper);
    }

    /**
     * Retrieves categories associated with a specific product.
     *
     * @param productId the ID of the product
     * @return a list of categories associated with the specified product, or an empty list if none are found
     */
    @Override
    public List<Category> getCategoriesByProduct(Long productId) {
        String sql = "SELECT c.* FROM category c " +
                "JOIN product p ON c.category_id = p.category_id " +
                "WHERE p.product_id = ?";
        return jdbcTemplate.query(sql, new Object[]{productId}, categoryRowMapper);
    }

    /**
     * Sets the popularity score of a specific category for a given season.
     * If an entry already exists for the given category and season, it updates the score.
     * Otherwise, it creates a new entry with the provided score.
     *
     * @param categoryId the ID of the category
     * @param season the name of the season (e.g., "Spring", "Summer", etc.)
     * @param score the popularity score to set for the category for the specified season
     */
    @Override
    public void setCategoryPopularityForSeason(Long categoryId, String season, int score) {
        String sql = "INSERT INTO category_popularity (category_id, season, popularity_score) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE popularity_score = ?";
        jdbcTemplate.update(sql, categoryId, season, score, score);
    }

    /**
     * Retrieves the popularity score of a specific category for a given season.
     *
     * @param categoryId the ID of the category
     * @param season the name of the season (e.g., "Spring", "Summer", etc.)
     * @return the popularity score of the category for the specified season, or null if not found
     */
    @Override
    public Integer getCategoryPopularityForSeason(Long categoryId, String season) {
        String sql = "SELECT popularity_score FROM category_popularity WHERE category_id = ? AND season = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, categoryId, season);
    }

    /**
     * Retrieves all popularity scores for a specific category across different seasons.
     *
     * @param categoryId the ID of the category
     * @return a list of popularity scores for the category across different seasons, or an empty list if none are found
     */
    @Override
    public List<Integer> getAllCategoryPopularities(Long categoryId) {
        String sql = "SELECT popularity_score FROM category_popularity WHERE category_id = ?";
        return jdbcTemplate.query(sql, new Object[]{categoryId}, (rs, rowNum) -> rs.getInt("popularity_score"));
    }

    /**
     * Fetches the popularity scores for a given category across various seasons.
     *
     * The results are ordered based on the sequence of seasons: Spring, Summer, Fall, and Winter.
     *
     * @param categoryId The ID of the category.
     * @return A list of maps, where each map contains a season and its corresponding popularity score.
     */
    @Override
    public List<Map<String, Object>> getCategoryPopularityOverview(Long categoryId) {
        String sql = "SELECT season, popularity_score as score FROM category_popularity WHERE category_id = ? ORDER BY FIELD(season, 'Spring', 'Summer', 'Fall', 'Winter')";
        return jdbcTemplate.query(sql, new Object[]{categoryId}, (rs, rowNum) -> {
            Map<String, Object> data = new HashMap<>();
            data.put("season", rs.getString("season"));
            data.put("score", rs.getInt("score"));
            return data;
        });
    }

}
