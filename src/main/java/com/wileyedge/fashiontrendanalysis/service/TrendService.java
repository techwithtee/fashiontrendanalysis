package com.wileyedge.fashiontrendanalysis.service;

import com.wileyedge.fashiontrendanalysis.model.Trend;

import java.util.List;

public interface TrendService {

    List<Trend> getAllTrends();

    Trend getTrendById(Long trendId);

    Long addTrend(Trend trend);

    boolean updateTrend(Long trendId, Trend trend);

    boolean deleteTrend(Long trendId);

    List<Trend> getTrendsByCategory(Long categoryId);

    List<Trend> getTrendsByDesigner(Long designerId);

    List<Trend> getTrendsByLocation(String location);

    List<Trend> getTrendsBySeason(String season);

    Integer getTrendPopularityScore(Long trendId);

    void recordPopularityScore(Long trendId, int popularityScore);

    double calculateTrendPopularityScore(Long trendId); // Add this method
}
