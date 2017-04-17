package com.androidnerdcolony.movietime_enjoytheshow.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnerdcolony.movietime_enjoytheshow.BuildConfig;
import com.androidnerdcolony.movietime_enjoytheshow.R;
import com.androidnerdcolony.movietime_enjoytheshow.fragments.adapters.CardViewAdapter;
import com.androidnerdcolony.movietime_enjoytheshow.fragments.adapters.HomePopularImagePagerAdapter;
import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverData;
import com.androidnerdcolony.movietime_enjoytheshow.util.ApiEndpointInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tiger on 4/9/2017.
 */

public class HomeListFragment extends Fragment implements CardViewAdapter.PostClickListener {

    @BindView(R.id.recycle_now_playing)
    RecyclerView nowPlayingView;
    @BindView(R.id.most_popular_list)
    ViewPager mostPopularList;
    CardViewAdapter mCardViewAdapter;
    HomePopularImagePagerAdapter mHomePopularImagePagerAdapter;
    List<DiscoverData.ResultsBean> list = new ArrayList<>();
    private Context context;
    private Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request request = chain.request().newBuilder().build();
                                return chain.proceed(request);
                            }}).build();

        Retrofit retrofitRef = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiEndpointInterface service = retrofitRef.create(ApiEndpointInterface.class);

        Call<DiscoverData> call = service.getDiscoverMovie(BuildConfig.API_KEY, getString(R.string.popularity_desc));

        call.enqueue(new Callback<DiscoverData>() {
            @Override
            public void onResponse(Call<DiscoverData> call, retrofit2.Response<DiscoverData> response) {
                Log.d("MainRetrifot", "onResponse: " + response.code());

                if (response.isSuccessful()){
                    DiscoverData data = response.body();
                    list = data.getResults();
                    mHomePopularImagePagerAdapter = new HomePopularImagePagerAdapter(context, list);
                    mostPopularList.setAdapter(mHomePopularImagePagerAdapter);

                    mCardViewAdapter = new CardViewAdapter(context, list, HomeListFragment.this);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3);
                    nowPlayingView.setLayoutManager(layoutManager);
                    nowPlayingView.setAdapter(mCardViewAdapter);
                }
            }

            @Override
            public void onFailure(Call<DiscoverData> call, Throwable t) {

            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void PostClicked(View v, int position) {

    }
}
