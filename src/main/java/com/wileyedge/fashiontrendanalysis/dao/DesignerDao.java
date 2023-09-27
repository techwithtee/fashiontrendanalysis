package com.wileyedge.fashiontrendanalysis.dao;

import com.wileyedge.fashiontrendanalysis.model.Designer;
import com.wileyedge.fashiontrendanalysis.model.Product;

import java.util.List;

public interface DesignerDao {

    List<Designer> getAllDesigners();

    Designer getDesignerById(Long designerId);

    Long addDesigner(Designer designer);

    boolean updateDesigner(Long designerId, Designer designer);

    boolean deleteDesigner(Long designerId);

    List<Designer> getDesignersByLocation(String location);

    Integer getDesignerTrendCount(Long designerId);

    Integer getDesignerPopularityScore(Long designerId);

    List<Product> getProductsForDesigner(Long designerId);



}
