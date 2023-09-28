package com.wileyedge.fashiontrendanalysis.dao;

import com.wileyedge.fashiontrendanalysis.exceptions.CustomUncheckedException;
import com.wileyedge.fashiontrendanalysis.model.Designer;
import com.wileyedge.fashiontrendanalysis.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Test class for the DesignerDaoImpl.
 * It tests various CRUD operations related to the Designer entity.
 */
@ExtendWith(MockitoExtension.class)
public class DesignerDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private DesignerDaoImpl designerDao;

    /**
     * Setup method for initializing any necessary data or configurations before the tests run.
     */
    @BeforeEach
    public void setup() {
        // Any setup required before tests
    }

    /**
     * Test if all designers can be fetched successfully.
     */
    @Test
    public void testGetAllDesigners() {
        // Expected designer list
        List<Designer> expectedDesigners = Arrays.asList(
                new Designer(1L, "Designer 1", "Location 1", 5, 90),
                new Designer(2L, "Designer 2", "Location 2", 10, 85)
        );

        // Mock the behavior of the jdbcTemplate
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expectedDesigners);

        // Test the method
        List<Designer> returnedDesigners = designerDao.getAllDesigners();

        // Assert that the expected list matches the returned list
        assertEquals(expectedDesigners, returnedDesigners);
    }

    /**
     * Test to check if a designer can be fetched successfully by their ID.
     */
    @Test
    public void testGetDesignerById() {
        Long designerId = 1L;
        Designer expectedDesigner = new Designer(designerId, "Designer 1", "Location 1", 5, 90);

        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), eq(designerId)))
                .thenReturn(expectedDesigner);

        Designer returnedDesigner = designerDao.getDesignerById(designerId);

        assertEquals(expectedDesigner, returnedDesigner);
    }

    /**
     * Test to check if a designer can be added successfully.
     */
    @Test
    public void testAddDesignerSuccess() {
        Designer designer = new Designer(null, "New Designer", "New Location", 0, 0);

        designerDao.addDesigner(designer);

        verify(jdbcTemplate, times(1)).update(any(PreparedStatementCreator.class), any(KeyHolder.class));

    }

    /**
     * Test to check if adding a designer throws an exception upon failure.
     */
    @Test
    public void testAddDesignerFailure() {
        Designer designer = new Designer(null, "New Designer", "New Location", 0, 0);

        doThrow(new DataAccessException("Test exception") {})
                .when(jdbcTemplate).update(any(PreparedStatementCreator.class), any(KeyHolder.class));

        assertThrows(CustomUncheckedException.class, () -> designerDao.addDesigner(designer));
    }

    /**
     * Test to check if updating a designer's details works as expected.
     */
    @Test
    public void testUpdateDesignerSuccess() {
        Designer designer = new Designer(1L, "Updated Designer", "Updated Location", 0, 0);

        when(jdbcTemplate.update(anyString(), eq(designer.getDesignerName()), eq(designer.getDesignerLocation()), eq(designer.getTrendCount()), eq(designer.getPopularityScore()), eq(1L))).thenReturn(1);

        boolean result = designerDao.updateDesigner(1L, designer);

        assertTrue(result);
    }


    /**
     * Test to check if a designer can be deleted successfully.
     */
    @Test
    public void testDeleteDesignerSuccess() {
        Long designerId = 1L;

        when(jdbcTemplate.update(anyString(), eq(designerId))).thenReturn(1);

        boolean result = designerDao.deleteDesigner(designerId);

        assertTrue(result);
    }

    /**
     * Test to check if deleting a designer returns false when the designer doesn't exist.
     */
    @Test
    public void testDeleteDesignerFailure() {
        Long designerId = 1L;

        when(jdbcTemplate.update(anyString(), eq(designerId))).thenReturn(0);

        boolean result = designerDao.deleteDesigner(designerId);

        assertFalse(result);
    }

    /**
     * Test to check if designers from a specific location can be fetched correctly.
     */
    @Test
    public void testGetDesignersByLocation() {
        String location = "Location 1";
        List<Designer> expectedDesigners = Arrays.asList(
                new Designer(1L, "Designer 1", location, 5, 90),
                new Designer(3L, "Designer 3", location, 8, 88)
        );

        when(jdbcTemplate.query(anyString(), any(RowMapper.class), eq(location)))
                .thenReturn(expectedDesigners);

        List<Designer> returnedDesigners = designerDao.getDesignersByLocation(location);

        assertEquals(expectedDesigners, returnedDesigners);
    }

    /**
     * Test to check if the trend count for a specific designer can be fetched correctly.
     */
    @Test
    public void testGetDesignerTrendCount() {
        Long designerId = 1L;
        Integer expectedTrendCount = 10;

        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), eq(designerId)))
                .thenReturn(expectedTrendCount);

        Integer trendCount = designerDao.getDesignerTrendCount(designerId);

        assertEquals(expectedTrendCount, trendCount);
    }

    /**
     * Test to check if the popularity score for a specific designer can be fetched correctly.
     */
    @Test
    public void testGetDesignerPopularityScore() {
        Long designerId = 1L;
        Integer expectedPopularityScore = 95;

        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), eq(designerId)))
                .thenReturn(expectedPopularityScore);

        Integer popularityScore = designerDao.getDesignerPopularityScore(designerId);

        assertEquals(expectedPopularityScore, popularityScore);
    }

    /**
     * Test to check if products associated with a specific designer can be fetched correctly.
     */
    @Test
    public void testGetProductsForDesigner() {
        Long designerId = 1L; // Replace with the designer ID you want to test
        List<Product> expectedProducts = Arrays.asList(
                new Product(1L, "Product 1", "Description 1"),
                new Product(2L, "Product 2", "Description 2")
        );

        when(jdbcTemplate.query(anyString(), any(RowMapper.class), eq(designerId))).thenReturn(expectedProducts);

        List<Product> returnedProducts = designerDao.getProductsForDesigner(designerId);

        assertEquals(expectedProducts, returnedProducts);
    }

    /**
     * Cleanup method to reset any mock behavior after each test runs.
     */
    @AfterEach
    public void cleanup() {
        reset(jdbcTemplate);
    }
}

