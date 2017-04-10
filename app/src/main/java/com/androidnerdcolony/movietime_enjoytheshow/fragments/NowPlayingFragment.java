package com.androidnerdcolony.movietime_enjoytheshow.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnerdcolony.movietime_enjoytheshow.R;
import com.androidnerdcolony.movietime_enjoytheshow.data.DiscoverData;
import com.androidnerdcolony.movietime_enjoytheshow.fragments.adapters.CardViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by tiger on 4/9/2017.
 */

public class NowPlayingFragment extends Fragment {

    private Context mContext;
    private Unbinder mUnbinder;

    @BindView(R.id.recycle_now_playing)
    RecyclerView nowPlayingView;
    CardViewAdapter mCardViewAdapter;
    List<DiscoverData.ResultsBean> list = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();


        list.add(setDiscoverList());

        mCardViewAdapter = new CardViewAdapter(mContext, list);

    }

    private DiscoverData.ResultsBean setDiscoverList() {
        DiscoverData.ResultsBean discover = new DiscoverData.ResultsBean();
        discover.setAdult(true);
        discover.setBackdrop_path("/6aUWe0GSl69wMTSWWexsorMIvwU.jpg");
        discover.setPoster_path("/tWqifoYuwLETmmasnGHO7xBjEtt.jpg");
        discover.setOverview("A live-action adaptation of Disney's version of the classic 'Beauty and the Beast' tale of a cursed prince and a beautiful young woman who helps him break the spell.\",\n");
        discover.setRelease_date("2017-03-16");
        List<Integer> genre_ids = new ArrayList<>();
        genre_ids.add(14);
        genre_ids.add(10402);
        genre_ids.add(10749);
        discover.setGenre_ids(genre_ids);
        discover.setId(321612);
        discover.setTitle("Beauty and the Beast");
        discover.setOriginal_title("Beauty and the Beast");
        discover.setOriginal_language("en");
        discover.setPopularity(217.902232);
        discover.setVote_count(1440);
        discover.setVideo(false);
        discover.setVote_average(7);

        return discover;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        nowPlayingView.setLayoutManager(layoutManager);
        nowPlayingView.setAdapter(mCardViewAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
