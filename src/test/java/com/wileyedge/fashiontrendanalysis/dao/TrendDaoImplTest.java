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
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrendDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private TrendDaoImpl trendDao;

    private final RowMapper<Trend> rowMapper = (rs, rowNum) -> {
        Trend trend = new Trend();
        trend.setTrendId(rs.getLong("trend_id"));
        trend.setTrendName(rs.getString("trend_name"));
        trend.setTrendDesc(rs.getString("trend_desc"));
        trend.setCategoryId(rs.getLong("category_id"));
        trend.setDesignerId(rs.getLong("designer_id"));
        trend.setLocation(rs.getString("location"));
        trend.setSeason(rs.getString("season"));
        return trend;
    };

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

        when(jdbcTemplate.query(anyString(), eq(rowMapper))).thenReturn(expectedTrends);

        List<Trend> returnedTrends = trendDao.getAllTrends();

        assertEquals(expectedTrends, returnedTrends);
    }

    @Test
    public void testGetTrendById() {
        Trend expectedTrend = new Trend(1L, "Trend 1", "Description 1", 1L, 1L, "Location 1", "Season 1");

        when(jdbcTemplate.queryForObject(anyString(), eq(new Object[]{1L}), eq(rowMapper))).thenReturn(expectedTrend);

        Trend returnedTrend = trendDao.getTrendById(1L);

        assertEquals(expectedTrend, returnedTrend);
    }

    @Test
    public void testAddTrendSuccess() {
        Trend trend = new Trend(null, "New Trend", "New Description", 1L, 1L, "New Location", "New Season");
        Long expectedTrendId = 3L;

        Map<String, Object> keyHolderMap = new HashMap<>();
        keyHolderMap.put("trend_id", expectedTrendId);

        GeneratedKeyHolder actualKeyHolder = new GeneratedKeyHolder();

        when(jdbcTemplate.update(any(PreparedStatementCreator.class), argThat(keyHolder -> {
            return keyHolder.equals(actualKeyHolder);
        }))).thenReturn(1);

        Long trendId = trendDao.addTrend(trend);

        assertEquals(expectedTrendId, trendId);
    }


    @Test
    public void testAddTrendFailure() {
        Trend trend = new Trend(null, "New Trend", "New Description", 1L, 1L, "New Location", "New Season");

        doThrow(new DataAccessException("Test exception") {})
                .when(jdbcTemplate).update(any(PreparedStatementCreator.class), any(KeyHolder.class));

        assertThrows(CustomUncheckedException.class, () -> trendDao.addTrend(trend));
    }

    @Test
    public void testUpdateTrendSuccess() {
        Trend trend = new Trend(1L, "Updated Trend", "Updated Description", 1L, 1L, "Updated Location", "Updated Season");

        when(jdbcTemplate.update(anyString(), anyString(), anyString(), eq(1L))).thenReturn(1);

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

    @AfterEach
    public void cleanup() {
        reset(jdbcTemplate);
    }
}
