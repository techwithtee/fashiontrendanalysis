package com.wileyedge.fashiontrendanalysis.model;

public class Designer {
    private Long designerId;
    private String designerName;
    private String designerLocation;

    // Default constructor
    public Designer() {

    }


    // Constructor with parameters
    public Designer(Long designerId, String designerName, String designerLocation) {
        this.designerId = designerId;
        this.designerName = designerName;
        this.designerLocation = designerLocation;
    }

    // Getter for designerId
    public Long getDesignerId() {
        return designerId;
    }

    // Setter for designerId
    public void setDesignerId(Long designerId) {
        this.designerId = designerId;
    }

    // Getter for designerName
    public String getDesignerName() {
        return designerName;
    }

    // Setter for designerName
    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    // Getter for designerLocation
    public String getDesignerLocation() {
        return designerLocation;
    }

    // Setter for designerLocation
    public void setDesignerLocation(String designerLocation) {
        this.designerLocation = designerLocation;
    }

}
