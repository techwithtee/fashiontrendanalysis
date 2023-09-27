package com.wileyedge.fashiontrendanalysis.dao;

import com.wileyedge.fashiontrendanalysis.model.Designer;
import com.wileyedge.fashiontrendanalysis.model.Product;

import java.util.List;

public interface ProductDao {

    /**
     * Fetches all products present in the database.
     *
     * @return a list containing all products, or an empty list if none exist.
     */
    List<Product> getAllProducts();

    /**
     * Retrieves a particular product based on its ID.
     *
     * @param productId the unique identifier for the product
     * @return the corresponding product object or null if it doesn't exist.
     */
    Product getProductById(Long productId);

    /**
     * Adds a new product entry to the database.
     *
     * @param product the product object to be added
     * @return the ID assigned to the newly created product or null if the operation fails.
     */
    Long addProduct(Product product);

    /**
     * Updates details of an existing product in the database.
     *
     * @param productId the unique identifier for the product to be updated
     * @param product the product object containing updated details
     * @return true if the operation succeeds, false otherwise.
     */
    boolean updateProduct(Long productId, Product product);

    /**
     * Deletes a product entry from the database based on its ID.
     *
     * @param productId the unique identifier for the product to be deleted
     * @return true if the product was deleted successfully, false otherwise.
     */
    boolean deleteProduct(Long productId);

    /**
     * Fetches all products associated with a specific designer.
     *
     * @param designerId the unique identifier for the designer
     * @return a list of products related to the given designer or an empty list if none found.
     */
    List<Product> getProductsByDesigner(Long designerId);

    /**
     * Retrieves all products under a specific category.
     *
     * @param categoryId the unique identifier for the category
     * @return a list of products under the given category or an empty list if none found.
     */
    List<Product> getProductsByCategory(Long categoryId);

    /**
     * Associates a designer with a product, indicating that the designer has designed the product.
     *
     * @param designerId the unique identifier for the designer
     * @param productId the unique identifier for the product
     */
    void associateDesignerWithProduct(Long designerId, Long productId);

    /**
     * Removes the association between a designer and a product.
     *
     * @param designerId the unique identifier for the designer
     * @param productId the unique identifier for the product
     */
    void dissociateDesignerFromProduct(Long designerId, Long productId);

    /**
     * Fetches all designers associated with a particular product.
     *
     * @param productId the unique identifier for the product
     * @return a list of designers who have designed the given product.
     */
    List<Designer> getDesignersForProduct(Long productId);

    /**
     * Sets a popularity score for a product for a specific trend.
     *
     * @param productId the unique identifier for the product
     * @param trendId the unique identifier for the trend
     * @param score the popularity score to be set
     */
    void setProductPopularityForTrend(Long productId, Long trendId, int score);

    /**
     * Fetches the popularity score of a product for a particular trend.
     *
     * @param productId the unique identifier for the product
     * @param trendId the unique identifier for the trend
     * @return the popularity score of the product for the given trend.
     */
    Integer getProductPopularityForTrend(Long productId, Long trendId);

    /**
     * Retrieves all popularity scores for a specific product.
     *
     * @param productId the unique identifier for the product
     * @return a list of all popularity scores for the given product across different trends.
     */
    List<Integer> getAllProductPopularities(Long productId);

}
