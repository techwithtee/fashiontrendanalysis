package com.wileyedge.fashiontrendanalysis.model;

/**
 * Represents a designer in the fashion trend analysis system.
 * Contains information such as designer's name, location, trend count, and popularity score.
 *
 * @author [Your Name]
 * @version 1.0
 */
public class Designer {
    private Long designerId;
    private String designerName;
    private String designerLocation;
    private Integer trendCount;
    private Integer popularityScore;

    /**
     * Default constructor, initializes a new empty Designer object.
     */
    public Designer() {

    }

    /**
     * Parameterized constructor.
     *
     * @param designerId       The unique ID of the designer.
     * @param designerName     The name of the designer.
     * @param designerLocation The location where the designer is based.
     * @param trendCount       The count of trends associated with the designer.
     * @param popularityScore  The popularity score of the designer.
     */
    public Designer(Long designerId, String designerName, String designerLocation, Integer trendCount, Integer popularityScore) {
        this.designerId = designerId;
        this.designerName = designerName;
        this.designerLocation = designerLocation;
        this.trendCount = trendCount;
        this.popularityScore = popularityScore;
    }

    /**
     * @return The unique ID of the designer.
     */
    public Long getDesignerId() {
        return designerId;
    }

    /**
     * Sets the ID for the designer.
     *
     * @param designerId The unique ID to set for the designer.
     */
    public void setDesignerId(Long designerId) {
        this.designerId = designerId;
    }

    /**
     * @return The name of the designer.
     */
    public String getDesignerName() {
        return designerName;
    }

    /**
     * Sets the name for the designer.
     *
     * @param designerName The name to set for the designer.
     */
    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    /**
     * @return The location where the designer is based.
     */
    public String getDesignerLocation() {
        return designerLocation;
    }

    /**
     * Sets the location for the designer.
     *
     * @param designerLocation The location to set for the designer.
     */
    public void setDesignerLocation(String designerLocation) {
        this.designerLocation = designerLocation;
    }

    /**
     * @return The count of trends associated with the designer.
     */
    public Integer getTrendCount() {
        return trendCount;
    }

    /**
     * Sets the trend count for the designer.
     *
     * @param trendCount The trend count to set for the designer.
     */
    public void setTrendCount(Integer trendCount) {
        this.trendCount = trendCount;
    }

    /**
     * @return The popularity score of the designer.
     */
    public Integer getPopularityScore() {
        return popularityScore;
    }

    /**
     * Sets the popularity score for the designer.
     *
     * @param popularityScore The popularity score to set for the designer.
     */
    public void setPopularityScore(Integer popularityScore) {
        this.popularityScore = popularityScore;
    }
}
