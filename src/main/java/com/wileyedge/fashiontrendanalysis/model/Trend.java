package com.wileyedge.fashiontrendanalysis.model;

import java.util.List;

/**
 * Represents a fashion trend with various attributes such as name, description, category, designer, etc.
 */
public class Trend {
    private Long trendId;
    private String trendName;
    private String trendDesc;
    private Long categoryId;
    private Long designerId;
    private String location;
    private String season;
    private Integer popularityScore;

    /**
     * Default constructor for Trend.
     */
    public Trend() {
    }

    /**
     * Constructs a Trend instance with the provided parameters.
     *
     * @param trendId         The unique identifier for the trend.
     * @param trendName       The name of the trend.
     * @param trendDesc       The description of the trend.
     * @param categoryId      The identifier for the category to which this trend belongs.
     * @param designerId      The identifier for the designer who created this trend.
     * @param location        The location where this trend is popular.
     * @param season          The season during which this trend is popular.
     * @param popularityScore The score indicating the popularity of this trend.
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
     * Alternative constructor for Trend with different parameters.
     * Note: This constructor does not initialize any fields.
     *
     * @param o          Object type (currently unused).
     * @param newTrend   The new trend name.
     * @param newDescription The new trend description.
     * @param l          Long type (currently unused).
     * @param l1         Another Long type (currently unused).
     * @param newLocation The new location.
     * @param newSeason  The new season.
     */
    public Trend(Object o, String newTrend, String newDescription, long l, long l1, String newLocation, String newSeason) {
    }

    /**
     * Another alternative constructor for Trend with a different set of parameters.
     * Note: This constructor does not initialize any fields.
     *
     * @param o              Object type (currently unused).
     * @param duplicateTrend A duplicate trend name.
     * @param l              Long type (currently unused).
     * @param l1             Another Long type (currently unused).
     * @param description    The trend description.
     */
    public Trend(Object o, String duplicateTrend, long l, long l1, String description) {
    }
    //Getters and setters
    /**
     * Gets the unique identifier of the trend.
     *
     * @return the trend ID.
     */
    public Long getTrendId() {
        return trendId;
    }

    /**
     * Sets the unique identifier of the trend.
     *
     * @param trendId The ID to set for this trend.
     */
    public void setTrendId(Long trendId) {
        this.trendId = trendId;
    }

    /**
     * Gets the name of the trend.
     *
     * @return the trend name.
     */
    public String getTrendName() {
        return trendName;
    }

    /**
     * Sets the name of the trend.
     *
     * @param trendName The name to set for this trend.
     */
    public void setTrendName(String trendName) {
        this.trendName = trendName;
    }

    /**
     * Gets the description of the trend.
     *
     * @return the trend description.
     */
    public String getTrendDesc() {
        return trendDesc;
    }

    /**
     * Sets the description of the trend.
     *
     * @param trendDesc The description to set for this trend.
     */
    public void setTrendDesc(String trendDesc) {
        this.trendDesc = trendDesc;
    }

    /**
     * Gets the category ID associated with this trend.
     *
     * @return the category ID.
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the category ID for this trend.
     *
     * @param categoryId The category ID to set for this trend.
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Gets the designer ID associated with this trend.
     *
     * @return the designer ID.
     */
    public Long getDesignerId() {
        return designerId;
    }

    /**
     * Sets the designer ID for this trend.
     *
     * @param designerId The designer ID to set for this trend.
     */
    public void setDesignerId(Long designerId) {
        this.designerId = designerId;
    }

    /**
     * Gets the location where this trend is popular.
     *
     * @return the location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location where this trend is popular.
     *
     * @param location The location to set for this trend.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the season during which this trend is popular.
     *
     * @return the season.
     */
    public String getSeason() {
        return season;
    }

    /**
     * Sets the season during which this trend is popular.
     *
     * @param season The season to set for this trend.
     */
    public void setSeason(String season) {
        this.season = season;
    }

    /**
     * Gets the popularity score of this trend.
     *
     * @return the popularity score.
     */
    public Integer getPopularityScore() {
        return popularityScore;
    }

    /**
     * Sets the popularity score for this trend.
     *
     * @param popularityScore The popularity score to set for this trend.
     */
    public void setPopularityScore(Integer popularityScore) {
        this.popularityScore = popularityScore;
    }

    /**
     * Calculates the popularity score for this trend based on various factors.
     * This method can be further enhanced to include factors like likes, comments, etc.
     *
     * @return A calculated score representing the popularity of this trend.
     */
    public double calculatePopularityScore() {
        // Implement the logic to calculate the popularity score here.
        // Use the existing popularityScore field and other attributes of the trend.
        return (double) popularityScore; // Modify this with your logic.
    }

    /**
     * Calculates the average popularity score for a list of trends.
     *
     * @param trends The list of trends for which the average popularity score is to be calculated.
     * @return A double representing the average popularity score.
     */
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
