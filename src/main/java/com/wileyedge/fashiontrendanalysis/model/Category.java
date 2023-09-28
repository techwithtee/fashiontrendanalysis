package com.wileyedge.fashiontrendanalysis.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

/**
 *Represents a category in the fashion trend analysis system.
 *  Contains information such as the category's unique ID and name.
 *  The class uses Lombok annotations for boilerplate code reduction.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category {

    /** Unique identifier for the category. */
    private Long categoryId;

    /** Name of the category. */
    private String categoryName;
}
