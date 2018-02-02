package com.example.compaq.weatherapp;

/**
 * Created by Compaq on 2/2/2018.
 */

public class GetterSetterClass {
    private  String state;
    private String city;
    private String sublocal;
    private String landmark;
    private String postalcode;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSublocal() {
        return sublocal;
    }

    public void setSublocal(String sublocal) {
        this.sublocal = sublocal;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private String country;


}
