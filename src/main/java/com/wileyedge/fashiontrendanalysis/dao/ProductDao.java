package com.wileyedge.fashiontrendanalysis.dao;

import com.wileyedge.fashiontrendanalysis.model.Designer;
import com.wileyedge.fashiontrendanalysis.model.Product;

import java.util.List;

public interface ProductDao {

    /**
     * Retrieves all products from the database.
     *
     * @return a list of all products, or an empty list if no products exist
     */
    List<Product> getAllProducts();

    /**
     * Retrieves a specific product by its ID.
     *
     * @param productId the ID of the product to retrieve
     * @return the product with the specified ID, or null if not found
     */
    Product getProductById(Long productId);

    /**
     * Adds a new product to the database.
     *
     * @param product the product to add
     * @return the ID of the newly created product, or null if the addition failed
     */
    Long addProduct(Product product);

    /**
     * Updates an existing product in the database by its ID.
     *
     * @param productId the ID of the product to update
     * @param product the product object with updated details
     * @return true if the product was updated successfully, false otherwise
     */
    boolean updateProduct(Long productId, Product product);

    /**
     * Removes a specific product by its ID from the database.
     *
     * @param productId the ID of the product to delete
     * @return true if the product was deleted successfully, false otherwise
     */
    boolean deleteProduct(Long productId);

    /**
     * Retrieves products based on a specific designer.
     *
     * @param designerId the ID of the designer whose products to retrieve
     * @return a list of products associated with the designer, or an empty list if no such products exist
     */
    List<Product> getProductsByDesigner(Long designerId);

    /**
     * Retrieves products based on a specific category.
     *
     * @param categoryId the ID of the category whose products to retrieve
     * @return a list of products associated with the category, or an empty list if no such products exist
     */
    List<Product> getProductsByCategory(Long categoryId);

    void associateDesignerWithProduct(Long designerId, Long productId);
    void dissociateDesignerFromProduct(Long designerId, Long productId);
    List<Designer> getDesignersForProduct(Long productId);
    void setProductPopularityForTrend(Long productId, Long trendId, int score);
    Integer getProductPopularityForTrend(Long productId, Long trendId);
    List<Integer> getAllProductPopularities(Long productId);

}
