package com.wileyedge.fashiontrendanalysis.service;

import com.wileyedge.fashiontrendanalysis.dao.CategoryDao;
import com.wileyedge.fashiontrendanalysis.model.Category;
import com.wileyedge.fashiontrendanalysis.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class provides the implementation of the CategoryService interface,
 * offering methods to manage and retrieve category-related data from the database.
 *
 * @see com.wileyedge.fashiontrendanalysis.service.CategoryService
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    /**
     * Constructs a new CategoryServiceImpl with the specified CategoryDao.
     *
     * @param categoryDao the DAO responsible for Category entity operations.
     */
    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    /**
     * Retrieves all the categories present in the database.
     *
     * @return a list containing all the categories, or an empty list if none are found.
     */
    @Override
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    /**
     * Retrieves a category based on its unique identifier.
     *
     * @param categoryId the unique identifier of the desired category.
     * @return the Category object matching the provided ID, or null if not found.
     */
    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryDao.getCategoryById(categoryId);
    }

    /**
     * Adds a new category to the database.
     *
     * @param category the category entity to be added.
     * @return the unique identifier of the newly added category, or null if the operation failed.
     */
    @Override
    public Long addCategory(Category category) {
        return categoryDao.addCategory(category);
    }

    /**
     * Updates the details of an existing category based on its unique identifier.
     *
     * @param categoryId the unique identifier of the category to be updated.
     * @param category   the updated Category entity.
     * @return true if the update was successful, false otherwise.
     */
    @Override
    public boolean updateCategory(Long categoryId, Category category) {
        return categoryDao.updateCategory(categoryId, category);
    }

    /**
     * Removes a category from the database based on its unique identifier.
     *
     * @param categoryId the unique identifier of the category to be deleted.
     * @return true if the category was successfully removed, false otherwise.
     */
    @Override
    public boolean deleteCategory(Long categoryId) {
        return categoryDao.deleteCategory(categoryId);
    }

    /**
     * Retrieves a list of categories associated with a specific trend.
     *
     * @param trendId the unique identifier of the trend.
     * @return a list of categories associated with the given trend, or an empty list if none are found.
     */
    @Override
    public List<Category> getCategoriesByTrend(Long trendId) {
        return categoryDao.getCategoriesByTrend(trendId);
    }

    /**
     * Retrieves a list of categories associated with a specific product.
     *
     * @param productId the unique identifier of the product.
     * @return a list of categories associated with the given product, or an empty list if none are found.
     */
    @Override
    public List<Category> getCategoriesByProduct(Long productId) {
        return categoryDao.getCategoriesByProduct(productId);
    }

    /**
     * Sets the popularity score of a category for a specific season.
     *
     * @param categoryId the ID of the category for which the popularity score is to be set.
     * @param season     the season for which the score is being set.
     * @param score      the popularity score to set for the category in the specified season.
     */
    @Override
    public void setCategoryPopularityForSeason(Long categoryId, String season, int score) {
        categoryDao.setCategoryPopularityForSeason(categoryId, season, score);
    }

    /**
     * Retrieves the popularity score of a category for a specific season.
     *
     * @param categoryId the ID of the category for which the popularity score is to be retrieved.
     * @param season     the season for which the score is being retrieved.
     * @return the popularity score of the category for the specified season, or null if not found.
     */
    @Override
    public Integer getCategoryPopularityForSeason(Long categoryId, String season) {
        return categoryDao.getCategoryPopularityForSeason(categoryId, season);
    }

    /**
     * Retrieves all popularity scores associated with a specific category across different metrics or seasons.
     *
     * @param categoryId the ID of the category for which all popularity scores are to be retrieved.
     * @return a list of popularity scores associated with the category, or an empty list if none are found.
     */
    @Override
    public List<Integer> getAllCategoryPopularities(Long categoryId) {
        return categoryDao.getAllCategoryPopularities(categoryId);
    }

    /**
     * Retrieves an overview of the popularity of a given category, segmented by season.
     *
     * Note: This implementation returns dummy data.
     *
     * @param categoryId The ID of the category for which the popularity overview is required.
     * @return A list of maps, each representing the popularity score for the category in a specific season.
     */
    @Override
    public List<Map<String, Object>> getCategoryPopularityOverview(Long categoryId) {
        List<Map<String, Object>> dummyData = new ArrayList<>();
        dummyData.add(Map.of("season", "Spring", "score", 85));
        dummyData.add(Map.of("season", "Summer", "score", 90));
        dummyData.add(Map.of("season", "Fall", "score", 75));
        dummyData.add(Map.of("season", "Winter", "score", 80));
        return dummyData;
    }

}



