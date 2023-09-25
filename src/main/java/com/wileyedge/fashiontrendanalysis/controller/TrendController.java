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

    @GetMapping
    public ResponseEntity<List<Trend>> getAllTrends() {
        List<Trend> trends = trendService.getAllTrends();
        return new ResponseEntity<>(trends, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trend> getTrendById(@PathVariable Long id) {
        Trend trend = trendService.getTrendById(id);
        if (trend != null) {
            return new ResponseEntity<>(trend, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Long> addTrend(@RequestBody Trend trend) {
        Long trendId = trendService.addTrend(trend);
        return new ResponseEntity<>(trendId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTrend(@PathVariable Long id, @RequestBody Trend trend) {
        boolean updated = trendService.updateTrend(id, trend);
        if (updated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrend(@PathVariable Long id) {
        boolean deleted = trendService.deleteTrend(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Trend>> getTrendsByCategory(@PathVariable Long categoryId) {
        List<Trend> trends = trendService.getTrendsByCategory(categoryId);
        return new ResponseEntity<>(trends, HttpStatus.OK);
    }

    @GetMapping("/designer/{designerId}")
    public ResponseEntity<List<Trend>> getTrendsByDesigner(@PathVariable Long designerId) {
        List<Trend> trends = trendService.getTrendsByDesigner(designerId);
        return new ResponseEntity<>(trends, HttpStatus.OK);
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<Trend>> getTrendsByLocation(@PathVariable String location) {
        List<Trend> trends = trendService.getTrendsByLocation(location);
        return new ResponseEntity<>(trends, HttpStatus.OK);
    }

    @GetMapping("/season/{season}")
    public ResponseEntity<List<Trend>> getTrendsBySeason(@PathVariable String season) {
        List<Trend> trends = trendService.getTrendsBySeason(season);
        return new ResponseEntity<>(trends, HttpStatus.OK);
    }

    @PostMapping("/{trendId}/categories/{categoryId}")
    public ResponseEntity<Void> associateTrendWithCategory(@PathVariable Long trendId, @PathVariable Long categoryId) {
        boolean associated = trendService.associateTrendWithCategory(trendId, categoryId);
        if (associated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

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
