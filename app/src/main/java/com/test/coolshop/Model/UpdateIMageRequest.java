package com.test.coolshop.Model;

import com.google.gson.annotations.SerializedName;

public class UpdateIMageRequest {
    @SerializedName("avatar")
    private String avatar;

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
