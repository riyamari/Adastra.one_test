package com.example.adastraonetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.adastraonetest.model.Movie;
import com.example.adastraonetest.rest.ApiClient;
import com.example.adastraonetest.rest.ApiInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recylerview_main)
    RecyclerView recyclerView;
    ApiInterface apiService;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        apiService = ApiClient.getClient(this).create(ApiInterface.class);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        progressDialog.setCancelable(false);
        Call<List<Movie>> call = apiService.getMovies();
        call.enqueue(new Callback<List<Movie>>() {
         @Override
         public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
             if (response.isSuccessful()) {
             } else {
                 Toast.makeText(MainActivity.this, response.message(),
                         Toast.LENGTH_SHORT).show();
             }
         }

         @Override
         public void onFailure(Call<List<Movie>> call, Throwable t) {
             progressDialog.dismiss();
             Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT)
                     .show();

         }
     });

    }
}