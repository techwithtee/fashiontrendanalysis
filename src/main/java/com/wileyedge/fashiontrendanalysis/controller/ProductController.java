package com.wileyedge.fashiontrendanalysis.controller;

import com.wileyedge.fashiontrendanalysis.model.Designer;
import com.wileyedge.fashiontrendanalysis.model.Product;
import com.wileyedge.fashiontrendanalysis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for managing operations related to products.
 */
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ProductController {

    private final ProductService productService;

    /**
     * Constructs a new ProductController with the provided ProductService.
     *
     * @param productService The service responsible for Product entity operations.
     */
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    /**
     * Fetches all products.
     *
     * @return a list of all products, or an empty list if none exist
     * @apiEndpoint GET http://localhost:6363/api/products
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    /**
     * Retrieves a specific product by its ID.
     *
     * @param productId the ID of the product to retrieve
     * @return the product with the specified ID, or 404 if not found
     * @apiEndpoint GET http://localhost:6363/api/products/{productId}
     */
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Adds a new product.
     *
     * @param product the product to add
     * @return the ID of the newly created product
     * @apiEndpoint POST http://localhost:6363/api/products
     */
    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    /**
     * Updates a product by its ID.
     *
     * @param productId the ID of the product to update
     * @param product the updated product details
     * @return a confirmation of the update
     * @apiEndpoint PUT http://localhost:6363/api/products/{productId}
     */
    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        boolean updated = productService.updateProduct(productId, product);
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a product by its ID.
     *
     * @param productId the ID of the product to delete
     * @return a confirmation of the deletion
     * @apiEndpoint DELETE http://localhost:6363/api/products/{productId}
     */
    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        boolean deleted = productService.deleteProduct(productId);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Fetches products associated with a specific designer.
     *
     * @param designerId the ID of the designer
     * @return a list of products by the specified designer
     * @apiEndpoint GET http://localhost:6363/api/products/designer/{designerId}
     */
    @GetMapping("/designer/{designerId}")
    public ResponseEntity<List<Product>> getProductsByDesigner(@PathVariable Long designerId) {
        return ResponseEntity.ok(productService.getProductsByDesigner(designerId));
    }

    /**
     * Fetches products associated with a specific category.
     *
     * @param categoryId the ID of the category
     * @return a list of products in the specified category
     * @apiEndpoint GET http://localhost:6363/api/products/category/{categoryId}
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(productService.getProductsByCategory(categoryId));
    }

    /**
     * Associates a designer with a product.
     *
     * @param designerId The ID of the designer to associate.
     * @param productId The ID of the product to associate with the designer.
     * @return A response indicating the success of the operation.
     * @apiEndpoint POST http://localhost:6363/api/products/{productId}/associateDesigner/{designerId}
     */
    @PostMapping("/{productId}/associateDesigner/{designerId}")
    public ResponseEntity<Void> associateDesignerWithProduct(@PathVariable Long designerId, @PathVariable Long productId) {
        // Logic to associate designer with product
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Dissociates a designer from a product.
     *
     * @param designerId The ID of the designer to dissociate.
     * @param productId The ID of the product to dissociate the designer from.
     * @return A response indicating the success of the operation.
     * @apiEndpoint DELETE http://localhost:6363/api/products/{productId}/dissociateDesigner/{designerId}
     */
    @DeleteMapping("/{productId}/dissociateDesigner/{designerId}")
    public ResponseEntity<Void> dissociateDesignerFromProduct(@PathVariable Long designerId, @PathVariable Long productId) {
        // Logic to dissociate designer from product
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Fetches the designers associated with a specific product.
     *
     * @param productId The ID of the product.
     * @return A list of designers associated with the specified product.
     * @apiEndpoint GET http://localhost:6363/api/products/{productId}/designers
     */
    @GetMapping("/{productId}/designers")
    public ResponseEntity<List<Designer>> getDesignersForProduct(@PathVariable Long productId) {
        // Logic to get designers for a product
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    /**
     * Sets the popularity score of a product for a specific trend.
     *
     * @param productId The ID of the product.
     * @param trendId The ID of the trend.
     * @param score The popularity score to set.
     * @return A response indicating the success of the operation.
     * @apiEndpoint POST http://localhost:6363/api/products/{productId}/popularity/{trendId}
     */
    @PostMapping("/{productId}/popularity/{trendId}")
    public ResponseEntity<?> setProductPopularityForTrend(@PathVariable Long productId, @PathVariable Long trendId, @RequestBody int score) {
        productService.setProductPopularityForTrend(productId, trendId, score);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Fetches the popularity score of a product for a specific trend.
     *
     * @param productId The ID of the product.
     * @param trendId The ID of the trend.
     * @return The popularity score of the specified product for the specified trend.
     * @apiEndpoint GET http://localhost:6363/api/products/{productId}/popularity/{trendId}
     */
    @GetMapping("/{productId}/popularity/{trendId}")
    public ResponseEntity<Integer> getProductPopularityForTrend(@PathVariable Long productId, @PathVariable Long trendId) {
        Integer score = productService.getProductPopularityForTrend(productId, trendId);
        return new ResponseEntity<>(score, HttpStatus.OK);
    }

    /**
     * Fetches all popularity scores of a product.
     *
     * @param productId The ID of the product.
     * @return A list of all popularity scores of the specified product.
     * @apiEndpoint GET http://localhost:6363/api/products/{productId}/all-popularities
     */
    @GetMapping("/{productId}/all-popularities")
    public ResponseEntity<List<Integer>> getAllProductPopularities(@PathVariable Long productId) {
        List<Integer> scores = productService.getAllProductPopularities(productId);
        return new ResponseEntity<>(scores, HttpStatus.OK);
    }

    @GetMapping("/visualisation")
    public String showVisualisationPage() {
        return "visualisation"; // This corresponds to the HTML file name without the extension
    }
}
