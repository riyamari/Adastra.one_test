package com.example.adastraonetest.rest;

import com.example.adastraonetest.model.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
   @GET("hiren/androidweb.php")
    Call<Data> getMovies();
}
