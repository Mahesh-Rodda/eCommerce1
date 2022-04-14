package com.cjss.eCommerce1.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USERS_TABLE")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String mobileNumber;
    private String password;
    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.DETACH)
    private List<UserAddressEntity> userAddressEntity;
    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.DETACH)
    private List<OrderEntity> orderEntities;
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.DETACH)
    private List<CartEntity> cartEntities;

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

    public List<UserAddressEntity> getUserAddressEntity() {
        return userAddressEntity;
    }

    public void setUserAddressEntity(List<UserAddressEntity> userAddressEntity) {
        this.userAddressEntity = userAddressEntity;
    }

    public List<OrderEntity> getOrderEntities() {
        return orderEntities;
    }

    public List<CartEntity> getCartEntities() {
        return cartEntities;
    }
}
