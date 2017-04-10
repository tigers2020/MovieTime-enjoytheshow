package com.androidnerdcolony.movietime_enjoytheshow.util;

import android.content.Context;
import android.net.Uri;

/**
 * Created by tiger on 4/10/2017.
 */

public class ApiUtils {

    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/";
    public static String getImageUrl(String poster_path) {
        String url = IMAGE_BASE_URL + poster_path;
        return url;
    }
    public Uri getDiscoverMovie(Context context){
        Uri uri;
        ApiUrlManager manager = new ApiUrlManager.ApiBuilder(context).base().movie().apiKey().queryStrings(null).build();
        uri = manager.getUri();
        return  uri;
    }
}
