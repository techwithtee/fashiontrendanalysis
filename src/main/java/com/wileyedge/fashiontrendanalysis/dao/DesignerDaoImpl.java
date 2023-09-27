package com.wileyedge.fashiontrendanalysis.dao;

import com.wileyedge.fashiontrendanalysis.exceptions.CustomUncheckedException;
import com.wileyedge.fashiontrendanalysis.model.Designer;
import com.wileyedge.fashiontrendanalysis.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class DesignerDaoImpl implements DesignerDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Designer> rowMapper;
   private final RowMapper<Product> productRowMapper;

    @Autowired
    public DesignerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = (rs, rowNum) -> {
            Designer designer = new Designer();
            designer.setDesignerId(rs.getLong("designer_id"));
            designer.setDesignerName(rs.getString("designer_name"));
            designer.setDesignerLocation(rs.getString("designer_location"));
            designer.setTrendCount(rs.getInt("trend_count"));
            designer.setPopularityScore(rs.getInt("popularity_score"));
            return designer;
        };
        this.productRowMapper = (rs, rowNum) -> {
            Product product = new Product();
            product.setProductId(rs.getLong("product_id"));
            product.setProductName(rs.getString("product_name"));
            // ... set other product fields here
            return product;
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
        String sql = "INSERT INTO designer (designer_name, designer_location, trend_count, popularity_score) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"designer_id"});
                ps.setString(1, designer.getDesignerName());
                ps.setString(2, designer.getDesignerLocation());
                ps.setInt(3, designer.getTrendCount());
                ps.setInt(4, designer.getPopularityScore());
                return ps;
            }, keyHolder);
        } catch (DataAccessException e) {
            throw new CustomUncheckedException("Failed to add designer", e);
        }
        return (Long) keyHolder.getKey();
    }


    @Override
    public boolean updateDesigner(Long designerId, Designer designer) {
        String query = "UPDATE designer SET designer_name=?, designer_location=?, trend_count=?, popularity_score=? WHERE designer_id=?";
        return jdbcTemplate.update(query, designer.getDesignerName(), designer.getDesignerLocation(), designer.getTrendCount(), designer.getPopularityScore(), designerId) > 0;
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

    @Override
    public Integer getDesignerTrendCount(Long designerId) {
        String query = "SELECT trend_count FROM designer WHERE designer_id=?";
        return jdbcTemplate.queryForObject(query, Integer.class, designerId);
    }

    @Override
    public Integer getDesignerPopularityScore(Long designerId) {
        String query = "SELECT popularity_score FROM designer WHERE designer_id=?";
        return jdbcTemplate.queryForObject(query, Integer.class, designerId);
    }

    @Override
    public List<Product> getProductsForDesigner(Long designerId) {
        String query = "SELECT * FROM products WHERE product_id IN (SELECT product_id FROM product_designer_association WHERE designer_id=?)";
        return jdbcTemplate.query(query, productRowMapper, designerId);
    }
}
