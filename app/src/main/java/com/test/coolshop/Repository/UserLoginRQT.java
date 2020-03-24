package com.test.coolshop.Repository;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.test.coolshop.Model.LoginRequest;
import com.test.coolshop.Model.LoginResponse;
import com.test.coolshop.neworkConfiguration.RetrofitInstance;
import com.test.coolshop.neworkConfiguration.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLoginRQT {

    private MutableLiveData<LoginResponse> userData = new MutableLiveData<>();

    public LiveData<LoginResponse> userLoginRequest(LoginRequest loginRequest) {
        RetrofitService service = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<LoginResponse> call = service.login(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    userData.setValue(response.body());
                } else {
                    userData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                userData.setValue(null);
            }
        });
        return userData;
    }

}
