package com.androidnerdcolony.movietime_enjoytheshow.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidnerdcolony.movietime_enjoytheshow.R;
import com.androidnerdcolony.movietime_enjoytheshow.activities.DetailActivity;
import com.androidnerdcolony.movietime_enjoytheshow.fragments.adapters.CardViewAdapter;
import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverMovieData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tiger on 4/9/2017.
 */

public class HomeListFragment extends BaseFragment implements CardViewAdapter.PostClickListener {

    @BindView(R.id.recycle_popular_playing)
    RecyclerView nowPlayingView;
    CardViewAdapter mCardViewAdapter;
    List<DiscoverMovieData.ResultsBean> list = new ArrayList<>();
    private Context context;
    private Unbinder mUnbinder;
    Call<DiscoverMovieData> call;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    private void loadDataIntoAdapter(DiscoverMovieData data) {
        list = data.getResults();

        if (mCardViewAdapter == null){
            mCardViewAdapter = new CardViewAdapter(context, list, HomeListFragment.this);
        }
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3);
        nowPlayingView.setLayoutManager(layoutManager);
        nowPlayingView.setAdapter(mCardViewAdapter);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        call = loadMovieData();
        call.enqueue(new Callback<DiscoverMovieData>() {
            @Override
            public void onResponse(Call<DiscoverMovieData> call, Response<DiscoverMovieData> response) {
                loadDataIntoAdapter(response.body());
            }

            @Override
            public void onFailure(Call<DiscoverMovieData> call, Throwable t) {

            }
        });

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void PostClicked(View v, int position) {
        DiscoverMovieData.ResultsBean data = list.get(position);
        int movieId = data.getId();
        Toast.makeText(context, "poster clicked : " + movieId + "\n" + data.getTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("movieId", movieId);
        startActivity(intent);
    }
}
