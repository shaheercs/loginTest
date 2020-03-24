package com.test.coolshop.neworkConfiguration;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.test.coolshop.Setting.Utils.token;

public class RetrofitInstance {
    private static Retrofit retrofit;



    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl("https://xxx.xxx.com")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    public static OkHttpClient okHttpClient = new OkHttpClient()
            .newBuilder().addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request originalRequest = chain.request();

                    Request.Builder builder = originalRequest.newBuilder().header("Basic-Auth", "Basic SFVCVU46aHVidW5kZXYh")
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Access-Control-Allow-Origin", "https://xxx.xxx.com")
                            .addHeader("Authorization", token);

                    Request newRequest = builder.build();
                    return chain.proceed(newRequest);
                }
            })

            .build();
}

