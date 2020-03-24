package com.test.coolshop.viewModel;

import android.content.Context;
import android.util.Log;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.test.coolshop.Model.LoginRequest;
import com.test.coolshop.Model.LoginResponse;
import com.test.coolshop.Setting.Utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class SplashViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
    @Mock
    SplashViewModel splashViewModel;
    private String email;
    private String password;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        email="shaheer@hubun.com";
        password="asdfg";

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void checkUserAlreadyLog() {


    }
}