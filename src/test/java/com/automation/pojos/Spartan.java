package com.automation.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/*
This class represents Spartan POJO
Example of JSON response:
{
    "id": 393,              --> private int id;
    "name": "Michael Scott",--> private String name
    "gender": "Male"        --> private String gender;
    "phone"; 6969696969     --> @SerializedName("phone") private long phoneNumber;
}
SerializedName --> an annotation that indicates this member
should be serialized to JSON with
 */

public class Spartan {
    private  int id;
    private  String name;
    private String gender;
    @SerializedName("phone")
    private long phoneNumber;
    /*
     These field (phoneNumber) is annotated with the @SerializedName annotation.
     The parameter (value) of this annotation is the name to be used when
     serialising and deserialising objects. For example, the Java field
     phoneNumber is represented as name in JSON.
     */


    public Spartan(String name, String gender, long phoneNumber){
        this.name = name;
        this.gender = gender;
        setPhoneNumber(phoneNumber);

    }


    //constructor used to initialise the objects
    public Spartan(int id, String name, String gender, long phoneNumber){
        this.id = id;
        this.name = name;
        this.gender = gender;
        setPhoneNumber(phoneNumber);

    }
    //default constructor
    public Spartan(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        //converting the phoneNumber to String so we can use the method length()
        // to check the length of phone number
        if(String.valueOf(phoneNumber).length() < 10){
            throw new RuntimeException("Phone number is too short!");

        }
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Spartan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    // Indicates whether some other object is "equal to" this one
    // If you have two objects which are .equals(), but have different hash codes, you lose!
    // The part of the contract here which is important is:
    // objects which are .equals() MUST have the same .hashCode().
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spartan spartan = (Spartan) o;
        return id == spartan.id &&
                phoneNumber == spartan.phoneNumber &&
                Objects.equals(name, spartan.name) &&
                Objects.equals(gender, spartan.gender);
    }


    /*
    Java Object hashCode() is a native method and returns the integer
    hash code value of the object. ... If two objects are equal according to equals() method,
    then their hash code must be same. If two objects are unequal according to equals() method,
    their hash code are not required to be different.
    hashCode() is used for bucketing in Hash implementations like HashMap, HashTable, HashSet, etc.
    The value received from hashCode() is used as the bucket number for storing elements of the set/map.
    This bucket number is the address of the element inside the set/map.

     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, phoneNumber);
    }


}
