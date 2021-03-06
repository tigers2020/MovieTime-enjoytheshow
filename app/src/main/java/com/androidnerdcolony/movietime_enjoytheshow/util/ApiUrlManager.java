package com.androidnerdcolony.movietime_enjoytheshow.util;

import android.content.Context;
import android.net.Uri;

import com.androidnerdcolony.movietime_enjoytheshow.BuildConfig;
import com.androidnerdcolony.movietime_enjoytheshow.R;

import java.util.Map;

import timber.log.Timber;

/**
 * Created by tiger on 4/8/2017.
 */

public class ApiUrlManager {

    private Uri uri;

    public ApiUrlManager(Uri uri){this.uri = uri;}
    private ApiUrlManager(ApiBuilder builder) {
        this.uri = builder.uri;
    }

    public Uri getUri(){
        return uri;
    }

    public void setUri(Uri uri){
        this.uri = uri;
    }

    public static class ApiBuilder {
        private static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/";
        public static final String BASE_URL = "api.themoviedb.org";
        private static final String API_KEY = BuildConfig.API_KEY;
        private static final String VERSION3 = "3";
        private Uri uri = Uri.EMPTY;
        private Context mContext;

        ApiBuilder(Context context) {
            mContext = context;
        }

        ApiBuilder base() {
            this.uri = this.uri.buildUpon().scheme("https").authority(BASE_URL).appendPath(VERSION3).build();

            Timber.d("this uri = " + this.uri.toString());
            return this;
        }

        ApiBuilder movie() {
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_movie)).build();
            return this;
        }

        public ApiBuilder lists() {
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_lists)).build();
            return this;
        }

        ApiBuilder movieId(String movieId) {
            this.uri = this.uri.buildUpon().appendPath(movieId).build();
            return this;
        }

        ApiBuilder apiKey() {
            //this.uri = this.uri.buildUpon().appendQueryParameter(mContext.getString(R.string.api_key), API_KEY).build();
            return this;
        }

        ApiUrlManager build() {
            return new ApiUrlManager(this);
        }

        public ApiBuilder language(String language) {
            //this.url = this.url + mContext.getString(R.string.query_language, language);
            this.uri = this.uri.buildUpon().appendQueryParameter(mContext.getString(R.string.query_language), language).build();
            return null;
        }

        ApiBuilder tv() {
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_tv)).build();
            return this;
        }

        ApiBuilder tvId(String tvId) {
            this.uri = this.uri.buildUpon().appendPath(tvId).build();
            return this;
        }
        ApiBuilder discover(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_discover)).build();
            return this;
        }
        ApiBuilder find(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_find)).build();
            return this;
        }
        ApiBuilder genre(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_genre)).build();
            return this;
        }
        ApiBuilder keyword(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_keyword)).build();
            return this;
        }
        ApiBuilder account_states(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_account_states)).build();
            return this;
        }
        ApiBuilder alternative_titles(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_alternative_titles)).build();
            return this;
        }
        ApiBuilder credites(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_credits)).build();
            return this;
        }
        ApiBuilder images(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_images)).build();
            return this;
        }
        ApiBuilder release_dates(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_release_dates)).build();
            return this;
        }
        ApiBuilder vidoes(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_videos)).build();
            return this;
        }
        ApiBuilder translations(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_translations)).build();
            return this;
        }
        ApiBuilder alternativrecommendationse_titles(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_recommendations)).build();
            return this;
        }
        ApiBuilder similar(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_similar)).build();
            return this;
        }
        ApiBuilder reviews(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_reviews)).build();
            return this;
        }
        ApiBuilder latest(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_latest)).build();
            return this;
        }
        ApiBuilder now_playing(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_now_playing)).build();
            return this;
        }
        ApiBuilder popular(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_popular)).build();
            return this;
        }
        ApiBuilder top_rated(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_top_rated)).build();
            return this;
        }
        ApiBuilder upcoming(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_upcoming)).build();
            return this;
        }
        ApiBuilder network(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_network)).build();
            return this;
        }
        ApiBuilder person(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_person)).build();
            return this;
        }
        ApiBuilder movie_credits(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_movie_credits)).build();
            return this;
        }
        ApiBuilder tv_credits(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_tv_credits)).build();
            return this;
        }
        ApiBuilder combined_credits(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_combined_credits)).build();
            return this;
        }
        ApiBuilder external_ids(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_external_ids)).build();
            return this;
        }
        ApiBuilder tagged_images(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_tagged_images)).build();
            return this;
        }
        ApiBuilder changes(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_changes)).build();
            return this;
        }
        ApiBuilder review(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_review)).build();
            return this;
        }
        ApiBuilder search(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_search)).build();
            return this;
        }
        ApiBuilder company(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_company)).build();
            return this;
        }
        ApiBuilder collection(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_collection)).build();
            return this;
        }
        ApiBuilder multi(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_multi)).build();
            return this;
        }
        ApiBuilder timezones(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_timezones)).build();
            return this;
        }
        ApiBuilder episode(){
            this.uri = this.uri.buildUpon().appendPath(mContext.getString(R.string.path_episode)).build();
            return this;
        }

        ApiBuilder queryStrings(Map<String, String> queryString) {
            if (queryString == null){
                return this;
            }
            for (Map.Entry<String, String> pairs : queryString.entrySet()) {
                String key = pairs.getKey();
                String value = pairs.getValue();
                this.uri = this.uri.buildUpon().appendQueryParameter(key, value).build();
            }

            return this;
        }
    }
}
