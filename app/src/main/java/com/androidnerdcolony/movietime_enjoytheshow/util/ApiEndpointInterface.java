package com.androidnerdcolony.movietime_enjoytheshow.util;


import com.androidnerdcolony.movietime_enjoytheshow.objects.ConfigurationData;
import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverMovieData;
import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverTvData;
import com.androidnerdcolony.movietime_enjoytheshow.objects.MovieCreditData;
import com.androidnerdcolony.movietime_enjoytheshow.objects.MovieImageData;
import com.androidnerdcolony.movietime_enjoytheshow.objects.MovieReleaseDateData;
import com.androidnerdcolony.movietime_enjoytheshow.objects.MovieReviewData;
import com.androidnerdcolony.movietime_enjoytheshow.objects.MovieVideoData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
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

    @GET("3/movie/{movieId}/release_dates")
    Call<MovieReleaseDateData> getReleaseDates(
            @Path("movieId") String movieId
    );

    @GET("3/movie/{movieId}/images")
    Call<MovieImageData> getMovieImages(
            @Path("movieId") String movieId,
            @Query("language") String language,
            @Query("include_image_language") String image_language
    );

    @GET("3/movie/{movieId}/credits")
    Call<MovieCreditData> getMovieCredits(
            @Path("movieId") String movieId
    );

    @GET("3/movie/{movieId}/reviews")
    Call<MovieReviewData> getMovieReviews(
            @Path("movieId") String movieId,
            @Query("language") String language,
            @Query("page") String page
    );

    @GET("3/movie/{movieId}/recommendations")
    Call<DiscoverMovieData> getMovieRecommendations(
            @Path("movieId") String movieId,
            @Query("language") String language,
            @Query("page") String page
    );

    @GET("3/movie/{movieId}/similar")
    Call<DiscoverMovieData> getMovieSimilar(
            @Path("movieId") String movieId,
            @Query("language") String language,
            @Query("page") String page
    );

    @GET("3/movie/{movieId}/videos")
    Call<MovieVideoData> getMovieVideos(
            @Path("movieId") String movieId,
            @Query("language") String language
    );


}
