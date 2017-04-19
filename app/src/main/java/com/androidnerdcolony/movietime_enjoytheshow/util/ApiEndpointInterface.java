package com.androidnerdcolony.movietime_enjoytheshow.util;

import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by tiger on 4/15/2017.
 */

public interface ApiEndpointInterface {

    @GET("3/discover/movie")
    Call<DiscoverData> getDiscoverMovie(
            @Query("sort_by") String sort_by);

    @GET("3/discover/tv")
    Call<DiscoverData> getDiscoverTv(
      @Query("sort_by") String sort_by);

}
