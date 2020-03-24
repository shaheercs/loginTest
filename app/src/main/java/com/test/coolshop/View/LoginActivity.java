package com.test.coolshop.View;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

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
        loginViewModel = ViewModelProviders.of(this,new LoginVMFactory(this,activityBinding.getRoot())).get(LoginViewModel.class);
        activityBinding.setLogin(loginViewModel);
        loginViewModel.startEditTextDelayer();
    }

    @Override
    public void onBackPressed() {
        LoginActivity.this.moveTaskToBack(true);
    }
}
