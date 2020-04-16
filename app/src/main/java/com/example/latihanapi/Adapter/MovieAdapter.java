package com.example.latihanapi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.latihanapi.Activity.AddMovie;
import com.example.latihanapi.Model.moviemodel.MovieResultsItem;
import com.example.latihanapi.R;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private ArrayList<MovieResultsItem> movieResultsItems = new ArrayList<MovieResultsItem>();

    private Context context;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(MovieResultsItem movie);
    }

    private static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/";
    public MovieAdapter(Context context) {
        this.context = context;
    }


    public void setData(ArrayList<MovieResultsItem> items){
        movieResultsItems.clear();
        movieResultsItems.addAll(items);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(BASE_IMAGE_URL+movieResultsItems.get(position).getPosterPath()).into(holder.ivImage);
        holder.ivJudul.setText(movieResultsItems.get(position).getTitle());
        holder.ivRating.setText(String.valueOf(movieResultsItems.get(position).getVoteAverage()));
        holder.ivrelease.setText(movieResultsItems.get(position).getReleaseDate());
        holder.cardMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(movieResultsItems.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieResultsItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView ivJudul,ivRating,ivrelease;
        CardView cardMovie;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.imagemovie);
            ivJudul = itemView.findViewById(R.id.judulmovie);
            ivRating = itemView.findViewById(R.id.ratingmovie);
            ivrelease = itemView.findViewById(R.id.release);
            cardMovie = itemView.findViewById(R.id.item);
        }
    }
}
