package com.wileyedge.fashiontrendanalysis.service;

import com.wileyedge.fashiontrendanalysis.model.Trend;

import java.util.List;

public interface TrendService {

    List<Trend> getAllTrends();

    Trend getTrendById(Long trendId);

    Long addTrend(Trend trend);

    boolean updateTrend(Long trendId, Trend trend);

    boolean deleteTrend(Long trendId);

    boolean associateTrendWithCategory(Long trendId, Long categoryId);

    boolean dissociateTrendFromCategory(Long trendId, Long categoryId);

    List<Trend> getTrendsByCategory(Long categoryId);

    List<Trend> getTrendsByDesigner(Long designerId);

    List<Trend> getTrendsByLocation(String location);

    List<Trend> getTrendsBySeason(String season);

    boolean setTrendPopularity(Long trendId, int score); // Added setTrendPopularity method

    int getTrendPopularity(Long trendId); // Added getTrendPopularity method

}
