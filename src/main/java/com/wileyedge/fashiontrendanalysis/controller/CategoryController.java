package com.wileyedge.fashiontrendanalysis.controller;

import com.wileyedge.fashiontrendanalysis.model.Category;
import com.wileyedge.fashiontrendanalysis.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsible for handling web requests related to the Category entity.
 * Provides CRUD operations for Category through RESTful API endpoints.
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Constructs a new CategoryController instance and injects the associated CategoryService.
     *
     * @param categoryService Service responsible for Category entity operations.
     */
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Fetches all categories.
     *
     * @return A list containing all categories.
     * @apiEndpoint GET https://your-domain/api/categories
     */
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    /**
     * Retrieves a specific category by its ID.
     *
     * @param categoryId The ID of the desired category.
     * @return The category with the specified ID.
     * @apiEndpoint GET https://your-domain/api/categories/{categoryId}
     */
    @GetMapping("/{categoryId}")
    public Category getCategoryById(@PathVariable Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    /**
     * Adds a new category.
     *
     * @param category The category to be added.
     * @return A response indicating the success of the operation.
     * @apiEndpoint POST https://your-domain/api/categories
     */
    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        Long id = categoryService.addCategory(category);
        if (id != null) {
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Modifies an existing category.
     *
     * @param categoryId The ID of the category to be updated.
     * @param category The updated category details.
     * @return A response indicating the success of the operation.
     * @apiEndpoint PUT https://your-domain/api/categories/{categoryId}
     */
    @PutMapping("/{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
        boolean isUpdated = categoryService.updateCategory(categoryId, category);
        return isUpdated ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Deletes a category.
     *
     * @param categoryId The ID of the category to be removed.
     * @return A response indicating the success of the operation.
     * @apiEndpoint DELETE https://your-domain/api/categories/{categoryId}
     */
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
        boolean isDeleted = categoryService.deleteCategory(categoryId);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Retrieves categories associated with a specific trend.
     *
     * @param trendId the ID of the trend to find categories for.
     * @return a list of categories associated with the given trend.
     * @apiEndpoint GET https://your-domain/api/categories/trend/{trendId}
     */
    @GetMapping("/trend/{trendId}")
    public List<Category> getCategoriesByTrend(@PathVariable Long trendId) {
        return categoryService.getCategoriesByTrend(trendId);
    }

    /**
     * Retrieves categories associated with a specific product.
     *
     * @param productId the ID of the product to find categories for.
     * @return a list of categories associated with the given product.
     * @apiEndpoint GET https://your-domain/api/categories/product/{productId}
     */
    @GetMapping("/product/{productId}")
    public List<Category> getCategoriesByProduct(@PathVariable Long productId) {
        return categoryService.getCategoriesByProduct(productId);
    }

    @PostMapping("/{categoryId}/popularity/{season}")
    public ResponseEntity<?> setCategoryPopularityForSeason(@PathVariable Long categoryId, @PathVariable String season, @RequestBody int score) {
        categoryService.setCategoryPopularityForSeason(categoryId, season, score);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{categoryId}/popularity/{season}")
    public ResponseEntity<Integer> getCategoryPopularityForSeason(@PathVariable Long categoryId, @PathVariable String season) {
        Integer score = categoryService.getCategoryPopularityForSeason(categoryId, season);
        return new ResponseEntity<>(score, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}/all-popularities")
    public ResponseEntity<List<Integer>> getAllCategoryPopularities(@PathVariable Long categoryId) {
        List<Integer> scores = categoryService.getAllCategoryPopularities(categoryId);
        return new ResponseEntity<>(scores, HttpStatus.OK);
    }

}
