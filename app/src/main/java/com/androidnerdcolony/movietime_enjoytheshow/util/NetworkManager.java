package com.androidnerdcolony.movietime_enjoytheshow.util;

import android.content.Context;

import com.androidnerdcolony.movietime_enjoytheshow.BuildConfig;
import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverMovieData;
import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverTvData;

import java.io.IOException;
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
        QueryManager manager = new QueryManager.QueryBuilder(context)
                .language()
                .sortBy(MoviePreferenceManager.getSortBy(context))
                .includeAdult()
                .includeVideo()
                .page("1")
                .build();
        return manager.getQuery();
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
        QueryManager manager = new QueryManager.QueryBuilder(context)
                .language()
                .sortBy(MoviePreferenceManager.getSortBy(context))
                .includeNullFirstAirDates("false")
                .page("1")
                .timezone(MoviePreferenceManager.getTimezone(context))
                .build();
        return manager.getQuery();
    }
}
