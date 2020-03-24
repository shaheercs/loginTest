package com.test.coolshop.viewModel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.test.coolshop.Model.LoginRequest;
import com.test.coolshop.Model.LoginResponse;
import com.test.coolshop.R;
import com.test.coolshop.Repository.UserLoginRQT;
import com.test.coolshop.Setting.Utils;
import com.test.coolshop.View.ProfileActivity;

import static com.test.coolshop.Setting.Utils.showSnackbar;
import static com.test.coolshop.Setting.Utils.token;

public class LoginViewModel extends ViewModel {

    public String emailAddress;
    public String password;
    private long delay = 3500;
    private long last_text_edit = 0;
    private Handler handler = new Handler();
    private Runnable input_finish_checker;
    private String message = "";
    private FragmentActivity context;
    private UserLoginRQT userLoginRQT = new UserLoginRQT();
    private LoginRequest loginRequest;
    private View rootView;

    public LoginViewModel(FragmentActivity application, View rootView) {
        context = application;
        this.rootView = rootView;
    }


    public void startEditTextDelayer() {
        input_finish_checker = new Runnable() {
            public void run() {
                if (System.currentTimeMillis() > (last_text_edit + delay - 500)) {
                    handler.removeCallbacks(input_finish_checker);
                    if (message.equalsIgnoreCase("email") && !validateEmail()) {
                        showSnackbar(rootView, context.getString(R.string.email_error_warning));
                    } else if (message.equalsIgnoreCase("password") && !validatePassword()) {
                        showSnackbar(rootView, context.getString(R.string.password_error_warning));
                    }
                }
            }
        };
    }



    private boolean validateEmail() {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches();
    }

    private boolean validatePassword() {
        return password.trim().length() > 4;
    }


    public void onPasswordTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().trim().length() > 0 && s.toString().trim().length() - start != 0) {
            last_text_edit = System.currentTimeMillis();
            handler.postDelayed(input_finish_checker, delay);
            message = "password";
        } else {
            handler.removeCallbacks(input_finish_checker);
        }
    }

    public void onEmailTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() - start != 0) {
            last_text_edit = System.currentTimeMillis();
            handler.postDelayed(input_finish_checker, delay);
            message = "email";
        } else {
            handler.removeCallbacks(input_finish_checker);
        }
    }

    /**
     * login button action hits here..
     */
    public void login() {
        if ((emailAddress != null && password != null) && validateEmail() && validatePassword()) {
            loginRequest = new LoginRequest(emailAddress, password);
            userLoginRQT.userLoginRequest(loginRequest).observe((LifecycleOwner) context, new Observer<LoginResponse>() {
                @Override
                public void onChanged(LoginResponse loginResponse) {
                    if (loginResponse != null) {
                        /**
                         * saving the userId and token
                         * saving the email and password for auto login
                         */
                        token = loginResponse.getToken();
                        Utils.saveSharedSetting(context, "userId", loginResponse.getUserid());
                        Utils.saveSharedSetting(context, "email", loginRequest.getEmail());
                        Utils.saveSharedSetting(context, "password", loginRequest.getPassword());
                        /**
                         * navigating to profile Page
                         */
                        Intent intent = new Intent(context, ProfileActivity.class);
                        context.startActivity(intent);
                    } else {
                        Utils.showSnackbar(rootView, context.getString(R.string.server_error));
                    }
                }
            });
        } else {
            Utils.showSnackbar(rootView, context.getString(R.string.invalid_credentials));
            // nextPage();
        }
    }

    //Todo remove this method
    public void nextPage() {
        Intent intent = new Intent(context, ProfileActivity.class);
        context.startActivity(intent);
    }
}
