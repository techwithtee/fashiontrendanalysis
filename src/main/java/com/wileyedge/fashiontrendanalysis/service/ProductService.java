package com.wileyedge.fashiontrendanalysis.service;

import com.wileyedge.fashiontrendanalysis.model.Product;
import java.util.List;

/**
 * Defines the service operations for managing products.
 */
public interface ProductService {

    /**
     * Fetches all products from the database.
     *
     * @return List of all products, or an empty list if none found.
     */
    List<Product> getAllProducts();

    /**
     * Fetches a specific product using its unique ID.
     *
     * @param productId The ID of the desired product.
     * @return The Product with the provided ID or null if not found.
     */
    Product getProductById(Long productId);

    /**
     * Adds a new product entry to the database.
     *
     * @param product The product entity to be added.
     * @return The ID of the newly added product or null if the operation failed.
     */
    Long addProduct(Product product);

    /**
     * Updates details of an existing product based on its unique ID.
     *
     * @param productId Unique identifier of the product to be updated.
     * @param product The updated product entity.
     * @return true if the update was successful, false otherwise.
     */
    boolean updateProduct(Long productId, Product product);

    /**
     * Deletes a product from the database using its unique ID.
     *
     * @param productId The ID of the product to be deleted.
     * @return true if the deletion was successful, false otherwise.
     */
    boolean deleteProduct(Long productId);

    /**
     * Fetches a list of products associated with a specific designer.
     *
     * @param designerId The ID of the desired designer.
     * @return List of products associated with the provided designer ID.
     */
    List<Product> getProductsByDesigner(Long designerId);

    /**
     * Fetches a list of products associated with a specific category.
     *
     * @param categoryId The ID of the desired category.
     * @return List of products associated with the provided category ID.
     */
    List<Product> getProductsByCategory(Long categoryId);
}
