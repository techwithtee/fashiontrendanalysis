package com.wileyedge.fashiontrendanalysis.model;

/**
 * Represents a category with its associated details.
 * Each category is identified by a unique ID and has a name.
 */
public class Category {

    /** Unique identifier for the category. */
    private Long categoryId;

    /** Name of the category. */
    private String categoryName;

    /**
     * Constructs a new Category with the specified ID and name.
     *
     * @param categoryId    the unique identifier for the category
     * @param categoryName  the name of the category
     */
    public Category(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    /**
     * Retrieves the unique identifier of the category.
     *
     * @return the unique identifier of the category
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the unique identifier for the category.
     *
     * @param categoryId the unique identifier to set
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Retrieves the name of the category.
     *
     * @return the name of the category
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets the name of the category.
     *
     * @param categoryName the name to set for the category
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
