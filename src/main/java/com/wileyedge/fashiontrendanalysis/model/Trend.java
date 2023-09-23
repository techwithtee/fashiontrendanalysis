package com.wileyedge.fashiontrendanalysis.model;

public class Trend {
    private Long trendId;
    private String trendName;
    private String trendDesc;
    private Long categoryId;
    private Long designerId;
    private String location;
    private String season;

    // Default constructor
    public Trend() {
    }

    // Constructor with parameters
    public Trend(Long trendId, String trendName, String trendDesc, Long categoryId, Long designerId, String location, String season) {
        this.trendId = trendId;
        this.trendName = trendName;
        this.trendDesc = trendDesc;
        this.categoryId = categoryId;
        this.designerId = designerId;
        this.location = location;
        this.season = season;
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

}
