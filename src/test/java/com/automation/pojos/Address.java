package com.automation.pojos;

/*
 "address": {
            "addressId": 11653,
            "street": "7777 NO NAME  Dr",
            "city": "Boston",
            "state": "Massachusetts",
            "zipCode": 22102
        }
 */

/*
/**
 * Composition - is one of the fundamental concepts of OOP
 * When class, is referenced to one or more objects of another class
 * in instance variables, it calls composition.
 *
 * company object will contain address object
 *
 * The main advantage of Composition is that you can reuse the code
 * without is-a relationship (without inheritance)
 */


public class Address {

    private int addressId;
    private String street;
    private String city;
    private String state;
    private String zipCode;


    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
