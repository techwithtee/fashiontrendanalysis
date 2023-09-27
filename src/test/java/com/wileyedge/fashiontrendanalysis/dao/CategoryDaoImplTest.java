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

@ExtendWith(MockitoExtension.class)
public class CategoryDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private CategoryDaoImpl categoryDao;

    @BeforeEach
    public void setup() {
        // Any setup required before tests
    }

    @Test
    public void testGetAllCategories() {
        List<Category> expectedCategories = Arrays.asList(
                new Category(1L, "Outerwear"),
                new Category(2L, "Footwear")
        );

        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expectedCategories);

        List<Category> returnedCategories = categoryDao.getAllCategories();

        assertEquals(expectedCategories, returnedCategories);
    }

    @Test
    public void testGetCategoryById() {
        Category expectedCategory = new Category(1L, "Outerwear");

        when(jdbcTemplate.queryForObject(anyString(), eq(new Object[]{1L}), any(RowMapper.class))).thenReturn(expectedCategory);

        Category returnedCategory = categoryDao.getCategoryById(1L);

        assertEquals(expectedCategory, returnedCategory);
    }

    @Test
    public void testAddCategorySuccess() {
        Category category = new Category(null, "Outerwear");

        categoryDao.addCategory(category);

        verify(jdbcTemplate, times(1)).update(any(PreparedStatementCreator.class), any(KeyHolder.class));
    }

    @Test
    public void testAddCategoryFailure() {
        Category category = new Category(null, "Outerwear");

        doThrow(new DataAccessException("Test exception") {})
                .when(jdbcTemplate).update(any(PreparedStatementCreator.class), any(KeyHolder.class));

        assertThrows(CustomUncheckedException.class, () -> categoryDao.addCategory(category));
    }


    @Test
    public void testUpdateCategorySuccess() {
        Category category = new Category(1L, "Footwear");

        when(jdbcTemplate.update(anyString(), anyString(), eq(1L))).thenReturn(1);

        boolean result = categoryDao.updateCategory(1L, category);

        assertTrue(result);
    }

    @Test
    public void testDeleteCategorySuccess() {
        when(jdbcTemplate.update(anyString(), eq(1L))).thenReturn(1);

        boolean result = categoryDao.deleteCategory(1L);

        assertTrue(result);
    }

    @Test
    public void testDeleteCategoryFailure() {
        when(jdbcTemplate.update(anyString(), eq(1L))).thenReturn(0);

        boolean result = categoryDao.deleteCategory(1L);

        assertFalse(result);
    }

    @AfterEach
    public void cleanup() {
        reset(jdbcTemplate);
    }

    @Test
    public void testSetCategoryPopularityForSeason() {
        // Mock behavior
        when(jdbcTemplate.update("INSERT INTO category_popularity (category_id, season, popularity_score) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE popularity_score = ?", 1L, "Spring", 80, 80)).thenReturn(1);
        categoryDao.setCategoryPopularityForSeason(1L, "Spring", 80);

        // Verify interactions
        verify(jdbcTemplate, times(1)).update("INSERT INTO category_popularity (category_id, season, popularity_score) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE popularity_score = ?", 1L, "Spring", 80, 80);
    }

    @Test
    public void testSetCategoryPopularityForSeasonFailure() {
        // Mock behavior for edge case when no rows are affected
        when(jdbcTemplate.update(anyString(), anyLong(), anyString(), anyInt(), anyInt())).thenReturn(0);

        categoryDao.setCategoryPopularityForSeason(1L, "Spring", 90);

        // Verify interactions
        verify(jdbcTemplate, times(1)).update(anyString(), eq(1L), eq("Spring"), eq(90), eq(90));
    }

    @Test
    public void testGetCategoryPopularityForSeason() {
        // Mock behavior
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), anyLong(), anyString())).thenReturn(80);
        int score = categoryDao.getCategoryPopularityForSeason(1L, "Spring");

        // Assertions and Verify interactions
        assertEquals(80, score);
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), eq(Integer.class), anyLong(), anyString());
    }

    @Test
    public void testGetAllCategoryPopularities() {
        // Mock behavior
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(Arrays.asList(80, 85));
        List<Integer> scores = categoryDao.getAllCategoryPopularities(1L);

        // Assertions and Verify interactions
        assertEquals(2, scores.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(Object[].class), any(RowMapper.class));
    }


    @Test
    public void testSetCategoryPopularityForSeasonException() {
        // Mock behavior
        doThrow(new EmptyResultDataAccessException(1)).when(jdbcTemplate).update(
                anyString(), anyLong(), anyString(), anyInt(), anyInt()
        );

        // Assert and Verify interactions
        assertThrows(EmptyResultDataAccessException.class, () -> categoryDao.setCategoryPopularityForSeason(1L, "Spring", 80));
        verify(jdbcTemplate, times(1)).update(anyString(), any(), any(), any(), any());
    }



    @Test
    public void testGetCategoryPopularityForSeasonNoData() {
        // Mock behavior
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), anyLong(), anyString())).thenReturn(null);
        Integer score = categoryDao.getCategoryPopularityForSeason(1L, "Spring");

        // Assertions and Verify interactions
        assertNull(score);
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), eq(Integer.class), anyLong(), anyString());
    }


    @Test
    public void testGetAllCategoryPopularitiesNoData() {
        // Mock behavior
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(new ArrayList<>());
        List<Integer> scores = categoryDao.getAllCategoryPopularities(1L);

        // Assertions and Verify interactions
        assertTrue(scores.isEmpty());
        verify(jdbcTemplate, times(1)).query(anyString(), any(Object[].class), any(RowMapper.class));
    }

}
