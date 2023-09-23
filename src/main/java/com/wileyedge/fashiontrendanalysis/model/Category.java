package com.wileyedge.fashiontrendanalysis.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

/**
 * Represents a category with its associated details.
 * Each category is identified by a unique ID and has a name.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Category {

    /** Unique identifier for the category. */
    private Long categoryId;

    /** Name of the category. */
    private String categoryName;
}
