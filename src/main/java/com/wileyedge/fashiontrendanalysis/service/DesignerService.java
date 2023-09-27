package com.wileyedge.fashiontrendanalysis.service;

import com.wileyedge.fashiontrendanalysis.model.Designer;
import com.wileyedge.fashiontrendanalysis.model.Product;

import java.util.List;

/**
 * Defines the service operations for managing designers in the application.
 */
public interface DesignerService {

    /**
     * Retrieves all designers from the system.
     *
     * @return a list of all designers or an empty list if none are found.
     */
    List<Designer> getAllDesigners();

    /**
     * Retrieves a specific designer by its ID.
     *
     * @param designerId the ID of the desired designer.
     * @return the Designer object or null if not found.
     */
    Designer getDesignerById(Long designerId);

    /**
     * Adds a new designer to the system.
     *
     * @param designer the designer entity to be added.
     * @return the ID of the newly added designer or null if the operation failed.
     */
    Long addDesigner(Designer designer);

    /**
     * Updates the details of an existing designer in the system.
     *
     * @param designerId the ID of the designer to be updated.
     * @param designer the updated Designer entity.
     * @return true if the update was successful, false otherwise.
     */
    boolean updateDesigner(Long designerId, Designer designer);

    /**
     * Removes a designer from the system.
     *
     * @param designerId the ID of the designer to be deleted.
     * @return true if the designer was successfully removed, false otherwise.
     */
    boolean deleteDesigner(Long designerId);

    /**
     * Retrieves designers based on their location.
     *
     * @param location the location to filter designers by.
     * @return a list of designers located in the specified location or an empty list if none are found.
     */
    List<Designer> getDesignersByLocation(String location);

    /**
     * Retrieves the number of trends associated with a specific designer.
     *
     * @param designerId the ID of the designer.
     * @return the count of trends associated with the designer or null if not found.
     */
    Integer getDesignerTrendCount(Long designerId);

    /**
     * Retrieves the popularity score of a specific designer.
     *
     * @param designerId the ID of the designer.
     * @return the popularity score of the designer or null if not found.
     */
    Integer getDesignerPopularityScore(Long designerId);

    /**
     * Retrieves a list of products associated with a specific designer.
     *
     * @param designerId the ID of the designer.
     * @return a list of products associated with the designer or an empty list if none are found.
     */
    List<Product> getProductsForDesigner(Long designerId);
}
