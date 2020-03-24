package com.test.coolshop.View;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.test.coolshop.Model.ErrorHandler;
import com.test.coolshop.R;
import com.test.coolshop.Setting.LoginVMFactory;
import com.test.coolshop.Setting.SplashViewModelFactory;
import com.test.coolshop.databinding.ActivityMainBinding;
import com.test.coolshop.viewModel.LoginViewModel;
import com.test.coolshop.viewModel.SplashViewModel;

import static com.test.coolshop.Setting.Utils.showSnackbar;

public class LoginActivity extends AppCompatActivity {

    private ActivityMainBinding activityBinding;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        loginViewModel = ViewModelProviders.of(this, new LoginVMFactory(this, activityBinding.getRoot())).get(LoginViewModel.class);
        activityBinding.setLogin(loginViewModel);
        loginViewModel.startEditTextDelayer();
        /**
         * observing the error in text field.
         */
        loginViewModel.getError().observe(this, new Observer<ErrorHandler>() {
            @Override
            public void onChanged(ErrorHandler errorHandler) {
                if (errorHandler != null && errorHandler.getEvent() != null) {
                    if (!errorHandler.getStatus()) {
                        activityBinding.usernameLayout.setErrorEnabled(errorHandler.getStatus());
                        activityBinding.passwordLayout.setErrorEnabled(errorHandler.getStatus());
                    } else if (errorHandler.getEvent().equalsIgnoreCase("email")) {
                        activityBinding.usernameLayout.setError(getString(R.string.email_error_warning));
                        activityBinding.usernameLayout.setErrorEnabled(errorHandler.getStatus());
                    } else if (errorHandler.getEvent().equalsIgnoreCase("password")) {
                        activityBinding.passwordLayout.setError(getString(R.string.password_error_warning));
                        activityBinding.passwordLayout.setErrorEnabled(errorHandler.getStatus());
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        LoginActivity.this.moveTaskToBack(true);
    }
}
