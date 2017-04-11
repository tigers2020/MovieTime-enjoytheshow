package com.androidnerdcolony.movietime_enjoytheshow.sync;

import android.content.Context;
import android.net.Uri;

import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverData;
import com.androidnerdcolony.movietime_enjoytheshow.util.ApiUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

/**
 * Created by tiger on 4/10/2017.
 */

public class MovieSyncTask {

    public static DiscoverData DiscoverMovies(Context context) {
        Map<String, String> queryString;
        queryString = ApiUtils.getQueryStrings(context);
        Uri uri = ApiUtils.getDiscoverMovieUri(context, queryString);

        try {
            URL discoverUrl = new URL(uri.toString());
            String jsonMovieResponse = ApiUtils.getResponseFromHttpUrl(discoverUrl);
            Gson gson = new Gson();
            DiscoverData data = gson.fromJson(jsonMovieResponse, DiscoverData.class);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
