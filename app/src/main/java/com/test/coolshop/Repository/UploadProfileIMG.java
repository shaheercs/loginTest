package com.test.coolshop.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.test.coolshop.Model.UpdateImageResponse;
import com.test.coolshop.neworkConfiguration.RetrofitInstance;
import com.test.coolshop.neworkConfiguration.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadProfileIMG {
    private MutableLiveData<String> profile_url = new MutableLiveData<>();

    public LiveData<String> uploadImage(String userId, String img) {
        RetrofitService service = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<UpdateImageResponse> call = service.updateImage(userId, img);
        call.enqueue(new Callback<UpdateImageResponse>() {
            @Override
            public void onResponse(Call<UpdateImageResponse> call, Response<UpdateImageResponse> response) {
                if (response.isSuccessful()) {
                    profile_url.setValue(response.body().getAvatar_url());
                } else {
                    profile_url.setValue("");
                }
            }

            @Override
            public void onFailure(Call<UpdateImageResponse> call, Throwable t) {
                profile_url.setValue("");
            }
        });
        return profile_url;
    }
}
