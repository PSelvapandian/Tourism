package com.example.tourism.ModelClasses;

import com.example.tourism.Enum.Gender;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CustomUser
{
    private Long userId;
    private String name;
    private String email;
    private String password;
    private String dob;
    private Gender gender;
    private byte[] profile;
    private Address address;
    private String mobile;
    private Date createAt;
    private Date updateAt;
    private String activateToken;
    private String tokenExpireDateTime;
    private Boolean isAccountActive=false;
    private Boolean isAgency=false;
    private List<Roles> roles;

    public void insertCustomUser(String name, String email, String password, String dob, Address address, String mobile) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.address = address;
        this.mobile = mobile;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public byte[] getProfile() {
        return profile;
    }

    public void setProfile(byte[] profile) {
        this.profile = profile;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getActivateToken() {
        return activateToken;
    }

    public void setActivateToken(String activateToken) {
        this.activateToken = activateToken;
    }

    public String getTokenExpireDateTime() {
        return tokenExpireDateTime;
    }

    public void setTokenExpireDateTime(String tokenExpireDateTime) {
        this.tokenExpireDateTime = tokenExpireDateTime;
    }

    public Boolean getAccountActive() {
        return isAccountActive;
    }

    public void setAccountActive(Boolean accountActive) {
        isAccountActive = accountActive;
    }

    public Boolean getAgency() {
        return isAgency;
    }

    public void setAgency(Boolean agency) {
        isAgency = agency;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "CustomUser{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dob='" + dob + '\'' +
                ", gender=" + gender +
                ", profile=" + Arrays.toString(profile) +
                ", address=" + address +
                ", mobile='" + mobile + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", activateToken='" + activateToken + '\'' +
                ", tokenExpireDateTime='" + tokenExpireDateTime + '\'' +
                ", isAccountActive=" + isAccountActive +
                ", isAgency=" + isAgency +
                ", roles=" + roles +
                '}';
    }
}
