package com.wileyedge.fashiontrendanalysis.service;

import com.wileyedge.fashiontrendanalysis.dao.DesignerDao;
import com.wileyedge.fashiontrendanalysis.model.Designer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesignerServiceImpl implements DesignerService {

    private final DesignerDao designerDao;

    @Autowired
    public DesignerServiceImpl(DesignerDao designerDao) {
        this.designerDao = designerDao;
    }

    @Override
    public List<Designer> getAllDesigners() {
        return designerDao.getAllDesigners();
    }

    @Override
    public Designer getDesignerById(Long designerId) {
        return designerDao.getDesignerById(designerId);
    }

    @Override
    public Long addDesigner(Designer designer) {
        return designerDao.addDesigner(designer);
    }

    @Override
    public boolean updateDesigner(Long designerId, Designer designer) {
        return designerDao.updateDesigner(designerId, designer);
    }

    @Override
    public boolean deleteDesigner(Long designerId) {
        return designerDao.deleteDesigner(designerId);
    }

    @Override
    public List<Designer> getDesignersByLocation(String location) {
        return designerDao.getDesignersByLocation(location);
    }
}
