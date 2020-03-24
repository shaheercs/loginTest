package com.test.coolshop.View;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.test.coolshop.R;
import com.test.coolshop.databinding.ActivityMainBinding;
import com.test.coolshop.viewModel.LoginViewModel;

import static com.test.coolshop.Setting.Utils.showSnackbar;

public class LoginActivity extends AppCompatActivity {

    private ActivityMainBinding activityBinding;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        activityBinding.setLogin(loginViewModel);
        loginViewModel.startEditTextDelayer();
        loginViewModel.errorMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    if (s.equalsIgnoreCase("email")) {
                        showSnackbar(activityBinding.getRoot(), getString(R.string.email_error_warning));
                    } else if (s.equalsIgnoreCase("password")) {
                        showSnackbar(activityBinding.getRoot(), getString(R.string.password_error_warning));
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
