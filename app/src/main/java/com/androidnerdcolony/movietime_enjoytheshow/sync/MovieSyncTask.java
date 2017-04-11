package com.androidnerdcolony.movietime_enjoytheshow.sync;

import android.content.Context;
import android.net.Uri;

import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverData;
import com.androidnerdcolony.movietime_enjoytheshow.util.ApiUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;

import timber.log.Timber;

/**
 * Created by tiger on 4/10/2017.
 */

public class MovieSyncTask {

    public static DiscoverData DiscoverMovies(Context context, Uri uri) {
            if (uri == null){
                Timber.e("Uri is Null");
                return null;
            }
        try {
            URL discoverUrl = new URL(uri.toString());
            String jsonMovieResponse = ApiUtils.getResponseFromHttpUrl(discoverUrl);
            Gson gson = new Gson();
            return gson.fromJson(jsonMovieResponse, DiscoverData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
