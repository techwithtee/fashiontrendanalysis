package com.wileyedge.fashiontrendanalysis.dao;

import com.wileyedge.fashiontrendanalysis.exceptions.CustomUncheckedException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrendDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private TrendDaoImpl trendDao;


    @BeforeEach
    public void setup() {
        // Any setup required before tests
    }

    @Test
    public void testGetAllTrends() {
        List<Trend> expectedTrends = Arrays.asList(
                new Trend(1L, "Trend 1", "Description 1", 1L, 1L, "Location 1", "Season 1"),
                new Trend(2L, "Trend 2", "Description 2", 2L, 2L, "Location 2", "Season 2")
        );

        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expectedTrends);

        List<Trend> returnedTrends = trendDao.getAllTrends();

        assertEquals(expectedTrends, returnedTrends);
    }

    @Test
    public void testGetTrendById() {
        Trend expectedTrend = new Trend(1L, "Trend 1", "Description 1", 1L, 1L, "Location 1", "Season 1");

        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(expectedTrend);

        Trend returnedTrend = trendDao.getTrendById(1L);

        assertEquals(expectedTrend, returnedTrend);
    }

    @Test
    public void testAddTrendSuccess() {
        Trend trend = new Trend(null, "New Trend", "New Description", 1L, 1L, "New Location", "New Season");


        when(jdbcTemplate.update(anyString(), eq(trend.getTrendName()), eq(trend.getTrendDesc()), eq(trend.getCategoryId()), eq(trend.getDesignerId()), eq(trend.getLocation()), eq(trend.getSeason()))).thenReturn(1);
        when(jdbcTemplate.queryForObject(anyString(), eq(Long.class))).thenReturn(1L);

        Long trendId = trendDao.addTrend(trend);

        assertEquals(1l, trendId);
    }


    @Test
    public void testUpdateTrendSuccess() {
        Trend trend = new Trend(1L, "Updated Trend", "Updated Description", 1L, 1L, "Updated Location", "Updated Season");

        // Stubbing the jdbcTemplate.update method
        when(jdbcTemplate.update(
                "UPDATE trend SET trend_name=?, trend_desc=?, category_id=?, designer_id=?, location=?, season=? WHERE trend_id=?",
                null, null, null, null, null, null, 1L))
                .thenReturn(1); // You can change the return value as needed

        boolean result = trendDao.updateTrend(1L, trend);

        assertTrue(result);
    }

    @Test
    public void testDeleteTrendSuccess() {
        when(jdbcTemplate.update(anyString(), eq(1L))).thenReturn(1);

        boolean result = trendDao.deleteTrend(1L);

        assertTrue(result);
    }

    @Test
    public void testDeleteTrendFailure() {
        when(jdbcTemplate.update(anyString(), eq(1L))).thenReturn(0);

        boolean result = trendDao.deleteTrend(1L);

        assertFalse(result);
    }

    @Test
    public void testRetrieveNonExistingTrend() {
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(null);

        Trend result = trendDao.getTrendById(999L);
        assertNull(result);
    }


    @AfterEach
    public void cleanup() {
        reset(jdbcTemplate);
    }

    // Add tests for other TrendDao methods like getTrendsByCategory, getTrendsByDesigner, etc.
}
