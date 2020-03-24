package com.test.coolshop.Model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("email")
    private String userid;
    @SerializedName("token")
    private String token;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
