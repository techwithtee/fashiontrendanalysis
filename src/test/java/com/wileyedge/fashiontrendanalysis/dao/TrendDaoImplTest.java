package com.wileyedge.fashiontrendanalysis.dao;

import com.wileyedge.fashiontrendanalysis.model.Trend;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for the TrendDaoImpl.
 * It tests various CRUD operations related to the Trend entity.
 */
@ExtendWith(MockitoExtension.class)
public class TrendDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private TrendDaoImpl trendDao;

    /**
     * Setup method for initializing any necessary data or configurations before the tests run.
     */
    @BeforeEach
    public void setup() {
        // Any setup required before tests
    }

    /**
     * Test to ensure all trends can be fetched correctly.
     */
    @Test
    public void testGetAllTrends() {
        List<Trend> expectedTrends = Arrays.asList(
                new Trend(Optional.of(1L), "Trend 1", "Description 1", 1L, 1L, "Location 1", "Season 1"),
                new Trend(Optional.of(2L), "Trend 2", "Description 2", 2L, 2L, "Location 2", "Season 2")
        );

        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expectedTrends);

        List<Trend> returnedTrends = trendDao.getAllTrends();

        assertEquals(expectedTrends, returnedTrends);
    }

    /**
     * Test to ensure that a trend can be fetched by its ID.
     */
    @Test
    public void testGetTrendById() {
        Trend expectedTrend = new Trend(Optional.of(1L), "Trend 1", "Description 1", 1L, 1L, "Location 1", "Season 1");

        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(expectedTrend);

        Trend returnedTrend = trendDao.getTrendById(1L);

        assertEquals(expectedTrend, returnedTrend);
    }

    /**
     * Test to ensure a new trend can be added successfully.
     */
    @Test
    public void testAddTrendSuccess() {
        Trend trend = new Trend(null, "New Trend", "New Description", 1L, 1L, "New Location", "New Season");


        when(jdbcTemplate.update(anyString(), eq(trend.getTrendName()), eq(trend.getTrendDesc()), eq(trend.getCategoryId()), eq(trend.getDesignerId()), eq(trend.getLocation()), eq(trend.getSeason()))).thenReturn(1);
        when(jdbcTemplate.queryForObject(anyString(), eq(Long.class))).thenReturn(1L);

        Long trendId = trendDao.addTrend(trend);

        assertEquals(1l, trendId);
    }

    /**
     * Test to ensure that a trend's details can be updated.
     */
    @Test
    public void testUpdateTrendSuccess() {
        Trend trend = new Trend(Optional.of(1L), "Updated Trend", "Updated Description", 1L, 1L, "Updated Location", "Updated Season");

        // Stubbing the jdbcTemplate.update method
        when(jdbcTemplate.update(
                "UPDATE trend SET trend_name=?, trend_desc=?, category_id=?, designer_id=?, location=?, season=? WHERE trend_id=?",
                null, null, null, null, null, null, 1L))
                .thenReturn(1); // You can change the return value as needed

        boolean result = trendDao.updateTrend(1L, trend);

        assertTrue(result);
    }

    /**
     * Test to ensure a trend can be deleted successfully.
     */
    @Test
    public void testDeleteTrendSuccess() {
        when(jdbcTemplate.update(anyString(), eq(1L))).thenReturn(1);

        boolean result = trendDao.deleteTrend(1L);

        assertTrue(result);
    }

    /**
     * Test to ensure a trend cannot be deleted when it doesn't exist.
     */
    @Test
    public void testDeleteTrendFailure() {
        when(jdbcTemplate.update(anyString(), eq(1L))).thenReturn(0);

        boolean result = trendDao.deleteTrend(1L);

        assertFalse(result);
    }

    /**
     * Test to handle scenarios where the requested trend doesn't exist.
     */
    @Test
    public void testRetrieveNonExistingTrend() {
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(null);

        Trend result = trendDao.getTrendById(999L);
        assertNull(result);
    }

    /**
     * Test to fetch all trends associated with a specific category.
     */
    @Test
    public void testGetTrendsByCategory() {
        List<Trend> expectedTrends = Arrays.asList(
                new Trend(Optional.of(1L), "Trend 1", "Description 1", 1L, 1L, "Location 1", "Season 1"),
                new Trend(Optional.of(2L), "Trend 2", "Description 2", 2L, 2L, "Location 2", "Season 2")
        );

        when(jdbcTemplate.query(anyString(), any(RowMapper.class), eq(1L))).thenReturn(expectedTrends);

        List<Trend> returnedTrends = trendDao.getTrendsByCategory(1L);

        assertEquals(expectedTrends, returnedTrends);
    }

    /**
     * Test to fetch all trends designed by a specific designer.
     */
    @Test
    public void testGetTrendsByDesigner() {
        List<Trend> expectedTrends = Arrays.asList(
                new Trend(Optional.of(1L), "Trend 1", "Description 1", 1L, 1L, "Location 1", "Season 1"),
                new Trend(Optional.of(2L), "Trend 2", "Description 2", 2L, 2L, "Location 2", "Season 2")
        );

        when(jdbcTemplate.query(anyString(), any(RowMapper.class), eq(1L))).thenReturn(expectedTrends);

        List<Trend> returnedTrends = trendDao.getTrendsByDesigner(1L);

        assertEquals(expectedTrends, returnedTrends);
    }

    /**
     * Test to fetch all trends associated with a specific location.
     */
    @Test
    public void testGetTrendsByLocation() {
        List<Trend> expectedTrends = Arrays.asList(
                new Trend(Optional.of(1L), "Trend 1", "Description 1", 1L, 1L, "Location 1", "Season 1"),
                new Trend(Optional.of(2L), "Trend 2", "Description 2", 2L, 2L, "Location 2", "Season 2")
        );

        when(jdbcTemplate.query(anyString(), any(RowMapper.class), eq("Location 1"))).thenReturn(expectedTrends);

        List<Trend> returnedTrends = trendDao.getTrendsByLocation("Location 1");

        assertEquals(expectedTrends, returnedTrends);
    }

    /**
     * Test to fetch all trends for a specific season.
     */
    @Test
    public void testGetTrendsBySeason() {
        List<Trend> expectedTrends = Arrays.asList(
                new Trend(Optional.of(1L), "Trend 1", "Description 1", 1L, 1L, "Location 1", "Season 1"),
                new Trend(Optional.of(2L), "Trend 2", "Description 2", 2L, 2L, "Location 2", "Season 2")
        );

        when(jdbcTemplate.query(anyString(), any(RowMapper.class), eq("Season 1"))).thenReturn(expectedTrends);

        List<Trend> returnedTrends = trendDao.getTrendsBySeason("Season 1");

        assertEquals(expectedTrends, returnedTrends);
    }

    /**
     * Test to ensure a trend can be associated with a category.
     */
    @Test
    public void testAssociateTrendWithCategorySuccess() {
        when(jdbcTemplate.update(anyString(), eq(1L), eq(2L))).thenReturn(1);

        boolean result = trendDao.associateTrendWithCategory(1L, 2L);

        assertTrue(result);
    }

    /**
     * Test to handle scenarios where a trend can't be associated with a category.
     */
    @Test
    public void testAssociateTrendWithCategoryFailure() {
        when(jdbcTemplate.update(anyString(), eq(1L), eq(2L))).thenReturn(0);

        boolean result = trendDao.associateTrendWithCategory(1L, 2L);

        assertFalse(result);
    }

    /**
     * Test to ensure a trend can be dissociated from a category.
     */

    @Test
    public void testDissociateTrendFromCategorySuccess() {
        when(jdbcTemplate.update(anyString(), eq(1L), eq(2L))).thenReturn(1);

        boolean result = trendDao.dissociateTrendFromCategory(1L, 2L);

        assertTrue(result);
    }

    /**
     * Test to handle scenarios where a trend can't be dissociated from a category.
     */
    @Test
    public void testDissociateTrendFromCategoryFailure() {
        when(jdbcTemplate.update(anyString(), eq(1L), eq(2L))).thenReturn(0);

        boolean result = trendDao.dissociateTrendFromCategory(1L, 2L);

        assertFalse(result);
    }

    /**
     * Test to set the popularity score for a trend.
     */
    @Test
    public void testSetTrendPopularitySuccess() {
        when(jdbcTemplate.update(anyString(), eq(1), eq(1L))).thenReturn(1);

        boolean result = trendDao.setTrendPopularity(1L, 1);

        assertTrue(result);
    }

    /**
     * Test to handle scenarios where setting the popularity score for a trend fails.
     */
    @Test
    public void testSetTrendPopularityFailure() {
        when(jdbcTemplate.update(anyString(), eq(1), eq(1L))).thenReturn(0);

        boolean result = trendDao.setTrendPopularity(1L, 1);

        assertFalse(result);
    }

    /**
     * Test to fetch the popularity score of a trend.
     */
    @Test
    public void testGetTrendPopularity() {
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), eq(1L))).thenReturn(5);

        int popularity = trendDao.getTrendPopularity(1L);

        assertEquals(5, popularity);
    }


    /**
     * Cleanup method to reset any mock behavior after each test runs.
     */
    @AfterEach
    public void cleanup() {
        reset(jdbcTemplate);
    }

    // Add tests for other TrendDao methods like getTrendsByCategory, getTrendsByDesigner, etc.
}
