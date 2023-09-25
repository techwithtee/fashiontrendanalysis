package com.wileyedge.fashiontrendanalysis.dao;

import com.wileyedge.fashiontrendanalysis.exceptions.CustomUncheckedException;
import com.wileyedge.fashiontrendanalysis.model.Designer;
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

@ExtendWith(MockitoExtension.class)
public class DesignerDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private DesignerDaoImpl designerDao;

    @BeforeEach
    public void setup() {
        // Any setup required before tests
    }

    @Test
    public void testGetAllDesigners() {
        List<Designer> expectedDesigners = Arrays.asList(
                new Designer(1L, "Designer 1", "Location 1", 5, 90),
                new Designer(2L, "Designer 2", "Location 2", 10, 85)
        );

        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expectedDesigners);

        List<Designer> returnedDesigners = designerDao.getAllDesigners();

        assertEquals(expectedDesigners, returnedDesigners);
    }

    @Test
    public void testGetDesignerById() {
        Long designerId = 1L;
        Designer expectedDesigner = new Designer(designerId, "Designer 1", "Location 1", 5, 90);

        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), eq(designerId)))
                .thenReturn(expectedDesigner);

        Designer returnedDesigner = designerDao.getDesignerById(designerId);

        assertEquals(expectedDesigner, returnedDesigner);
    }

    @Test
    public void testAddDesignerSuccess() {
        Designer designer = new Designer(null, "New Designer", "New Location", 0, 0);
        Long expectedDesignerId = 3L;

        Map<String, Object> keyHolderMap = new HashMap<>();
        keyHolderMap.put("designer_id", expectedDesignerId);

        when(jdbcTemplate.update(any(PreparedStatementCreator.class), argThat(keyHolder -> keyHolder.equals(keyHolderMap))))
                .thenReturn(1);

        Long designerId = designerDao.addDesigner(designer);

        assertEquals(expectedDesignerId, designerId);
    }

    @Test
    public void testAddDesignerFailure() {
        Designer designer = new Designer(null, "New Designer", "New Location", 0, 0);

        doThrow(new DataAccessException("Test exception") {})
                .when(jdbcTemplate).update(any(PreparedStatementCreator.class), any(KeyHolder.class));

        assertThrows(CustomUncheckedException.class, () -> designerDao.addDesigner(designer));
    }

    @Test
    public void testUpdateDesignerSuccess() {
        Long designerId = 1L;
        Designer designer = new Designer(designerId, "Updated Designer", "Updated Location", 0, 0);

        when(jdbcTemplate.update(anyString(), anyString(), anyString(), eq(designerId))).thenReturn(1);

        boolean result = designerDao.updateDesigner(designerId, designer);

        assertTrue(result);
    }

    @Test
    public void testUpdateDesignerFailure() {
        Long designerId = 1L;
        Designer designer = new Designer(designerId, "Updated Designer", "Updated Location", 0, 0);

        when(jdbcTemplate.update(anyString(), anyString(), anyString(), eq(designerId))).thenReturn(0);

        boolean result = designerDao.updateDesigner(designerId, designer);

        assertFalse(result);
    }

    @Test
    public void testDeleteDesignerSuccess() {
        Long designerId = 1L;

        when(jdbcTemplate.update(anyString(), eq(designerId))).thenReturn(1);

        boolean result = designerDao.deleteDesigner(designerId);

        assertTrue(result);
    }

    @Test
    public void testDeleteDesignerFailure() {
        Long designerId = 1L;

        when(jdbcTemplate.update(anyString(), eq(designerId))).thenReturn(0);

        boolean result = designerDao.deleteDesigner(designerId);

        assertFalse(result);
    }

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

    @Test
    public void testGetDesignerTrendCount() {
        Long designerId = 1L;
        Integer expectedTrendCount = 10;

        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), eq(designerId)))
                .thenReturn(expectedTrendCount);

        Integer trendCount = designerDao.getDesignerTrendCount(designerId);

        assertEquals(expectedTrendCount, trendCount);
    }

    @Test
    public void testGetDesignerPopularityScore() {
        Long designerId = 1L;
        Integer expectedPopularityScore = 95;

        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), eq(designerId)))
                .thenReturn(expectedPopularityScore);

        Integer popularityScore = designerDao.getDesignerPopularityScore(designerId);

        assertEquals(expectedPopularityScore, popularityScore);
    }

    @Test
    public void testGetDesignerTrendCountFailure() {
        Long designerId = 1L;

        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), eq(designerId)))
                .thenThrow(new DataAccessException("Test exception") {});

        assertThrows(CustomUncheckedException.class, () -> designerDao.getDesignerTrendCount(designerId));
    }

    @Test
    public void testGetDesignerPopularityScoreFailure() {
        Long designerId = 1L;

        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), eq(designerId)))
                .thenThrow(new DataAccessException("Test exception") {});

        assertThrows(CustomUncheckedException.class, () -> designerDao.getDesignerPopularityScore(designerId));
    }

    @AfterEach
    public void cleanup() {
        reset(jdbcTemplate);
    }
}
