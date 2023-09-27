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
     * @endpoint GET /api/products
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
     * @endpoint GET /api/products/{productId}
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
     * @endpoint POST /api/products
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
     * @endpoint PUT /api/products/{productId}
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
     * @endpoint DELETE /api/products/{productId}
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
     * @endpoint GET /api/products/designer/{designerId}
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
     * @endpoint GET /api/products/category/{categoryId}
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(productService.getProductsByCategory(categoryId));
    }

    @PostMapping("/{productId}/associateDesigner/{designerId}")
    public ResponseEntity<Void> associateDesignerWithProduct(@PathVariable Long designerId, @PathVariable Long productId) {
        // Logic to associate designer with product
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{productId}/dissociateDesigner/{designerId}")
    public ResponseEntity<Void> dissociateDesignerFromProduct(@PathVariable Long designerId, @PathVariable Long productId) {
        // Logic to dissociate designer from product
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{productId}/designers")
    public ResponseEntity<List<Designer>> getDesignersForProduct(@PathVariable Long productId) {
        // Logic to get designers for a product
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @PostMapping("/{productId}/popularity/{trendId}")
    public ResponseEntity<?> setProductPopularityForTrend(@PathVariable Long productId, @PathVariable Long trendId, @RequestBody int score) {
        productService.setProductPopularityForTrend(productId, trendId, score);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{productId}/popularity/{trendId}")
    public ResponseEntity<Integer> getProductPopularityForTrend(@PathVariable Long productId, @PathVariable Long trendId) {
        Integer score = productService.getProductPopularityForTrend(productId, trendId);
        return new ResponseEntity<>(score, HttpStatus.OK);
    }

    @GetMapping("/{productId}/all-popularities")
    public ResponseEntity<List<Integer>> getAllProductPopularities(@PathVariable Long productId) {
        List<Integer> scores = productService.getAllProductPopularities(productId);
        return new ResponseEntity<>(scores, HttpStatus.OK);
    }

}
