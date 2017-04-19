package com.androidnerdcolony.movietime_enjoytheshow.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnerdcolony.movietime_enjoytheshow.R;
import com.androidnerdcolony.movietime_enjoytheshow.fragments.adapters.CardViewAdapter;
import com.androidnerdcolony.movietime_enjoytheshow.fragments.adapters.HomePopularImagePagerAdapter;
import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverData;

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

    @BindView(R.id.recycle_now_playing)
    RecyclerView nowPlayingView;
    @BindView(R.id.most_popular_list)
    ViewPager mostPopularList;
    CardViewAdapter mCardViewAdapter;
    HomePopularImagePagerAdapter mHomePopularImagePagerAdapter;
    List<DiscoverData.ResultsBean> list = new ArrayList<>();
    private Context context;
    private Unbinder mUnbinder;
    Call<DiscoverData> call;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    private void loadDataIntoAdapter(DiscoverData data) {
        list = data.getResults();

        if (mHomePopularImagePagerAdapter == null) {
            mHomePopularImagePagerAdapter = new HomePopularImagePagerAdapter(context, list);
        }
        if (mCardViewAdapter == null){
            mCardViewAdapter = new CardViewAdapter(context, list, HomeListFragment.this);
        }
        mostPopularList.setAdapter(mHomePopularImagePagerAdapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3);
        nowPlayingView.setLayoutManager(layoutManager);
        nowPlayingView.setAdapter(mCardViewAdapter);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        call = loadData(0);
        call.enqueue(new Callback<DiscoverData>() {
            @Override
            public void onResponse(Call<DiscoverData> call, Response<DiscoverData> response) {
                loadDataIntoAdapter(response.body());
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
