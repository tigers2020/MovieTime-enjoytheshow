package com.androidnerdcolony.movietime_enjoytheshow.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnerdcolony.movietime_enjoytheshow.R;
import com.androidnerdcolony.movietime_enjoytheshow.fragments.adapters.CardViewAdapter;
import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverData;
import com.androidnerdcolony.movietime_enjoytheshow.sync.MovieSyncTask;

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
    }

    class loadDiscoverList extends AsyncTask<String, String, DiscoverData> {
        Context context;
        public loadDiscoverList(Context context) {
            this.context = context;

        }

        @Override
        protected void onPostExecute(DiscoverData discoverData) {
            super.onPostExecute(discoverData);
            if (discoverData != null) {
                list = discoverData.getResults();
                mCardViewAdapter = new CardViewAdapter(context, list);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                nowPlayingView.setLayoutManager(layoutManager);
                nowPlayingView.setAdapter(mCardViewAdapter);
            }
        }

        @Override
        protected DiscoverData doInBackground(String... strings) {

            return MovieSyncTask.DiscoverMovies(context);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        new loadDiscoverList(getContext()).execute("");

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }


}
