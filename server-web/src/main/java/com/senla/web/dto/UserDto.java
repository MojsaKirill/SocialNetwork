package com.senla.web.dto;

public class UserDto {
    private long id;
    private String email;
    private UserInfoDto userInfo;
    private UserAddressDto userAddress;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserInfoDto getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoDto userInfo) {
        this.userInfo = userInfo;
    }

    public UserAddressDto getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddressDto userAddress) {
        this.userAddress = userAddress;
    }
}
