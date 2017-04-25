package com.androidnerdcolony.movietime_enjoytheshow.util;

import android.content.Context;

import com.androidnerdcolony.movietime_enjoytheshow.BuildConfig;
import com.androidnerdcolony.movietime_enjoytheshow.R;
import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverMovieData;
import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverTvData;

import java.io.IOException;
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
import timber.log.Timber;

/**
 * Created by tiger on 4/10/2017.
 */

public class NetworkManager {
    private final static String TAG = NetworkManager.class.getSimpleName();


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

                        Timber.d("request Url = " + request.url().toString());
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
    public static Call<DiscoverMovieData> loadNowPlayingData(Context context, String language, String page){
        return getService().getDiscoverMovieNowPlaying(language, page);
    }

    public static Map<String, String> getDefaultTvQuery(Context context) {
        Map<String, String> query = new HashMap<>();
        query.put(context.getString(R.string.language), MoviePreferenceManager.getLanguage(context));
        query.put(context.getString(R.string.sort_by), MoviePreferenceManager.getSortBy(context));
        query.put(context.getString(R.string.include_null_first_air_dates), "false");

        query.put(context.getString(R.string.timezone), MoviePreferenceManager.getTimezone(context));
        return query;
    }
}
