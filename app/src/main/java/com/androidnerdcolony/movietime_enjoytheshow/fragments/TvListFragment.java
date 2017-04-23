package com.androidnerdcolony.movietime_enjoytheshow.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnerdcolony.movietime_enjoytheshow.R;
import com.androidnerdcolony.movietime_enjoytheshow.activities.DetailActivity;
import com.androidnerdcolony.movietime_enjoytheshow.fragments.adapters.TvViewAdapter;
import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverTvData;
import com.androidnerdcolony.movietime_enjoytheshow.util.NetworkManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by tiger on 4/9/2017.
 */

public class TvListFragment extends BaseFragment implements TvViewAdapter.PostClickListener{

    @BindView(R.id.recycle_tv_playing)
    RecyclerView nowPlayingView;
    @BindView(R.id.progressBar)
    ProgressBar loadingBar;
    TvViewAdapter mCardViewAdapter;
    private List<DiscoverTvData.ResultsBean> list = new ArrayList<>();
    private Context context;
    private Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    private void loadDataIntoAdapter() {
        if (mCardViewAdapter == null){
            mCardViewAdapter = new TvViewAdapter(context, list, TvListFragment.this);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3);
            nowPlayingView.setLayoutManager(layoutManager);

            nowPlayingView.setAdapter(mCardViewAdapter);
        }
        loadingBar.setVisibility(View.GONE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_list, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        loadingBar.setVisibility(View.VISIBLE);
        Call<DiscoverTvData> call = NetworkManager.loadTvData(context, NetworkManager.getDefaultQuery(context));

        call.enqueue(new Callback<DiscoverTvData>() {
            @Override
            public void onResponse(Call<DiscoverTvData> call, retrofit2.Response<DiscoverTvData> response) {
                Log.d("MainRetrifot", "onResponse: " + response.code());

                if (response.isSuccessful()) {
                    DiscoverTvData data = response.body();
                    list = data.getResults();
                    loadDataIntoAdapter();
                }
            }

            @Override
            public void onFailure(Call<DiscoverTvData> call, Throwable t) {

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
        DiscoverTvData.ResultsBean data = list.get(position);
        int movieId = data.getId();
        Toast.makeText(context, "poster clicked : " + movieId + "\n" + data.getName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("movieId", movieId);
        startActivity(intent);
    }

}
