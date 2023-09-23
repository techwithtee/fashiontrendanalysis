package com.wileyedge.fashiontrendanalysis.dao;

import com.wileyedge.fashiontrendanalysis.model.Category;

import java.util.List;

public interface CategoryDAO {

    /**
     * Retrieves all categories from the database.
     *
     * @return a list of all categories, or an empty list if none are found
     */
    List<Category> getAllCategories();

    /**
     * Retrieves a specific category by its ID.
     *
     * @param categoryId the ID of the category to retrieve
     * @return the category with the specified ID, or null if not found
     */
    Category getCategoryById(Long categoryId);

    /**
     * Adds a new category to the database.
     *
     * @param category the category to be added
     * @return the ID of the newly added category, or null if the operation failed
     */
    Long addCategory(Category category);

    /**
     * Updates an existing category in the database.
     *
     * @param categoryId the category with updated details
     * @return true if the update was successful, false otherwise
     */
    boolean updateCategory(Category categoryId, Category category);

    /**
     * Deletes a specific category from the database using its ID.
     *
     * @param categoryId the ID of the category to delete
     * @return true if the deletion was successful, false otherwise
     */
    boolean deleteCategory(Long categoryId);

    /**
     * Retrieves categories associated with a specific trend.
     *
     * @param trendId the ID of the trend
     * @return a list of categories associated with the specified trend, or an empty list if none are found
     */
    List<Category> getCategoriesByTrend(Long trendId);

    /**
     * Retrieves categories associated with a specific product.
     *
     * @param productId the ID of the product
     * @return a list of categories associated with the specified product, or an empty list if none are found
     */
    List<Category> getCategoriesByProduct(Long productId);
}
