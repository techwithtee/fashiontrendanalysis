package com.wileyedge.fashiontrendanalysis.dao;

import com.wileyedge.fashiontrendanalysis.model.Designer;
import com.wileyedge.fashiontrendanalysis.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;


import java.util.List;

/**
 * Implementation of the ProductDao interface using JDBC.
 * This class provides CRUD operations for the Product entity.
 */
@Repository
public class ProductDaoImpl implements ProductDao {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor to autowire the JdbcTemplate dependency.
     *
     * @param jdbcTemplate Object to interact with the database.
     */
    @Autowired
    public ProductDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * RowMapper implementation for the Product entity.
     * This mapper maps a row of the result set to a Product object.
     *
     * @param rs the result set from which the current row will be mapped
     * @param rowNum the number of the current row being mapped
     * @return a Product object with its fields set to the values from the current row of the result set
     */
    private final RowMapper<Product> productRowMapper = (rs, rowNum) -> {
        return new Product(
                rs.getLong("product_id"),
                rs.getString("product_name"),
                rs.getLong("category_id"),
                rs.getLong("designer_id"),
                rs.getString("product_description")
        );
    };

    // RowMapper for Designer
    private final RowMapper<Designer> designerRowMapper = (rs, rowNum) -> {
        Designer designer = new Designer();
        designer.setDesignerId(rs.getLong("designer_id"));
        designer.setDesignerName(rs.getString("designer_name"));
        designer.setDesignerLocation(rs.getString("designer_location"));
        designer.setTrendCount(rs.getInt("trend_count"));
        designer.setPopularityScore(rs.getInt("popularity_score"));
        return designer;
    };

    /**
     * Retrieves all products from the database.
     *
     * @return a list of all products, or an empty list if no products exist
     */
    @Override
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, productRowMapper);
    }

    /**
     * Retrieves a specific product by its ID.
     *
     * @param productId the ID of the product to retrieve
     * @return the product with the specified ID, or null if not found
     */
    @Override
    public Product getProductById(Long productId) {
        String sql = "SELECT * FROM product WHERE product_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{productId}, productRowMapper);
    }

    /**
     * Adds a new product to the database.
     *
     * @param product the product to add
     * @return the ID of the newly created product, or null if the addition failed
     */
    @Override
    public Long addProduct(Product product) {
        String sql = "INSERT INTO product (product_name, category_id, designer_id, product_description) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getProductName(), product.getCategoryId(), product.getDesignerId(), product.getProductDescription());
        // Assuming auto-increment primary key, retrieve the last inserted ID.
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
    }

    /**
     * Updates an existing product in the database by its ID.
     *
     * @param productId the ID of the product to update
     * @param product   the product object with updated details
     * @return true if the product was updated successfully, false otherwise
     */
    @Override
    public boolean updateProduct(Long productId, Product product) {
        String sql = "UPDATE product SET product_name = ?, category_id = ?, designer_id = ?, product_description = ? WHERE product_id = ?";
        int updated = jdbcTemplate.update(sql, product.getProductName(), product.getCategoryId(), product.getDesignerId(), product.getProductDescription(), productId);
        return updated > 0;
    }

    /**
     * Removes a specific product by its ID from the database.
     *
     * @param productId the ID of the product to delete
     * @return true if the product was deleted successfully, false otherwise
     */
    @Override
    public boolean deleteProduct(Long productId) {
        String sql = "DELETE FROM product WHERE product_id = ?";
        int deleted = jdbcTemplate.update(sql, productId);
        return deleted > 0;
    }

    /**
     * Retrieves products based on a specific designer.
     *
     * @param designerId the ID of the designer whose products to retrieve
     * @return a list of products associated with the designer, or an empty list if no such products exist
     */
    @Override
    public List<Product> getProductsByDesigner(Long designerId) {
        String sql = "SELECT * FROM product WHERE designer_id = ?";
        return jdbcTemplate.query(sql, new Object[]{designerId}, productRowMapper);
    }

    /**
     * Retrieves products based on a specific category.
     *
     * @param categoryId the ID of the category whose products to retrieve
     * @return a list of products associated with the category, or an empty list if no such products exist
     */
    @Override
    public List<Product> getProductsByCategory(Long categoryId) {
        String sql = "SELECT * FROM product WHERE category_id = ?";
        return jdbcTemplate.query(sql, new Object[]{categoryId}, productRowMapper);
    }

    /**
     * Associates a specific designer with a product in the database.
     * This creates a link between the designer and the product, indicating that the designer has designed the product.
     *
     * @param designerId the unique identifier for the designer
     * @param productId the unique identifier for the product
     */
    @Override
    public void associateDesignerWithProduct(Long designerId, Long productId) {
        String query = "INSERT INTO product_designer_association (product_id, designer_id) VALUES (?, ?)";
        jdbcTemplate.update(query, productId, designerId);
    }

    /**
     * Removes the association between a specific designer and a product.
     *
     * @param designerId the unique identifier for the designer
     * @param productId the unique identifier for the product
     */
    @Override
    public void dissociateDesignerFromProduct(Long designerId, Long productId) {
        String query = "DELETE FROM product_designer_association WHERE product_id=? AND designer_id=?";
        jdbcTemplate.update(query, productId, designerId);
    }

    /**
     * Retrieves all designers associated with a particular product.
     *
     * @param productId the unique identifier for the product
     * @return a list of designers who have designed the given product.
     */
    @Override
    public List<Designer> getDesignersForProduct(Long productId) {
        String query = "SELECT * FROM designers WHERE designer_id IN (SELECT designer_id FROM product_designer_association WHERE product_id=?)";
        return jdbcTemplate.query(query, designerRowMapper, productId);
    }

    /**
     * Sets a popularity score for a product for a specific trend.
     *
     * @param productId the unique identifier for the product
     * @param trendId the unique identifier for the trend
     * @param score the popularity score to be set
     */
    @Override
    public void setProductPopularityForTrend(Long productId, Long trendId, int score) {
        String sql = "INSERT INTO product_popularity (product_id, trend_id, popularity_score) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE popularity_score = ?";
        jdbcTemplate.update(sql, productId, trendId, score, score);
    }

    /**
     * Fetches the popularity score of a product for a particular trend.
     *
     * @param productId the unique identifier for the product
     * @param trendId the unique identifier for the trend
     * @return the popularity score of the product for the given trend.
     */
    @Override
    public Integer getProductPopularityForTrend(Long productId, Long trendId) {
        String sql = "SELECT popularity_score FROM product_popularity WHERE product_id = ? AND trend_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, productId, trendId);
    }

    /**
     * Retrieves all popularity scores for a specific product.
     *
     * @param productId the unique identifier for the product
     * @return a list of all popularity scores for the given product across different trends.
     */
    @Override
    public List<Integer> getAllProductPopularities(Long productId) {
        String sql = "SELECT popularity_score FROM product_popularity WHERE product_id = ?";
        return jdbcTemplate.query(sql, new Object[]{productId}, (rs, rowNum) -> rs.getInt("popularity_score"));
    }
}
