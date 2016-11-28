package com.example.myapplication;

/**
 * Created by Dinesh on 11/22/2016.
 */
public class User {
    public int id;
    public String name;
    public String address;
    public String email;
    public String gender;

    public User(int id,String name,String email,String address,String gender){
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.gender = gender;
    }
    public User(String name,String email,String address,String gender){
        this.name = name;
        this.email = email;
        this.address = address;
        this.gender = gender;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString(){
        return name + " " + gender + " " + email + " " +address;
    }

}
