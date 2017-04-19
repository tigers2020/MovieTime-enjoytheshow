package com.androidnerdcolony.movietime_enjoytheshow.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.androidnerdcolony.movietime_enjoytheshow.R;
import com.androidnerdcolony.movietime_enjoytheshow.fragments.adapters.CardViewAdapter;
import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverData;

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

public class TvListFragment extends BaseFragment implements CardViewAdapter.PostClickListener{

    @BindView(R.id.recycle_now_playing)
    RecyclerView nowPlayingView;
    @BindView(R.id.progressBar)
    ProgressBar loadingBar;
    CardViewAdapter mCardViewAdapter;
    List<DiscoverData.ResultsBean> list = new ArrayList<>();
    private Context context;
    private Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    private void loadDataIntoAdapter() {
        if (mCardViewAdapter == null){
            mCardViewAdapter = new CardViewAdapter(context, list, TvListFragment.this);
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
        Call<DiscoverData> call = loadData(2);

        call.enqueue(new Callback<DiscoverData>() {
            @Override
            public void onResponse(Call<DiscoverData> call, retrofit2.Response<DiscoverData> response) {
                Log.d("MainRetrifot", "onResponse: " + response.code());

                if (response.isSuccessful()) {
                    DiscoverData data = response.body();
                    list = data.getResults();
                    loadDataIntoAdapter();
                }
            }

            @Override
            public void onFailure(Call<DiscoverData> call, Throwable t) {

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

    }

}
