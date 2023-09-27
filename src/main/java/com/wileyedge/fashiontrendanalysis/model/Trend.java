package com.wileyedge.fashiontrendanalysis.model;

import java.util.List;

public class Trend {
    private Long trendId;
    private String trendName;
    private String trendDesc;
    private Long categoryId;
    private Long designerId;
    private String location;
    private String season;
    private Integer popularityScore;

    // Default constructor
    public Trend() {
    }

    // Constructor with parameters
    public Trend(Long trendId, String trendName, String trendDesc, Long categoryId, Long designerId, String location, String season, Integer popularityScore) {
        this.trendId = trendId;
        this.trendName = trendName;
        this.trendDesc = trendDesc;
        this.categoryId = categoryId;
        this.designerId = designerId;
        this.location = location;
        this.season = season;
        this.popularityScore = popularityScore;
    }

    public Trend(Object o, String newTrend, String newDescription, long l, long l1, String newLocation, String newSeason) {
    }

    public Trend(Object o, String duplicateTrend, long l, long l1, String description) {
    }
    //Getters and setters

    public Long getTrendId() {
        return trendId;
    }

    public void setTrendId(Long trendId) {
        this.trendId = trendId;
    }

    public String getTrendName() {
        return trendName;
    }

    public void setTrendName(String trendName) {
        this.trendName = trendName;
    }

    public String getTrendDesc() {
        return trendDesc;
    }

    public void setTrendDesc(String trendDesc) {
        this.trendDesc = trendDesc;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getDesignerId() {
        return designerId;
    }

    public void setDesignerId(Long designerId) {
        this.designerId = designerId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    // Getter for popularityScore
    public Integer getPopularityScore() {
        return popularityScore;
    }

    // Setter for popularityScore
    public void setPopularityScore(Integer popularityScore) {
        this.popularityScore = popularityScore;
    }

    // Calculate and return the popularity score for this trend
    public double calculatePopularityScore() {
        // You can implement the logic to calculate the popularity score here.
        // You may use the existing popularityScore field and other attributes of the trend.
        // For example, a simple calculation might be based on likes, comments, and other factors.
        return (double) popularityScore; // Change this with your logic.
    }

    // Calculate and return the average popularity score for a list of trends
    public static double calculateAveragePopularityScore(List<Trend> trends) {
        if (trends == null || trends.isEmpty()) {
            return 0.0; // Return 0 if there are no trends to calculate the average.
        }

        double totalScore = 0.0;
        for (Trend trend : trends) {
            totalScore += trend.calculatePopularityScore();
        }

        return totalScore / trends.size();
    }

}
