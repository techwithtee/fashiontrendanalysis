package com.wileyedge.fashiontrendanalysis.service;

import com.wileyedge.fashiontrendanalysis.model.Category;
import java.util.List;

/**
 * Defines the service operations for managing categories.
 */
public interface CategoryService {

    /**
     * Retrieves all categories from the system.
     *
     * @return a list of all categories or an empty list if none are found
     */
    List<Category> getAllCategories();

    /**
     * Retrieves a specific category by its ID.
     *
     * @param categoryId the ID of the desired category
     * @return the Category object or null if not found
     */
    Category getCategoryById(Long categoryId);

    /**
     * Adds a new category to the system.
     *
     * @param category the category to be added
     * @return the ID of the newly added category or null if the operation failed
     */
    Long addCategory(Category category);

    /**
     * Updates details of an existing category in the system.
     *
     * @param categoryId the ID of the category to be updated
     * @param category the Category object with updated details
     * @return true if the update was successful, false otherwise
     */
    boolean updateCategory(Long categoryId, Category category);

    /**
     * Deletes a specific category from the system.
     *
     * @param categoryId the ID of the category to be deleted
     * @return true if the category was deleted successfully, false otherwise
     */
    boolean deleteCategory(Long categoryId);

    /**
     * Retrieves categories associated with a specific trend.
     *
     * @param trendId the ID of the trend to find categories for
     * @return a list of categories associated with the trend or an empty list if none are found
     */
    List<Category> getCategoriesByTrend(Long trendId);

    /**
     * Retrieves categories associated with a specific product.
     *
     * @param productId the ID of the product to find categories for
     * @return a list of categories associated with the product or an empty list if none are found
     */
    List<Category> getCategoriesByProduct(Long productId);

    /**
     * Sets the popularity score of a category for a specific season.
     *
     * @param categoryId the ID of the category for which the popularity score is to be set
     * @param season the season for which the score is being set
     * @param score the popularity score to set for the category in the specified season
     */
    void setCategoryPopularityForSeason(Long categoryId, String season, int score);

    /**
     * Retrieves the popularity score of a category for a specific season.
     *
     * @param categoryId the ID of the category for which the popularity score is to be retrieved
     * @param season the season for which the score is being retrieved
     * @return the popularity score of the category for the specified season, or null if not found
     */
    Integer getCategoryPopularityForSeason(Long categoryId, String season);

    /**
     * Retrieves all popularity scores associated with a specific category across different metrics or seasons.
     *
     * @param categoryId the ID of the category for which all popularity scores are to be retrieved
     * @return a list of popularity scores associated with the category, or an empty list if none are found
     */
    List<Integer> getAllCategoryPopularities(Long categoryId);

}
