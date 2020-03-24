package com.test.coolshop.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.test.coolshop.Model.UserDetailsResponse;
import com.test.coolshop.Repository.GetUserInfo;
import com.test.coolshop.Repository.UploadProfileIMG;

public class ProfileViewModel extends ViewModel {
    //TODO need to with dependency induction
    private UploadProfileIMG uploadProfileIMG = new UploadProfileIMG();
    private GetUserInfo getUserInfo = new GetUserInfo();


    public LiveData<UserDetailsResponse> userInfo(String UserId) {
        return getUserInfo.getUserImageUrl(UserId);
    }

    public LiveData<String> uploadImage(String userID, String image) {
        return uploadProfileIMG.uploadImage(userID, image);
    }

}
