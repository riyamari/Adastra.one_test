package com.example.adastraonetest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.adastraonetest.R;
import com.example.adastraonetest.interfaces.RecyclerViewClickListener;
import com.example.adastraonetest.model.Data;
import com.example.adastraonetest.model.Movie;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoveListAdapter extends RecyclerView.Adapter<MoveListAdapter.MovieViewHolder> {
    private List<Movie> movielist;
   private RecyclerViewClickListener listener;
   private  Context context;

    public MoveListAdapter(List<Movie> movieList, RecyclerViewClickListener listener, Context context) {
        this.movielist=movieList;
        this.listener=listener;
        this.context=context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movielist_item_layout,viewGroup,false);
        return new MovieViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, final int i) {
      Movie temp=movielist.get(i);
      movieViewHolder.actorName.setText(movielist.get(i).getName());
        Glide.with(context)
                .load(movielist.get(i).getImageUrl())
                .into(movieViewHolder.imageView);
    }
    @Override
    public int getItemCount() {
        return movielist.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.actor_name_text)
        TextView actorName;
        @BindView(R.id.movie_image)
        ImageView imageView;
        public MovieViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            listener=listener;
            itemView.setOnClickListener(this);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onClick(View v) {

            listener.onClick(v,getAbsoluteAdapterPosition());
        }
    }
}
