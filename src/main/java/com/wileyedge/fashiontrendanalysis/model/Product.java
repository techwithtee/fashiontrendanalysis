package com.wileyedge.fashiontrendanalysis.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Represents a fashion product with its associated details.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    /** Unique identifier for the product. */
    private Long productId;

    /** Name of the product. */
    private String productName;

    /** Identifier for the product's category. */
    private Long categoryId;

    /** Identifier for the product's designer. */
    private Long designerId;

    /** Description of the product. */
    private String productDescription;

}
