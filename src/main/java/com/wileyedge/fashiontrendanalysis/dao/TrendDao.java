package com.wileyedge.fashiontrendanalysis.dao;

import com.wileyedge.fashiontrendanalysis.model.Trend;

import java.util.List;

public interface TrendDao {

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

    // Add method to calculate popularity score for a trend
    double calculatePopularityScore(Long trendId);

    // Method to record the popularity score for a trend
    void recordPopularityScore(Long trendId, int popularityScore);
}
