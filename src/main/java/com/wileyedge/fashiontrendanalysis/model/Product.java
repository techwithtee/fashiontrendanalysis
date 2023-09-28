package com.wileyedge.fashiontrendanalysis.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Represents a fashion product in the fashion trend analysis system.
 * Contains detailed attributes about a product, including its unique ID, name, associated category, designer, and a comprehensive description.
 * The class leverages Lombok annotations for automatic generation of getter, setter, and constructor methods, reducing boilerplate code.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    /**
     * Unique identifier for the product.
     */
    private Long productId;

    /**
     * Name or title of the product, providing a brief introduction to the item.
     */
    private String productName;

    /**
     * Identifier pointing to the category to which this product belongs.
     */
    private Long categoryId;

    /**
     * Identifier linking the product to its associated designer.
     */
    private Long designerId;

    /**
     * Detailed description of the product, potentially including materials, use-cases, design inspirations, etc.
     */
    private String productDescription;

    /**
     * Constructs a Product instance with the specified parameters.
     *
     * @param l  The product ID.
     * @param s  The product name.
     * @param s1 The product description.
     */
    public Product(long l, String s, String s1) {
    }

}
