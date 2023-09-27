package com.wileyedge.fashiontrendanalysis.service.impl;

import com.wileyedge.fashiontrendanalysis.dao.ProductDao;
import com.wileyedge.fashiontrendanalysis.model.Designer;
import com.wileyedge.fashiontrendanalysis.model.Product;
import com.wileyedge.fashiontrendanalysis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void associateDesignerWithProduct(Long designerId, Long productId) {
        productDao.associateDesignerWithProduct(designerId, productId);
    }

    @Override
    public void dissociateDesignerFromProduct(Long designerId, Long productId) {
        productDao.dissociateDesignerFromProduct(designerId, productId);
    }

    @Override
    public List<Designer> getDesignersForProduct(Long productId) {
        return productDao.getDesignersForProduct(productId);
    }

    @Override
    public void setProductPopularityForTrend(Long productId, Long trendId, int score) {
        productDao.setProductPopularityForTrend(productId, trendId, score);
    }

    @Override
    public Integer getProductPopularityForTrend(Long productId, Long trendId) {
        return productDao.getProductPopularityForTrend(productId, trendId);
    }

    @Override
    public List<Integer> getAllProductPopularities(Long productId) {
        return productDao.getAllProductPopularities(productId);
    }

}
