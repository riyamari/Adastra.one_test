package com.example.adastraonetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {
   @BindView(R.id.movie_image_dt)
    ImageView movieImage;
    @BindView(R.id.movie_name_txt_dt)
    TextView movieName;
    @BindView(R.id.actor_text_dt)
    TextView movieActor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        Glide.with(this)
                .load(intent.getStringExtra("MOVIEIMAGE"))
                .into(movieImage);
        movieName.setText(intent.getStringExtra("MOVIENAME"));
        movieActor.setText(intent.getStringExtra("MOVIEACTOR"));
    }
}