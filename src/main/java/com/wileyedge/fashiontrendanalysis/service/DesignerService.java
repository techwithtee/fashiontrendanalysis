package com.wileyedge.fashiontrendanalysis.service;

import com.wileyedge.fashiontrendanalysis.model.Designer;

import java.util.List;

public interface DesignerService {

    List<Designer> getAllDesigners();

    Designer getDesignerById(Long designerId);

    Long addDesigner(Designer designer);

    boolean updateDesigner(Long designerId, Designer designer);

    boolean deleteDesigner(Long designerId);

    List<Designer> getDesignersByLocation(String location);

    Integer getDesignerTrendCount(Long designerId);

    Integer getDesignerPopularityScore(Long designerId);
}
