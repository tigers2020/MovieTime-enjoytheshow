package com.androidnerdcolony.movietime_enjoytheshow.util;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.androidnerdcolony.movietime_enjoytheshow.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by tiger on 4/10/2017.
 */

public class ApiUtils {

    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/";
    private static final String TAG = ApiUtils.class.getSimpleName();

    public static String getImageUrl(String poster_path) {
        String url = IMAGE_BASE_URL + poster_path;
        return url;
    }

    public static Uri getDiscoverMovieUri(Context context, Map<String, String> queryString) {
        Uri uri;
        ApiUrlManager manager = new ApiUrlManager.ApiBuilder(context).base().movie().apiKey().queryStrings(queryString).build();
        uri = manager.getUri();
        return uri;
    }

    public static Map<String, String> getQueryStrings(Context context) {
        Map<String, String> queryString;

        queryString = setDefaultQueryString(context);

        return queryString;
    }

    public static Map<String, String> setDefaultQueryString(Context context) {
        Map<String, String> queryString = new HashMap<>();

        queryString.put(context.getString(R.string.language), MoviePreferenceManager.getLanguage(context));
        queryString.put(context.getString(R.string.sort_by), MoviePreferenceManager.getSortBy(context));
        queryString.put(context.getString(R.string.include_adult), MoviePreferenceManager.isIncludeAdult(context));
        queryString.put(context.getString(R.string.include_video), MoviePreferenceManager.isIncludeVideo(context));
        queryString.put(context.getString(R.string.page), "1");

        return queryString;


    }

    public static String getResponseFromHttpUrl(URL discoverUrl) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) discoverUrl.openConnection();

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK){
            Log.e(TAG, "connection problem with : " + connection.getResponseMessage());
            return null;
        }

        try{
            InputStream in = connection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            String response = "";

            if (hasInput){
                response = scanner.next();
            }
            scanner.close();
            return response;
        }finally {
            connection.disconnect();
        }
    }
}
