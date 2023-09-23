package com.wileyedge.fashiontrendanalysis.model;

/**
 * Represents a fashion product with its associated details.
 */
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

    /**
     * Constructs a new Product with the given details.
     *
     * @param productId          the unique identifier for the product
     * @param productName        the name of the product
     * @param categoryId         the identifier for the product's category
     * @param designerId         the identifier for the product's designer
     * @param productDescription the description of the product
     */
    public Product(Long productId, String productName, Long categoryId, Long designerId, String productDescription) {
        this.productId = productId;
        this.productName = productName;
        this.categoryId = categoryId;
        this.designerId = designerId;
        this.productDescription = productDescription;
    }

    /**
     * @return the unique identifier of the product
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * Sets the unique identifier for the product.
     *
     * @param productId the unique identifier to set
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * @return the name of the product
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the name of the product.
     *
     * @param productName the product name to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the identifier for the product's category
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the identifier for the product's category.
     *
     * @param categoryId the category identifier to set
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return the identifier for the product's designer
     */
    public Long getDesignerId() {
        return designerId;
    }

    /**
     * Sets the identifier for the product's designer.
     *
     * @param designerId the designer identifier to set
     */
    public void setDesignerId(Long designerId) {
        this.designerId = designerId;
    }

    /**
     * @return the description of the product
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * Sets the description of the product.
     *
     * @param productDescription the product description to set
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}
