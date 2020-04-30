package com.example.latihanapi.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.latihanapi.Model.moviemodel.Dates;
import com.example.latihanapi.Model.moviemodel.MovieDiscoverResponse;
import com.example.latihanapi.Model.moviemodel.MovieResultsItem;
import com.example.latihanapi.Service.ApiMain;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {
    private ApiMain apiMain;
    private MutableLiveData<ArrayList<MovieResultsItem>> listDiscoverMovie = new MutableLiveData<>();
    public void setMovieDiscover(){
        if (this.apiMain == null){
            apiMain = new ApiMain();
        }
        apiMain.getApiMovie().getMoviewDiscover().enqueue(new Callback<MovieDiscoverResponse>() {
            @Override
            public void onResponse(Call<MovieDiscoverResponse> call, Response<MovieDiscoverResponse> response) {
                MovieDiscoverResponse responseDiscover = response.body();
                if(responseDiscover != null && responseDiscover.getResults()!=null){
                    ArrayList<MovieResultsItem> movieResultsItems = responseDiscover.getResults();
                    listDiscoverMovie.postValue(movieResultsItems);
                }
            }

            @Override
            public void onFailure(Call<MovieDiscoverResponse> call, Throwable t) {

            }
        });
    }
    public LiveData<ArrayList<MovieResultsItem>> getMovieDiscover(){

        return listDiscoverMovie;
    }
}
