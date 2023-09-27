package com.wileyedge.fashiontrendanalysis.service;

import com.wileyedge.fashiontrendanalysis.model.Designer;
import com.wileyedge.fashiontrendanalysis.model.Product;
import java.util.List;

/**
 * This interface outlines the service operations for managing products in the system.
 * It provides methods for retrieving, adding, updating, deleting, and associating products
 * with designers and categories.
 */
public interface ProductService {

    /**
     * Retrieves all products stored in the database.
     *
     * @return a list containing all available products; an empty list if none are found.
     */
    List<Product> getAllProducts();

    /**
     * Retrieves a specific product identified by its unique ID.
     *
     * @param productId the unique identifier of the target product.
     * @return the corresponding Product entity, or null if not found.
     */
    Product getProductById(Long productId);

    /**
     * Persists a new product entity into the database.
     *
     * @param product the product to be stored.
     * @return the unique identifier assigned to the newly stored product, or null if the addition failed.
     */
    Long addProduct(Product product);

    /**
     * Updates the information of a product identified by its unique ID.
     *
     * @param productId the unique identifier of the product to be updated.
     * @param product the modified Product entity containing the new details.
     * @return true if the update succeeded, false otherwise.
     */
    boolean updateProduct(Long productId, Product product);

    /**
     * Removes a product, identified by its unique ID, from the database.
     *
     * @param productId the unique identifier of the product to be deleted.
     * @return true if the product was successfully removed; false otherwise.
     */
    boolean deleteProduct(Long productId);

    /**
     * Retrieves products that are associated with a specific designer.
     *
     * @param designerId the unique identifier of the designer in question.
     * @return a list of products linked to the given designer; an empty list if none are found.
     */
    List<Product> getProductsByDesigner(Long designerId);

    /**
     * Retrieves products that are categorized under a specific category.
     *
     * @param categoryId the unique identifier of the category in question.
     * @return a list of products under the given category; an empty list if none are found.
     */
    List<Product> getProductsByCategory(Long categoryId);

    /**
     * Associates a product with a designer.
     *
     * @param designerId the unique identifier of the designer.
     * @param productId the unique identifier of the product.
     */
    void associateDesignerWithProduct(Long designerId, Long productId);

    /**
     * Removes the association between a product and a designer.
     *
     * @param designerId the unique identifier of the designer.
     * @param productId the unique identifier of the product.
     */
    void dissociateDesignerFromProduct(Long designerId, Long productId);

    /**
     * Retrieves a list of designers associated with a specific product.
     *
     * @param productId the unique identifier of the product.
     * @return a list of designers linked to the product; an empty list if none are found.
     */
    List<Designer> getDesignersForProduct(Long productId);

    /**
     * Sets a product's popularity score for a specific trend.
     *
     * @param productId the unique identifier of the product.
     * @param trendId the unique identifier of the trend.
     * @param score the popularity score to be set.
     */
    void setProductPopularityForTrend(Long productId, Long trendId, int score);

    /**
     * Retrieves the popularity score of a product for a specific trend.
     *
     * @param productId the unique identifier of the product.
     * @param trendId the unique identifier of the trend.
     * @return the popularity score of the product for the given trend.
     */
    Integer getProductPopularityForTrend(Long productId, Long trendId);

    /**
     * Retrieves all popularity scores associated with a product.
     *
     * @param productId the unique identifier of the product.
     * @return a list containing all the popularity scores for the product; an empty list if none are found.
     */
    List<Integer> getAllProductPopularities(Long productId);

}
