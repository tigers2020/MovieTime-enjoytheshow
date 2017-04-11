package com.androidnerdcolony.movietime_enjoytheshow.util;

import android.content.Context;
import android.util.Log;

import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverData;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tiger on 4/10/2017.
 */

public class NetworkManager {
    private final static String TAG = NetworkManager.class.getSimpleName();
    private Context context;

    public NetworkManager(Context context) {
        this.context = context;
    }

    public static DiscoverData getDiscoverDataFromHttpUrl(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            Log.e(TAG, "connection Problem with " + connection.getResponseMessage());
            return null;
        }
        try{
            InputStream inputStream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            Gson gson = new Gson();
            return gson.fromJson(br, DiscoverData.class);
        }finally {
            connection.disconnect();
        }
    }


}
