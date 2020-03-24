package com.test.coolshop.viewModel;

import android.content.Intent;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.test.coolshop.Model.LoginRequest;
import com.test.coolshop.Model.LoginResponse;
import com.test.coolshop.Repository.UserLoginRQT;
import com.test.coolshop.Setting.Utils;
import com.test.coolshop.View.LoginActivity;
import com.test.coolshop.View.ProfileActivity;

import static com.test.coolshop.Setting.Utils.token;

public class SplashViewModel extends ViewModel {
    private FragmentActivity mContext;
    private UserLoginRQT userLoginRQT = new UserLoginRQT();
    private LoginRequest loginRequest;
    private String email = "";
    private String password = "";



    public SplashViewModel(FragmentActivity application, String param) {
        mContext=application;
    }

    /**
     * here we are checking we have saved the email and password in local memory?
     * if YES, we will call login api and got profilePage without showing LoginPage(without calling login api, we only having old token which lead to session time out)
     * if NO,we will take user to LoginPage
     */
    public void checkUserAlreadyLog() {
        email = Utils.readSharedSetting(mContext, "email", "");
        password = Utils.readSharedSetting(mContext, "password", "");
        if ((email != null && password != null) && (!email.isEmpty() && !password.isEmpty())) {
            loginRequest = new LoginRequest(email, password);
            userLoginRQT.userLoginRequest(loginRequest).observe( mContext, new Observer<LoginResponse>() {
                @Override
                public void onChanged(LoginResponse loginResponse) {
                    if (loginResponse != null) {
                        /**
                         * saving token and userId if auto login happens.
                         * navigating to profile activity
                         */
                        token = loginResponse.getToken();
                        Utils.saveSharedSetting(mContext, "userId", loginResponse.getUserid());
                        Intent intent = new Intent(mContext, ProfileActivity.class);
                        mContext.startActivity(intent);
                    }
                }
            });
        } else {
            /**
             * auto login failed or user is trying to login for first time
             */
            Intent intent = new Intent(mContext, LoginActivity.class);
            mContext.startActivity(intent);
        }

    }
}
