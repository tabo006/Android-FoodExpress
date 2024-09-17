package com.example.project;

public class Complaint {
    private String id;
    private String description;
    private String customerId,cookId;

    public Complaint() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Complaint(String description, String customerID, String cookId) {
        this.description = description;
        this.customerId = customerID;
        this.cookId = cookId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomerID() {
        return customerId;
    }

    public void setCustomerID(String customerID) {
        this.customerId = customerID;
    }

    public String getCookId() {
        return cookId;
    }

    public void setCookId(String cookId) {
        this.cookId = cookId;
    }
}
