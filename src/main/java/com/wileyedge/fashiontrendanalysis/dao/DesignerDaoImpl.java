package com.wileyedge.fashiontrendanalysis.dao;

import com.wileyedge.fashiontrendanalysis.model.Designer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DesignerDaoImpl implements DesignerDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Designer> rowMapper;

    @Autowired
    public DesignerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = (rs, rowNum) -> {
            Designer designer = new Designer();
            designer.setDesignerId(rs.getLong("designer_id"));
            designer.setDesignerName(rs.getString("designer_name"));
            designer.setDesignerLocation(rs.getString("designer_location"));
            return designer;
        };
    }

    @Override
    public List<Designer> getAllDesigners() {
        String query = "SELECT * FROM designer";
        return jdbcTemplate.query(query, rowMapper);
    }

    @Override
    public Designer getDesignerById(Long designerId) {
        String query = "SELECT * FROM designer WHERE designer_id=?";
        return jdbcTemplate.queryForObject(query, rowMapper, designerId);
    }

    @Override
    public Long addDesigner(Designer designer) {
        String query = "INSERT INTO designer (designer_name, designer_location) VALUES (?, ?)";
        jdbcTemplate.update(query, designer.getDesignerName(), designer.getDesignerLocation());
        return designer.getDesignerId();
    }

    @Override
    public boolean updateDesigner(Long designerId, Designer designer) {
        String query = "UPDATE designer SET designer_name=?, designer_location=? WHERE designer_id=?";
        return jdbcTemplate.update(query, designer.getDesignerName(), designer.getDesignerLocation(), designerId) > 0;
    }

    @Override
    public boolean deleteDesigner(Long designerId) {
        String query = "DELETE FROM designer WHERE designer_id=?";
        return jdbcTemplate.update(query, designerId) > 0;
    }

    @Override
    public List<Designer> getDesignersByLocation(String location) {
        String query = "SELECT * FROM designer WHERE designer_location=?";
        return jdbcTemplate.query(query, rowMapper, location);
    }
}
