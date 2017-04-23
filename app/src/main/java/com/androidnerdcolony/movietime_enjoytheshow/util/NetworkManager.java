package com.androidnerdcolony.movietime_enjoytheshow.util;

import android.content.Context;
import android.util.Log;

import com.androidnerdcolony.movietime_enjoytheshow.BuildConfig;
import com.androidnerdcolony.movietime_enjoytheshow.R;
import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverMovieData;
import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverTvData;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tiger on 4/10/2017.
 */

public class NetworkManager {
    private final static String TAG = NetworkManager.class.getSimpleName();
    private Context context;

    public NetworkManager(Context context) {
        this.context = context;
    }

    public static DiscoverMovieData getDiscoverDataFromHttpUrl(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            Log.e(TAG, "connection Problem with " + connection.getResponseMessage());
            return null;
        }
        try{
            InputStream inputStream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            Gson gson = new Gson();
            return gson.fromJson(br, DiscoverMovieData.class);
        }finally {
            connection.disconnect();
        }
    }
    public static ApiEndpointInterface getService() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        HttpUrl originalHttpUrl = original.url();

                        HttpUrl url = originalHttpUrl.newBuilder()
                                .addQueryParameter("api_key", BuildConfig.API_KEY).build();
                        Request request = original.newBuilder().url(url).build();
                        return chain.proceed(request);
                    }
                }).build();
        Retrofit retrofitRef = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofitRef.create(ApiEndpointInterface.class);
    }

    public static Map<String, String> getDefaultQuery(Context context){
        Map<String, String> map = new HashMap<>();

        map.put(context.getString(R.string.language), MoviePreferenceManager.getLanguage(context));
        map.put(context.getString(R.string.sort_by), MoviePreferenceManager.getSortBy(context));
        map.put(context.getString(R.string.region), MoviePreferenceManager.getRegion(context));
        map.put(context.getString(R.string.page), "1");

        return map;
    }

    public static Call<DiscoverMovieData> loadMovieData(Context context, Map<String, String> query) {
        return getService().getDiscoverMovie(query);

    }
    public static Call<DiscoverTvData> loadTvData(Context context, Map<String, String> query) {
        return getService().getDiscoverTv(query);

    }
}
