package com.test.coolshop.neworkConfiguration;

import com.test.coolshop.Model.LoginRequest;
import com.test.coolshop.Model.LoginResponse;
import com.test.coolshop.Model.UpdateIMageRequest;
import com.test.coolshop.Model.UpdateImageResponse;
import com.test.coolshop.Model.UserDetailsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitService {

    @POST("/sessions/new")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("/users/{userid}")
    Call<UserDetailsResponse> userInfo(@Path("userid") String userId);

    @POST("/users/{userid}/avatar")
    Call<UpdateImageResponse> updateImage(@Path("userid") String userId, @Body String updateIMageRequest);

}
