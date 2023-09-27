package com.wileyedge.fashiontrendanalysis.service;

import com.wileyedge.fashiontrendanalysis.dao.DesignerDao;
import com.wileyedge.fashiontrendanalysis.model.Designer;
import com.wileyedge.fashiontrendanalysis.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides the implementation of the DesignerService interface,
 * offering methods to manage and retrieve designer-related data from the database.
 *
 * @see com.wileyedge.fashiontrendanalysis.service.DesignerService
 */
@Service
public class DesignerServiceImpl implements DesignerService {

    private final DesignerDao designerDao;

    /**
     * Constructs a new DesignerServiceImpl with the specified DesignerDao.
     *
     * @param designerDao the DAO responsible for Designer entity operations.
     */
    @Autowired
    public DesignerServiceImpl(DesignerDao designerDao) {
        this.designerDao = designerDao;
    }

    /**
     * Retrieves all designers present in the database.
     *
     * @return a list containing all the designers, or an empty list if none are found.
     */
    @Override
    public List<Designer> getAllDesigners() {
        return designerDao.getAllDesigners();
    }

    /**
     * Retrieves a specific designer based on its unique identifier.
     *
     * @param designerId the unique identifier of the desired designer.
     * @return the Designer object matching the provided ID, or null if not found.
     */
    @Override
    public Designer getDesignerById(Long designerId) {
        return designerDao.getDesignerById(designerId);
    }

    /**
     * Adds a new designer to the database.
     *
     * @param designer the designer entity to be added.
     * @return the unique identifier of the newly added designer, or null if the operation failed.
     */
    @Override
    public Long addDesigner(Designer designer) {
        return designerDao.addDesigner(designer);
    }

    /**
     * Updates the details of an existing designer based on its unique identifier.
     *
     * @param designerId the unique identifier of the designer to be updated.
     * @param designer the updated Designer entity.
     * @return true if the update was successful, false otherwise.
     */
    @Override
    public boolean updateDesigner(Long designerId, Designer designer) {
        return designerDao.updateDesigner(designerId, designer);
    }

    /**
     * Removes a designer from the database based on its unique identifier.
     *
     * @param designerId the unique identifier of the designer to be deleted.
     * @return true if the designer was successfully removed, false otherwise.
     */
    @Override
    public boolean deleteDesigner(Long designerId) {
        return designerDao.deleteDesigner(designerId);
    }

    /**
     * Retrieves a list of designers based on their location.
     *
     * @param location the location to filter designers by.
     * @return a list of designers located in the specified location, or an empty list if none are found.
     */
    @Override
    public List<Designer> getDesignersByLocation(String location) {
        return designerDao.getDesignersByLocation(location);
    }

    /**
     * Retrieves the number of trends associated with a specific designer.
     *
     * @param designerId the ID of the designer.
     * @return the count of trends associated with the designer, or null if not found.
     */
    @Override
    public Integer getDesignerTrendCount(Long designerId) {
        return designerDao.getDesignerTrendCount(designerId);
    }

    /**
     * Retrieves the popularity score of a specific designer.
     *
     * @param designerId the ID of the designer.
     * @return the popularity score of the designer, or null if not found.
     */
    @Override
    public Integer getDesignerPopularityScore(Long designerId) {
        return designerDao.getDesignerPopularityScore(designerId);
    }

    /**
     * Retrieves a list of products associated with a specific designer.
     *
     * @param designerId the ID of the designer.
     * @return a list of products associated with the designer, or an empty list if none are found.
     */
    @Override
    public List<Product> getProductsForDesigner(Long designerId) {
        return designerDao.getProductsForDesigner(designerId);
    }
}
