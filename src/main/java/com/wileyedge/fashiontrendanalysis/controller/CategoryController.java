package com.wileyedge.fashiontrendanalysis.controller;

import com.wileyedge.fashiontrendanalysis.model.Category;
import com.wileyedge.fashiontrendanalysis.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller responsible for handling web requests related to the Category entity.
 * Provides CRUD operations for Category through RESTful API endpoints.
 */
@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
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
     * @apiEndpoint GET http://localhost:6363/api/categories
     */
    @GetMapping
    @CrossOrigin
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    /**
     * Retrieves a specific category by its ID.
     *
     * @param categoryId The ID of the desired category.
     * @return The category with the specified ID.
     * @apiEndpoint GET http://localhost:6363/api/categories/{categoryId}
     */
    @GetMapping("/{categoryId}")
    @CrossOrigin
    public Category getCategoryById(@PathVariable Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    /**
     * Adds a new category.
     *
     * @param category The category to be added.
     * @return A response indicating the success of the operation.
     * @apiEndpoint POST http://localhost:6363/api/categories
     */
    @PostMapping
    @CrossOrigin
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
     * @apiEndpoint PUT http://localhost:6363/api/categories/{categoryId}
     */
    @PutMapping("/{categoryId}")
    @CrossOrigin
    public ResponseEntity<?> updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
        boolean isUpdated = categoryService.updateCategory(categoryId, category);
        return isUpdated ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Deletes a category.
     *
     * @param categoryId The ID of the category to be removed.
     * @return A response indicating the success of the operation.
     * @apiEndpoint DELETE http://localhost:6363/api/categories/{categoryId}
     */
    @DeleteMapping("/{categoryId}")
    @CrossOrigin
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
        boolean isDeleted = categoryService.deleteCategory(categoryId);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Retrieves categories associated with a specific trend.
     *
     * @param trendId the ID of the trend to find categories for.
     * @return a list of categories associated with the given trend.
     * @apiEndpoint GET http://localhost:6363/api/categories/trend/{trendId}
     */
    @GetMapping("/trend/{trendId}")
    @CrossOrigin
    public List<Category> getCategoriesByTrend(@PathVariable Long trendId) {
        return categoryService.getCategoriesByTrend(trendId);
    }

    /**
     * Retrieves categories associated with a specific product.
     *
     * @param productId the ID of the product to find categories for.
     * @return a list of categories associated with the given product.
     * @apiEndpoint GET http://localhost:6363/api/categories/product/{productId}
     */
    @GetMapping("/product/{productId}")
    @CrossOrigin
    public List<Category> getCategoriesByProduct(@PathVariable Long productId) {
        return categoryService.getCategoriesByProduct(productId);
    }

    /**
     * Updates the popularity score of a category for a given season.
     *
     * @param categoryId The ID of the category whose popularity score is to be set.
     * @param season The season for which the popularity score is to be set.
     * @param score The popularity score.
     * @return A response indicating the success of the operation.
     * @apiEndpoint POST http://localhost:6363/api/categories/{categoryId}/popularity/{season}
     */
    @PostMapping("/{categoryId}/popularity/{season}")
    @CrossOrigin
    public ResponseEntity<?> setCategoryPopularityForSeason(@PathVariable Long categoryId, @PathVariable String season, @RequestBody int score) {
        categoryService.setCategoryPopularityForSeason(categoryId, season, score);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Retrieves the popularity score of a category for a given season.
     *
     * @param categoryId The ID of the category whose popularity score is to be retrieved.
     * @param season The season for which the popularity score is to be retrieved.
     * @return A response containing the popularity score.
     * @apiEndpoint GET http://localhost:6363/api/categories/{categoryId}/popularity/{season}
     */
    @GetMapping("/{categoryId}/popularity/{season}")
    @CrossOrigin
    public ResponseEntity<Integer> getCategoryPopularityForSeason(@PathVariable Long categoryId, @PathVariable String season) {
        Integer score = categoryService.getCategoryPopularityForSeason(categoryId, season);
        return new ResponseEntity<>(score, HttpStatus.OK);
    }

    /**
     * Retrieves all popularity scores of a category across seasons.
     *
     * @param categoryId The ID of the category whose popularity scores are to be retrieved.
     * @return A response containing a list of popularity scores.
     * @apiEndpoint GET http://localhost:6363/api/categories/{categoryId}/all-popularities
     */
    @GetMapping("/{categoryId}/all-popularities")
    @CrossOrigin
    public ResponseEntity<List<Integer>> getAllCategoryPopularities(@PathVariable Long categoryId) {
        List<Integer> scores = categoryService.getAllCategoryPopularities(categoryId);
        return new ResponseEntity<>(scores, HttpStatus.OK);
    }

    /**
     * Fetches the popularity overview for a specific category.
     * This overview provides detailed popularity metrics associated with the given category ID.
     *
     * @param categoryId The unique identifier of the category for which the popularity overview is to be fetched.
     * @return A ResponseEntity containing a list of popularity data maps for the specified category and an HTTP status.
     *
     * @apiEndpoint GET /api/{some_base_path}/{categoryId}/popularity-overview
     * @apiNote This endpoint is used to retrieve a comprehensive popularity overview for a particular category.
     *          The overview might include metrics like seasonal popularity scores, trends associated with the category, etc.
     * @apiPermission None (publicly accessible)
     */
    @GetMapping("/{categoryId}/popularity-overview")
    @CrossOrigin
    public ResponseEntity<List<Map<String, Object>>> getCategoryPopularityOverview(@PathVariable Long categoryId) {
        List<Map<String, Object>> popularityData = categoryService.getCategoryPopularityOverview(categoryId);
        return new ResponseEntity<>(popularityData, HttpStatus.OK);
    }

}
