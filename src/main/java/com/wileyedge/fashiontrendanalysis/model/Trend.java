package com.wileyedge.fashiontrendanalysis.model;

import java.util.List;

/**
 * Represents a fashion trend within the system, encapsulating attributes such as its unique ID,
 * name, description, associated category, designer, location, season, and popularity score.
 */
public class Trend {

    /** Unique identifier for the trend. */
    private Long trendId;

    /** Name of the trend. */
    private String trendName;

    /** Description of the trend. */
    private String trendDesc;

    /** Identifier for the associated category. */
    private Long categoryId;

    /** Identifier for the associated designer. */
    private Long designerId;

    /** Location where the trend is prominent. */
    private String location;

    /** Season during which the trend is relevant. */
    private String season;

    /** Popularity score of the trend. */
    private Integer popularityScore;

    /** Default constructor. */
    public Trend() {
    }

    /**
     * Parameterized constructor to initialize the trend with specified attributes.
     *
     * @param trendId the unique identifier for the trend
     * @param trendName the name of the trend
     * @param trendDesc the description of the trend
     * @param categoryId the identifier for the associated category
     * @param designerId the identifier for the associated designer
     * @param location the location where the trend is prominent
     * @param season the season during which the trend is relevant
     * @param popularityScore the popularity score of the trend
     */
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

    /**
     * Constructs a Trend instance with specified parameters.
     * @param o An unspecified object parameter. Its use is unclear from the context.
     * @param newTrend The name of the trend.
     * @param newDescription The description of the trend.
     * @param l A numeric identifier, possibly related to category. Its exact use is unclear.
     * @param l1 A numeric identifier, possibly related to designer. Its exact use is unclear.
     * @param newLocation The location associated with the trend.
     * @param newSeason The season associated with the trend.
     */
    public Trend(Object o, String newTrend, String newDescription, long l, long l1, String newLocation, String newSeason) {
    }

    /**
     * Constructs a Trend instance with specified parameters.
     * @param o An unspecified object parameter. Its use is unclear from the context.
     * @param duplicateTrend The name of the trend.
     * @param l A numeric identifier, possibly related to category. Its exact use is unclear.
     * @param l1 A numeric identifier, possibly related to designer. Its exact use is unclear.
     * @param description The description of the trend.
     */
    public Trend(Object o, String duplicateTrend, long l, long l1, String description) {
    }

    // Getters and setters

    /**
     * @return the unique identifier for the trend.
     */
    public Long getTrendId() {
        return trendId;
    }

    /**
     * Sets the unique identifier for the trend.
     * @param trendId The unique identifier to set.
     */
    public void setTrendId(Long trendId) {
        this.trendId = trendId;
    }

    /**
     * @return the name of the trend.
     */
    public String getTrendName() {
        return trendName;
    }

    /**
     * Sets the name of the trend.
     * @param trendName The name to set.
     */
    public void setTrendName(String trendName) {
        this.trendName = trendName;
    }

    /**
     * @return the description of the trend.
     */
    public String getTrendDesc() {
        return trendDesc;
    }

    /**
     * Sets the description of the trend.
     * @param trendDesc The description to set.
     */
    public void setTrendDesc(String trendDesc) {
        this.trendDesc = trendDesc;
    }

    /**
     * @return the identifier for the associated category.
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the identifier for the associated category.
     * @param categoryId The identifier to set.
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return the identifier for the associated designer.
     */
    public Long getDesignerId() {
        return designerId;
    }

    /**
     * Sets the identifier for the associated designer.
     * @param designerId The identifier to set.
     */
    public void setDesignerId(Long designerId) {
        this.designerId = designerId;
    }

    /**
     * @return the location associated with the trend.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location associated with the trend.
     * @param location The location to set.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the season associated with the trend.
     */
    public String getSeason() {
        return season;
    }

    /**
     * Sets the season associated with the trend.
     * @param season The season to set.
     */
    public void setSeason(String season) {
        this.season = season;
    }

    /**
     * @return the popularity score of the trend.
     */
    public Integer getPopularityScore() {
        return popularityScore;
    }

    /**
     * Sets the popularity score for the trend.
     * @param popularityScore The popularity score to set.
     */
    public void setPopularityScore(Integer popularityScore) {
        this.popularityScore = popularityScore;
    }

    /**
     * Calculates and returns the popularity score for this trend.
     * The logic for calculating the score might be based on likes, comments, and other factors.
     * @return the calculated popularity score for the trend.
     */
    public double calculatePopularityScore() {
        // You can implement the logic to calculate the popularity score here.
        // You may use the existing popularityScore field and other attributes of the trend.
        // For example, a simple calculation might be based on likes, comments, and other factors.
        return (double) popularityScore; // Change this with your logic.
    }

    /**
     * Calculates and returns the average popularity score for a list of trends.
     * @param trends The list of trends for which to calculate the average popularity score.
     * @return the average popularity score, or 0.0 if the list of trends is null or empty.
     */
    public static double calculateAveragePopularityScore(List<Trend> trends) {
        if (trends == null || trends.isEmpty()) {
            return 0.0;
        }

        double totalScore = 0.0;
        for (Trend trend : trends) {
            totalScore += trend.calculatePopularityScore();
        }

        return totalScore / trends.size();
    }

}
