package com.androidnerdcolony.movietime_enjoytheshow.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidnerdcolony.movietime_enjoytheshow.R;
import com.androidnerdcolony.movietime_enjoytheshow.activities.DetailActivity;
import com.androidnerdcolony.movietime_enjoytheshow.fragments.adapters.CardViewAdapter;
import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverData;
import com.androidnerdcolony.movietime_enjoytheshow.sync.MovieSyncTask;
import com.androidnerdcolony.movietime_enjoytheshow.util.ApiUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by tiger on 4/9/2017.
 */

public class MovieListFragment extends Fragment implements CardViewAdapter.PostClickListener {

    @BindView(R.id.recycle_now_playing)
    RecyclerView nowPlayingView;
    CardViewAdapter mCardViewAdapter;
    List<DiscoverData.ResultsBean> list = new ArrayList<>();
    private Context mContext;
    private Unbinder mUnbinder;
    Bundle args;

    public static MovieListFragment newInstance(int titleId){
        MovieListFragment fragment = new MovieListFragment();
        Bundle args = new Bundle();
        args.putInt("title_flag", titleId);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = getArguments();
        mContext = getContext();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        Map<String, String> queryString;
        queryString = ApiUtils.getQueryStrings(mContext);

        Uri uri = ApiUtils.getNowPlayingUri(mContext, queryString);
        new loadDiscoverList(getContext()).execute(uri);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void PostClicked(View v, int position) {
        DiscoverData.ResultsBean data = list.get(position);
        int movieId = data.getId();
        Uri uri = ApiUtils.getMovieDetailUri(mContext, movieId);
        Toast.makeText(mContext, "poster clicked : " + movieId + "\n" + data.getTitle(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(mContext, DetailActivity.class);

        intent.putExtra("uri", uri.toString());
        startActivity(intent);

    }

    private class loadDiscoverList extends AsyncTask<Uri, String, DiscoverData> {
        Context context;

        loadDiscoverList(Context context) {
            this.context = context;

        }

        @Override
        protected void onPostExecute(DiscoverData discoverData) {
            super.onPostExecute(discoverData);
            if (discoverData != null) {
                list = discoverData.getResults();
                mCardViewAdapter = new CardViewAdapter(context, list, MovieListFragment.this);
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3);
                nowPlayingView.setLayoutManager(layoutManager);
                nowPlayingView.setAdapter(mCardViewAdapter);
            }
        }

        @Override
        protected DiscoverData doInBackground(Uri... uris) {

            return MovieSyncTask.DiscoverMovies(context, uris[0]);
        }
    }


}
