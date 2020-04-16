package com.example.latihanapi.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.latihanapi.Activity.AddMovie;
import com.example.latihanapi.Adapter.MovieAdapter;
import com.example.latihanapi.Model.moviemodel.MovieResultsItem;
import com.example.latihanapi.R;
import com.example.latihanapi.ViewModel.MovieViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMovie extends Fragment {

    private MovieAdapter movieAdapter;
    private RecyclerView movierv;
    private MovieViewModel movieViewModel;
    private static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/";


    public FragmentMovie() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieAdapter = new MovieAdapter(getContext());
        movieAdapter.notifyDataSetChanged();

        movierv = view.findViewById(R.id.rvmovie);
        movierv.setLayoutManager(new GridLayoutManager(getContext(),2));

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.setMovieDiscover();
        movieViewModel.getMovieDiscover().observe(this,getMovieDiscover);

        movierv.setAdapter(movieAdapter);
        movieAdapter.setOnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(MovieResultsItem movie) {
                Intent intent = new Intent(getContext(), AddMovie.class);
                intent.putExtra("poster",movie.getPosterPath());
                intent.putExtra("title",movie.getTitle());
                intent.putExtra("release",movie.getReleaseDate());
                intent.putExtra("poster",movie.getPosterPath());
                startActivity(intent);
            }
        });
    }
    private Observer<ArrayList<MovieResultsItem>> getMovieDiscover = new Observer<ArrayList<MovieResultsItem>>() {
        @Override
        public void onChanged(ArrayList<MovieResultsItem> movieResultsItems) {
            if (movieResultsItems != null){
                movieAdapter.setData(movieResultsItems);
            }
        }
    };
}
