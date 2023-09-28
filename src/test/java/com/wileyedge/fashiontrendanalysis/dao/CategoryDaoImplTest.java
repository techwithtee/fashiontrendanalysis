package com.wileyedge.fashiontrendanalysis.dao;
import com.wileyedge.fashiontrendanalysis.exceptions.CustomUncheckedException;
import com.wileyedge.fashiontrendanalysis.model.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for CategoryDaoImpl. It tests the database operations related to the Category entity.
 */
@ExtendWith(MockitoExtension.class)
public class CategoryDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private CategoryDaoImpl categoryDao;

    @BeforeEach
    public void setup() {
        // Any setup required before each test
    }

    /**
     * Test if all categories can be successfully fetched from the database.
     */
    @Test
    public void testGetAllCategories() {
        // Prepare expected categories
        List<Category> expectedCategories = Arrays.asList(
                new Category(1L, "Outerwear"),
                new Category(2L, "Footwear")
        );

        // Mock behavior of JdbcTemplate
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expectedCategories);

        // Invoke the method to test
        List<Category> returnedCategories = categoryDao.getAllCategories();

        // Assert the result
        assertEquals(expectedCategories, returnedCategories);
    }

    /**
     * Test if a category can be fetched by its ID from the database.
     */
    @Test
    public void testGetCategoryById() {
        // Prepare expected category
        Category expectedCategory = new Category(1L, "Outerwear");

        // Mock behavior of JdbcTemplate
        when(jdbcTemplate.queryForObject(anyString(), eq(new Object[]{1L}), any(RowMapper.class))).thenReturn(expectedCategory);

        // Invoke the method to test
        Category returnedCategory = categoryDao.getCategoryById(1L);

        // Assert the result
        assertEquals(expectedCategory, returnedCategory);
    }

    /**
     * Test if a category can be successfully added to the database.
     */
    @Test
    public void testAddCategorySuccess() {
        // Prepare category data
        Category category = new Category(null, "Outerwear");

        // Invoke the method to test
        categoryDao.addCategory(category);

        // Verify interactions with JdbcTemplate
        verify(jdbcTemplate, times(1)).update(any(PreparedStatementCreator.class), any(KeyHolder.class));
    }

    /**
     * Test if adding a category fails when there's a data access exception.
     */
    @Test
    public void testAddCategoryFailure() {
        // Prepare category data
        Category category = new Category(null, "Outerwear");

        // Mock behavior to throw exception
        doThrow(new DataAccessException("Test exception") {})
                .when(jdbcTemplate).update(any(PreparedStatementCreator.class), any(KeyHolder.class));

        // Assert exception thrown and verify interactions
        assertThrows(CustomUncheckedException.class, () -> categoryDao.addCategory(category));
    }

    /**
     * Test if a category can be successfully updated in the database.
     */
    @Test
    public void testUpdateCategorySuccess() {
        // Prepare category data
        Category category = new Category(1L, "Footwear");

        // Mock behavior of JdbcTemplate
        when(jdbcTemplate.update(anyString(), anyString(), eq(1L))).thenReturn(1);

        // Invoke the method to test
        boolean result = categoryDao.updateCategory(1L, category);

        // Assert the result
        assertTrue(result);
    }

    /**
     * Test if a category can be successfully deleted from the database.
     */
    @Test
    public void testDeleteCategorySuccess() {
        // Mock behavior of JdbcTemplate
        when(jdbcTemplate.update(anyString(), eq(1L))).thenReturn(1);

        // Invoke the method to test
        boolean result = categoryDao.deleteCategory(1L);

        // Assert the result
        assertTrue(result);
    }

    /**
     * Test if the deletion of a non-existent category returns false.
     */
    @Test
    public void testDeleteCategoryFailure() {
        // Mock behavior of JdbcTemplate
        when(jdbcTemplate.update(anyString(), eq(1L))).thenReturn(0);

        // Invoke the method to test
        boolean result = categoryDao.deleteCategory(1L);

        // Assert the result
        assertFalse(result);
    }

    /**
     * Test if the popularity of a category for a specific season can be set in the database.
     */
    @Test
    public void testSetCategoryPopularityForSeason() {
        // Mock behavior of JdbcTemplate
        when(jdbcTemplate.update(anyString(), anyLong(), anyString(), anyInt(), anyInt())).thenReturn(1);

        // Invoke the method to test
        categoryDao.setCategoryPopularityForSeason(1L, "Spring", 80);

        // Verify interactions with JdbcTemplate
        verify(jdbcTemplate, times(1)).update(anyString(), anyLong(), anyString(), anyInt(), anyInt());
    }

    /**
     * Test if setting the popularity of a category for a specific season fails.
     */
    @Test
    public void testSetCategoryPopularityForSeasonFailure() {
        // Mock behavior for edge case when no rows are affected
        when(jdbcTemplate.update(anyString(), anyLong(), anyString(), anyInt(), anyInt())).thenReturn(0);

        // Invoke the method to test
        categoryDao.setCategoryPopularityForSeason(1L, "Spring", 90);

        // Verify interactions with JdbcTemplate
        verify(jdbcTemplate, times(1)).update(anyString(), anyLong(), anyString(), anyInt(), anyInt());
    }

    /**
     * Test if an exception is thrown when setting the popularity of a category for a season encounters a database error.
     */
    @Test
    public void testSetCategoryPopularityForSeasonException() {
        // Mock behavior to throw an exception
        doThrow(new EmptyResultDataAccessException(1)).when(jdbcTemplate).update(
                anyString(), anyLong(), anyString(), anyInt(), anyInt()
        );

        // Assert that the exception is thrown and verify interactions
        assertThrows(EmptyResultDataAccessException.class, () -> categoryDao.setCategoryPopularityForSeason(1L, "Spring", 80));
        verify(jdbcTemplate, times(1)).update(anyString(), any(), any(), any(), any());
    }

    /**
     * Test if fetching the popularity score of a category for a specific season works as expected.
     */
    @Test
    public void testGetCategoryPopularityForSeason() {
        // Mock behavior of JdbcTemplate
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), anyLong(), anyString())).thenReturn(80);

        // Invoke the method to test
        int score = categoryDao.getCategoryPopularityForSeason(1L, "Spring");

        // Assert the result and verify interactions
        assertEquals(80, score);
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), eq(Integer.class), anyLong(), anyString());
    }

    /**
     * Test if fetching the popularity score of a category for a season returns null when no data is present.
     */
    @Test
    public void testGetCategoryPopularityForSeasonNoData() {
        // Mock behavior of JdbcTemplate
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), anyLong(), anyString())).thenReturn(null);

        // Invoke the method to test
        Integer score = categoryDao.getCategoryPopularityForSeason(1L, "Spring");

        // Assert the result and verify interactions
        assertNull(score);
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), eq(Integer.class), anyLong(), anyString());
    }

    /**
     * Test if fetching all popularity scores of a category works as expected.
     */
    @Test
    public void testGetAllCategoryPopularities() {
        // Mock behavior of JdbcTemplate
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(Arrays.asList(80, 85));

        // Invoke the method to test
        List<Integer> scores = categoryDao.getAllCategoryPopularities(1L);

        // Assert the result and verify interactions
        assertEquals(2, scores.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(Object[].class), any(RowMapper.class));
    }

    /**
     * Test if fetching all popularity scores of a category returns an empty list when no data is present.
     */
    @Test
    public void testGetAllCategoryPopularitiesNoData() {
        // Mock behavior of JdbcTemplate
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(new ArrayList<>());

        // Invoke the method to test
        List<Integer> scores = categoryDao.getAllCategoryPopularities(1L);

        // Assert the result and verify interactions
        assertTrue(scores.isEmpty());
        verify(jdbcTemplate, times(1)).query(anyString(), any(Object[].class), any(RowMapper.class));
    }

}
