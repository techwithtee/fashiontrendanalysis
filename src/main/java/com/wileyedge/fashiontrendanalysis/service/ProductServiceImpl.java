package com.wileyedge.fashiontrendanalysis.service;

import com.wileyedge.fashiontrendanalysis.dao.ProductDao;
import com.wileyedge.fashiontrendanalysis.model.Designer;
import com.wileyedge.fashiontrendanalysis.model.Product;
import com.wileyedge.fashiontrendanalysis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Implementation of the ProductService interface.
 * This class provides concrete implementations for managing products in the application.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    /**
     * Constructs a new ProductServiceImpl instance and injects the associated ProductDao.
     *
     * @param productDao DAO responsible for Product entity CRUD operations.
     */
    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    /**
     * Fetches all products from the database.
     *
     * @return A list containing all products or an empty list if no products are found.
     */
    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    /**
     * Retrieves a product from the database based on its unique identifier.
     *
     * @param productId The unique identifier of the desired product.
     * @return The product corresponding to the provided ID or null if it doesn't exist.
     */
    @Override
    public Product getProductById(Long productId) {
        return productDao.getProductById(productId);
    }

    /**
     * Adds a new product to the database.
     *
     * @param product The product entity to be stored.
     * @return The ID assigned to the newly added product or null if the addition failed.
     */
    @Override
    public Long addProduct(Product product) {
        return productDao.addProduct(product);
    }

    /**
     * Modifies an existing product's details in the database.
     *
     * @param productId The ID of the product to be updated.
     * @param product The product entity containing the updated details.
     * @return A boolean indicating if the update was successful.
     */
    @Override
    public boolean updateProduct(Long productId, Product product) {
        return productDao.updateProduct(productId, product);
    }

    /**
     * Deletes a product from the database based on its unique identifier.
     *
     * @param productId The unique identifier of the product to be removed.
     * @return A boolean indicating if the deletion was successful.
     */
    @Override
    public boolean deleteProduct(Long productId) {
        return productDao.deleteProduct(productId);
    }

    /**
     * Fetches products associated with a specific designer.
     *
     * @param designerId The unique identifier of the designer.
     * @return A list of products associated with the given designer.
     */
    @Override
    public List<Product> getProductsByDesigner(Long designerId) {
        return productDao.getProductsByDesigner(designerId);
    }

    /**
     * Retrieves products that fall under a specific category.
     *
     * @param categoryId The unique identifier of the category.
     * @return A list of products that belong to the provided category.
     */
    @Override
    public List<Product> getProductsByCategory(Long categoryId) {
        return productDao.getProductsByCategory(categoryId);
    }

    /**
     * Associates a designer with a product in the database.
     *
     * @param designerId The unique identifier of the designer.
     * @param productId The unique identifier of the product.
     */
    @Override
    public void associateDesignerWithProduct(Long designerId, Long productId) {
        productDao.associateDesignerWithProduct(designerId, productId);
    }

    /**
     * Dissociates a designer from a product in the database.
     *
     * @param designerId The unique identifier of the designer.
     * @param productId The unique identifier of the product.
     */
    @Override
    public void dissociateDesignerFromProduct(Long designerId, Long productId) {
        productDao.dissociateDesignerFromProduct(designerId, productId);
    }

    /**
     * Retrieves all designers associated with a specific product from the database.
     *
     * @param productId The unique identifier of the product.
     * @return A list of designers associated with the given product.
     */
    @Override
    public List<Designer> getDesignersForProduct(Long productId) {
        return productDao.getDesignersForProduct(productId);
    }

    /**
     * Sets the popularity score of a product for a specific trend in the database.
     *
     * @param productId The unique identifier of the product.
     * @param trendId The unique identifier of the trend.
     * @param score The popularity score to be set.
     */
    @Override
    public void setProductPopularityForTrend(Long productId, Long trendId, int score) {
        productDao.setProductPopularityForTrend(productId, trendId, score);
    }

    /**
     * Retrieves the popularity score of a product for a specific trend from the database.
     *
     * @param productId The unique identifier of the product.
     * @param trendId The unique identifier of the trend.
     * @return The popularity score of the product for the given trend, or null if not found.
     */
    @Override
    public Integer getProductPopularityForTrend(Long productId, Long trendId) {
        return productDao.getProductPopularityForTrend(productId, trendId);
    }

    /**
     * Retrieves all popularity scores of a specific product from the database.
     *
     * @param productId The unique identifier of the product.
     * @return A list of popularity scores for the given product across all trends.
     */
    @Override
    public List<Integer> getAllProductPopularities(Long productId) {
        return productDao.getAllProductPopularities(productId);
    }

    /**
     * Fetches the count of products grouped by their respective categories.
     *
     * @return A map where the keys are category names and the values are counts of products
     *         associated with each category.
     *
     * @apiNote This method interfaces with the underlying DAO (Data Access Object) to retrieve
     *          the product count data from the database.
     */
    @Override
    public Map<String, Integer> getProductCountByCategory() {
        return productDao.getProductCountByCategory();
    }
}
