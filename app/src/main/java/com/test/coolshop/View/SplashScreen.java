package com.test.coolshop.View;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.test.coolshop.Setting.SplashViewModelFactory;
import com.test.coolshop.viewModel.SplashViewModel;

public class SplashScreen extends AppCompatActivity {
    private SplashViewModel splashViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * splash Screen viewModel will decide whether auto login should happen or not according to the previous
         */
        splashViewModel = ViewModelProviders.of(SplashScreen.this,new SplashViewModelFactory(this,"setFactory")).get(SplashViewModel.class);
        splashViewModel.checkUserAlreadyLog();


    }
}
