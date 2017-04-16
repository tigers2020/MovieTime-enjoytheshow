package com.androidnerdcolony.movietime_enjoytheshow.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnerdcolony.movietime_enjoytheshow.R;
import com.androidnerdcolony.movietime_enjoytheshow.fragments.adapters.CardViewAdapter;
import com.androidnerdcolony.movietime_enjoytheshow.fragments.adapters.HomePopularImagePagerAdapter;
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

public class HomeListFragment extends Fragment implements CardViewAdapter.PostClickListener{

    @BindView(R.id.recycle_now_playing)
    RecyclerView nowPlayingView;
    @BindView(R.id.most_popular_list)
    ViewPager mostPopularList;
    CardViewAdapter mCardViewAdapter;
    HomePopularImagePagerAdapter mHomePopularImagePagerAdapter;
    List<DiscoverData.ResultsBean> list = new ArrayList<>();
    private Context mContext;
    private Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        Map<String, String> popularQueryString;
        Map<String, String> upcomingQueryString;
        popularQueryString = ApiUtils.getQueryStrings(mContext);
        upcomingQueryString = ApiUtils.getQueryStrings(mContext);
        upcomingQueryString.put(getString(R.string.release_date_gte), "2017-04-15");
        popularQueryString.put(getString(R.string.sort_by), getString(R.string.popularity_desc));
        Uri uri;
        uri = ApiUtils.getDiscoverUpcomingMovie(mContext, upcomingQueryString);
        Uri popularUri = ApiUtils.getDiscoverMovie(mContext, popularQueryString);

        Log.d("upcoming", uri.toString());
        new loadDiscoverList(getContext(), 1).execute(popularUri);
        new loadDiscoverList(getContext(), 2).execute(uri);
        mostPopularList.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return false;
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

    private class loadDiscoverList extends AsyncTask<Uri, String, DiscoverData> {
        Context context;
        int loadId;

        loadDiscoverList(Context context, int loadId) {
            this.context = context;
            this.loadId = loadId;


        }

        @Override
        protected void onPostExecute(DiscoverData discoverData) {
            super.onPostExecute(discoverData);
            if (discoverData != null) {
                list = discoverData.getResults();
                if (loadId == 1){
                    mHomePopularImagePagerAdapter = new HomePopularImagePagerAdapter(context, list);
                    mostPopularList.setAdapter(mHomePopularImagePagerAdapter);
                }else {
                    mCardViewAdapter = new CardViewAdapter(context, list, HomeListFragment.this);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3);
                    nowPlayingView.setLayoutManager(layoutManager);
                    nowPlayingView.setAdapter(mCardViewAdapter);
                }
            }
        }

        @Override
        protected DiscoverData doInBackground(Uri... uris) {

            return MovieSyncTask.DiscoverMovies(context, uris[0]);
        }


    }


}
