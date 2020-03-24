package com.test.coolshop.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.test.coolshop.Model.UpdateImageResponse;
import com.test.coolshop.Model.UserDetailsResponse;
import com.test.coolshop.neworkConfiguration.RetrofitInstance;
import com.test.coolshop.neworkConfiguration.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUserInfo {
    private MutableLiveData<UserDetailsResponse> userData = new MutableLiveData<>();

    public LiveData<UserDetailsResponse> getUserImageUrl(String userId) {
        RetrofitService service = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<UserDetailsResponse> call = service.userInfo(userId);
        call.enqueue(new Callback<UserDetailsResponse>() {
            @Override
            public void onResponse(Call<UserDetailsResponse> call, Response<UserDetailsResponse> response) {
                if (response.isSuccessful()) {
                    userData.setValue(response.body());
                } else {
                    userData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<UserDetailsResponse> call, Throwable t) {
                userData.setValue(null);
            }
        });
        return userData;
    }
}
