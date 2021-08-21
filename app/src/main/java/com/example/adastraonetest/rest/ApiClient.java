package com.example.adastraonetest.rest;

import android.content.Context;

import com.example.adastraonetest.Utils.NetworkConnectionInterceptor;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    static final private String BASE_URL = "https://uniqueandrocode.000webhostapp.com/";
    private static Retrofit retrofit = null;
    public static Retrofit getClient(Context context) {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new NetworkConnectionInterceptor(context));
            httpClient.addInterceptor(logging);
            httpClient.readTimeout(600, TimeUnit.SECONDS);
            httpClient.connectTimeout(600, TimeUnit.SECONDS);
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }
}
