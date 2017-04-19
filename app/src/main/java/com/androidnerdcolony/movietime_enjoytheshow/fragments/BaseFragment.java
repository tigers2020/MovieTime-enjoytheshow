package com.androidnerdcolony.movietime_enjoytheshow.fragments;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.androidnerdcolony.movietime_enjoytheshow.BuildConfig;
import com.androidnerdcolony.movietime_enjoytheshow.R;
import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverData;
import com.androidnerdcolony.movietime_enjoytheshow.util.ApiEndpointInterface;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tiger on 4/19/2017.
 */

public class BaseFragment extends Fragment {

    Call<DiscoverData> loadData(int callingType) {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        HttpUrl originalHttpUrl = original.url();

                        HttpUrl url = originalHttpUrl.newBuilder()
                                .addQueryParameter("api_key", BuildConfig.API_KEY).build();
                        Request request = original.newBuilder().url(url).build();
                        Log.d("intercepter", "addQueryParameter: " + request.url().toString());
                        return chain.proceed(request);
                    }
                }).build();

        Retrofit retrofitRef = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiEndpointInterface service = retrofitRef.create(ApiEndpointInterface.class);

        switch (callingType) {
            case 0:
                return service.getDiscoverMovie(getString(R.string.popularity_desc));
            case 1:
                return service.getDiscoverMovie(getString(R.string.popularity_desc));
            case 2:
                return service.getDiscoverTv(getString(R.string.popularity_desc));
            default:
                return null;
        }

    }


}
