package com.wileyedge.fashiontrendanalysis.controller;

import com.wileyedge.fashiontrendanalysis.model.Trend;
import com.wileyedge.fashiontrendanalysis.service.TrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trends")
public class TrendController {

    private final TrendService trendService;

    @Autowired
    public TrendController(TrendService trendService) {
        this.trendService = trendService;
    }

    /**
     * Fetches all trends.
     *
     * @return a list of all trends
     * @apiEndpoint GET http://localhost:6363/api/trends
     */
    @GetMapping
    public ResponseEntity<List<Trend>> getAllTrends() {
        List<Trend> trends = trendService.getAllTrends();
        return new ResponseEntity<>(trends, HttpStatus.OK);
    }

    /**
     * Retrieves a specific trend by its ID.
     *
     * @param id the ID of the trend to retrieve
     * @return the trend with the specified ID, or 404 if not found
     * @apiEndpoint GET http://localhost:6363/api/trends/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Trend> getTrendById(@PathVariable Long id) {
        Trend trend = trendService.getTrendById(id);
        if (trend != null) {
            return new ResponseEntity<>(trend, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Adds a new trend.
     *
     * @param trend the trend to add
     * @return the ID of the newly created trend
     * @apiEndpoint POST http://localhost:6363/api/trends
     */
    @PostMapping
    public ResponseEntity<Long> addTrend(@RequestBody Trend trend) {
        Long trendId = trendService.addTrend(trend);
        return new ResponseEntity<>(trendId, HttpStatus.CREATED);
    }

    /**
     * Updates a trend by its ID.
     *
     * @param id the ID of the trend to update
     * @param trend the updated trend details
     * @return a confirmation of the update
     * @apiEndpoint PUT http://localhost:6363/api/trends/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTrend(@PathVariable Long id, @RequestBody Trend trend) {
        boolean updated = trendService.updateTrend(id, trend);
        if (updated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes a trend by its ID.
     *
     * @param id the ID of the trend to delete
     * @return a confirmation of the deletion
     * @apiEndpoint DELETE http://localhost:6363/api/trends/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrend(@PathVariable Long id) {
        boolean deleted = trendService.deleteTrend(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Fetches trends associated with a specific category.
     *
     * @param categoryId the ID of the category
     * @return a list of trends by the specified category
     * @apiEndpoint GET http://localhost:6363/api/trends/category/{categoryId}
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Trend>> getTrendsByCategory(@PathVariable Long categoryId) {
        List<Trend> trends = trendService.getTrendsByCategory(categoryId);
        return new ResponseEntity<>(trends, HttpStatus.OK);
    }

    /**
     * Fetches trends designed by a specific designer.
     *
     * @param designerId the ID of the designer
     * @return a list of trends by the specified designer
     * @apiEndpoint GET http://localhost:6363/api/trends/designer/{designerId}
     */
    @GetMapping("/designer/{designerId}")
    public ResponseEntity<List<Trend>> getTrendsByDesigner(@PathVariable Long designerId) {
        List<Trend> trends = trendService.getTrendsByDesigner(designerId);
        return new ResponseEntity<>(trends, HttpStatus.OK);
    }

    /**
     * Fetches trends from a specific location.
     *
     * @param location the location name
     * @return a list of trends from the specified location
     * @apiEndpoint GET http://localhost:6363/api/trends/location/{location}
     */
    @GetMapping("/location/{location}")
    public ResponseEntity<List<Trend>> getTrendsByLocation(@PathVariable String location) {
        List<Trend> trends = trendService.getTrendsByLocation(location);
        return new ResponseEntity<>(trends, HttpStatus.OK);
    }

    /**
     * Fetches trends from a specific season.
     *
     * @param season the season name
     * @return a list of trends from the specified season
     * @apiEndpoint GET http://localhost:6363/api/trends/season/{season}
     */
    @GetMapping("/season/{season}")
    public ResponseEntity<List<Trend>> getTrendsBySeason(@PathVariable String season) {
        List<Trend> trends = trendService.getTrendsBySeason(season);
        return new ResponseEntity<>(trends, HttpStatus.OK);
    }

    /**
     * Retrieves the popularity score of a specific trend.
     *
     * @param id the ID of the trend
     * @return the popularity score of the trend, or 404 if not found
     * @apiEndpoint GET http://localhost:6363/api/trends/{id}/popularityScore
     */
    @GetMapping("/{id}/popularityScore")
    public ResponseEntity<Integer> getTrendPopularityScore(@PathVariable Long id) {
        Integer popularityScore = trendService.getTrendPopularityScore(id);
        if (popularityScore != null) {
            return new ResponseEntity<>(popularityScore, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Records the popularity score for a specific trend.
     *
     * @param id the ID of the trend
     * @param popularityScore the popularity score to be recorded
     * @return a confirmation of the recording
     * @apiEndpoint POST http://localhost:6363/api/trends/{id}/recordPopularityScore
     */
    @PostMapping("/{id}/recordPopularityScore")
    public ResponseEntity<Void> recordPopularityScore(@PathVariable Long id, @RequestParam int popularityScore) {
        trendService.recordPopularityScore(id, popularityScore);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Associates a trend with a category.
     *
     * @param trendId the ID of the trend
     * @param categoryId the ID of the category
     * @return a confirmation of the association
     * @apiEndpoint POST http://localhost:6363/api/trends/{trendId}/categories/{categoryId}
     */
    @PostMapping("/{trendId}/categories/{categoryId}")
    public ResponseEntity<Void> associateTrendWithCategory(@PathVariable Long trendId, @PathVariable Long categoryId) {
        boolean associated = trendService.associateTrendWithCategory(trendId, categoryId);
        if (associated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Dissociates a trend from a category.
     *
     * @param trendId the ID of the trend
     * @param categoryId the ID of the category
     * @return a confirmation of the dissociation
     * @apiEndpoint DELETE http://localhost:6363/api/trends/{trendId}/categories/{categoryId}
     */
    @DeleteMapping("/{trendId}/categories/{categoryId}")
    public ResponseEntity<Void> dissociateTrendFromCategory(@PathVariable Long trendId, @PathVariable Long categoryId) {
        boolean dissociated = trendService.dissociateTrendFromCategory(trendId, categoryId);
        if (dissociated) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

