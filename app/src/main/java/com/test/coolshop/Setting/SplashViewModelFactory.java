package com.test.coolshop.Setting;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.test.coolshop.viewModel.SplashViewModel;

public class SplashViewModelFactory implements ViewModelProvider.Factory {
    FragmentActivity application;
    String param;
    public SplashViewModelFactory(FragmentActivity application, String setFactory) {
        this.application=application;
        param=setFactory;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SplashViewModel(application,param);
    }
}