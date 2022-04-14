package com.cjss.eCommerce1.model;

import java.util.List;

public class UserModel {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String mobileNumber;
    private String password;
    private List<UserAddressModel> userAddressModels;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserAddressModel> getUserAddressModels() {
        return userAddressModels;
    }

    public void setUserAddressModels(List<UserAddressModel> userAddressModels) {
        this.userAddressModels = userAddressModels;
    }
}
