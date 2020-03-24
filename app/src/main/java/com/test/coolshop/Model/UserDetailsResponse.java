package com.test.coolshop.Model;

import com.google.gson.annotations.SerializedName;

public class UserDetailsResponse {

    @SerializedName("email")
    private String email;
    @SerializedName("avatar_url")
    private String avatar_url;

    public String getEmail() {
        return email;
    }

    public String getAvatar_url() {
        return avatar_url;
    }
}
