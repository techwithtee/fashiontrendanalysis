package com.wileyedge.fashiontrendanalysis.dao;

import com.wileyedge.fashiontrendanalysis.model.Category;

import java.util.List;

public interface CategoryDAO {

    /**
     * Retrieves all categories from the database.
     *
     * @return a list of all fashion trend categories
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
     * This method is intended for admin usage.
     *
     * @param category the category to add
     * @return the ID of the newly created category
     */
    Long addCategory(Category category);

    /**
     * Updates an existing category in the database by its ID.
     * This method is intended for admin usage.
     *
     * @param categoryId the ID of the category to update
     * @param category the category object with updated details
     * @return true if the category was updated successfully, false otherwise
     */
    boolean updateCategory(Long categoryId, Category category);

    /**
     * Removes a specific category by its ID from the database.
     * This method is intended for admin usage.
     *
     * @param categoryId the ID of the category to delete
     * @return true if the category was deleted successfully, false otherwise
     */
    boolean deleteCategory(Long categoryId);
}
