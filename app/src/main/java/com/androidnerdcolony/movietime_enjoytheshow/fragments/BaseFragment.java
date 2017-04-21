package com.androidnerdcolony.movietime_enjoytheshow.fragments;

import android.support.v4.app.Fragment;

import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverMovieData;
import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverTvData;
import com.androidnerdcolony.movietime_enjoytheshow.util.NetworkManager;

import retrofit2.Call;

/**
 * Created by tiger on 4/19/2017.
 */

public class BaseFragment extends Fragment {

    Call<DiscoverMovieData> loadMovieData() {
        return NetworkManager.getService().getDiscoverMovie(NetworkManager.getDefaultQuery(getContext()));

    }
    Call<DiscoverTvData> loadTvData() {
        return NetworkManager.getService().getDiscoverTv(NetworkManager.getDefaultQuery(getContext()));

    }
}
