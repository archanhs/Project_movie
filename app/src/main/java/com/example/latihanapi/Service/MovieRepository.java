package com.example.latihanapi.Service;

import com.example.latihanapi.Model.moviemodel.MovieDiscoverResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieRepository {
    @GET("3/movie/now_playing?api_key=21580136955f398b4252d386b9cdaa0b")
    Call<MovieDiscoverResponse> getMoviewDiscover();

}
