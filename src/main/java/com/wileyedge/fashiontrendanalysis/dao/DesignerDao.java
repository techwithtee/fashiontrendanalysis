package com.wileyedge.fashiontrendanalysis.dao;

import com.wileyedge.fashiontrendanalysis.model.Designer;
import com.wileyedge.fashiontrendanalysis.model.Product;

import java.util.List;

/**
 * Interface defining CRUD operations and other functionalities related to the Designer entity.
 */
public interface DesignerDao {

    /**
     * Retrieves all designers from the database.
     *
     * @return a list of all designers, or an empty list if none are found
     */
    List<Designer> getAllDesigners();

    /**
     * Retrieves a specific designer by its ID.
     *
     * @param designerId the ID of the designer to retrieve
     * @return the designer with the specified ID, or null if not found
     */
    Designer getDesignerById(Long designerId);

    /**
     * Adds a new designer to the database.
     *
     * @param designer the designer to be added
     * @return the ID of the newly added designer, or null if the operation failed
     */
    Long addDesigner(Designer designer);

    /**
     * Updates an existing designer in the database.
     *
     * @param designerId the ID of the designer to update
     * @param designer the designer object containing updated details
     * @return true if the update was successful, false otherwise
     */
    boolean updateDesigner(Long designerId, Designer designer);

    /**
     * Deletes a specific designer from the database using its ID.
     *
     * @param designerId the ID of the designer to delete
     * @return true if the deletion was successful, false otherwise
     */
    boolean deleteDesigner(Long designerId);

    /**
     * Retrieves designers based on their location.
     *
     * @param location the location to filter designers by
     * @return a list of designers from the specified location, or an empty list if none are found
     */
    List<Designer> getDesignersByLocation(String location);

    /**
     * Retrieves the count of trends associated with a specific designer.
     *
     * @param designerId the ID of the designer
     * @return the count of trends associated with the designer, or null if the designer does not exist
     */
    Integer getDesignerTrendCount(Long designerId);

    /**
     * Retrieves the popularity score of a specific designer.
     *
     * @param designerId the ID of the designer
     * @return the popularity score of the designer, or null if the designer does not exist
     */
    Integer getDesignerPopularityScore(Long designerId);

    /**
     * Retrieves all products associated with a specific designer.
     *
     * @param designerId the ID of the designer
     * @return a list of products associated with the designer, or an empty list if none are found
     */
    List<Product> getProductsForDesigner(Long designerId);



}
