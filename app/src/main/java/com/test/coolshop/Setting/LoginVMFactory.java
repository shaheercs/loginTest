package com.test.coolshop.Setting;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.test.coolshop.viewModel.LoginViewModel;

public class LoginVMFactory implements ViewModelProvider.Factory {
    FragmentActivity application;
    View param;

    public LoginVMFactory(FragmentActivity application, View setFactory) {
        this.application = application;
        param = setFactory;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoginViewModel(application, param);
    }
}