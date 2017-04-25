package com.androidnerdcolony.movietime_enjoytheshow.util;

import com.androidnerdcolony.movietime_enjoytheshow.objects.ConfigurationData;
import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverMovieData;
import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverTvData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by tiger on 4/15/2017.
 */

public interface ApiEndpointInterface {

    @GET("3/discover/movie")
    Call<DiscoverMovieData> getDiscoverMovie(
            @QueryMap Map<String, String> options);

    @GET("3/movie/now_playing")
    Call<DiscoverMovieData> getDiscoverMovieNowPlaying(
            @Query("language") String Language,
            @Query("page") String page);

    @GET("3/discover/tv")
    Call<DiscoverTvData> getDiscoverTv(
            @QueryMap Map<String, String> options);

    @GET("3/configuration")
    Call<ConfigurationData> getConfiguration();


}
