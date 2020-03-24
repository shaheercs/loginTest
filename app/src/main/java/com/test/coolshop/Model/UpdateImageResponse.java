package com.test.coolshop.Model;

import com.google.gson.annotations.SerializedName;

public class UpdateImageResponse {
    @SerializedName("avatar_url")
    private String avatar_url;

    public String getAvatar_url() {
        return avatar_url;
    }
}
