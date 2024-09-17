package com.example.project;

public class Address {
    protected String city;
    protected String country;
    protected String state;
    protected String street;
    protected String postalCode;

    public Address(){

    }
    public Address( String city, String country,String street, String state, String postalcode){
        this.city=city;
        this.country=country;
        this.street=street;
        this.state=state;
        this.postalCode=postalcode;

    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getState() {
        return state;
    }

    public String getStreet() {
        return street;
    }

}
