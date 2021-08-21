package com.example.adastraonetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.adastraonetest.Utils.ErrorHandle;
import com.example.adastraonetest.Utils.NoConnectivityException;
import com.example.adastraonetest.adapter.MoveListAdapter;
import com.example.adastraonetest.interfaces.RecyclerViewClickListener;
import com.example.adastraonetest.model.Data;
import com.example.adastraonetest.model.Movie;
import com.example.adastraonetest.rest.ApiClient;
import com.example.adastraonetest.rest.ApiInterface;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.internal.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recylerview_main)
    RecyclerView recyclerView;
    ApiInterface apiService;
    ProgressDialog progressDialog;
    public Data dataList = new Data();
    private List<Movie> movieList;
    MoveListAdapter moveListAdapter;
    RecyclerViewClickListener listener;
    ErrorHandle errorHandle;
    String errormsg="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        apiService = ApiClient.getClient(this).create(ApiInterface.class);
        progressDialog = new ProgressDialog(this);
        errorHandle = new ErrorHandle(this);
        progressDialog.setMessage("Loading..");
        progressDialog.show();
        progressDialog.setCancelable(false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        listener=new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Movie movie=movieList.get(position);
                Intent intent=new Intent(MainActivity.this,DetailsActivity.class);
                intent.putExtra("MOVIENAME",movie.getMoviename());
                intent.putExtra("MOVIEACTOR",movie.getName());
                intent.putExtra("MOVIEIMAGE",movie.getImageUrl());
                startActivity(intent);
            }
        };
        getmovielist();
    }
    private void getmovielist() {
        Call<Data> call = apiService.getMovies();
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    dataList=  response.body();
                    movieList =dataList.getData();
                    if(movieList.size()>0){
                        moveListAdapter=new MoveListAdapter(movieList,listener,getApplicationContext());
                        recyclerView.setAdapter(moveListAdapter);
                    }
                } else {
                    errormsg = errorHandle.respMessage(response.code());
                    Toast.makeText(MainActivity.this, errormsg, Toast.LENGTH_SHORT)
                            .show();
                }
            }
            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                progressDialog.dismiss();
                if(t instanceof NoConnectivityException) {
                    Toast.makeText(MainActivity.this, R.string.no_internet, Toast.LENGTH_SHORT)
                            .show();
                }else {
                    Toast.makeText(MainActivity.this, R.string.toast_message, Toast.LENGTH_SHORT)
                            .show();
                }

            }
        });
    }
}