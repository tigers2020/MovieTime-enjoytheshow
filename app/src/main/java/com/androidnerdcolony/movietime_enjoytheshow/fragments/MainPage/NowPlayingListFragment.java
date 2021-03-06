package com.androidnerdcolony.movietime_enjoytheshow.fragments.MainPage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnerdcolony.movietime_enjoytheshow.R;
import com.androidnerdcolony.movietime_enjoytheshow.activities.DetailActivity;
import com.androidnerdcolony.movietime_enjoytheshow.fragments.BaseFragment;
import com.androidnerdcolony.movietime_enjoytheshow.fragments.MainPage.adapters.CardViewAdapter;
import com.androidnerdcolony.movietime_enjoytheshow.fragments.MainPage.adapters.ScrollListener;
import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverMovieData;
import com.androidnerdcolony.movietime_enjoytheshow.util.NetworkManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by tiger on 4/9/2017.
 */

public class NowPlayingListFragment extends BaseFragment implements CardViewAdapter.PostClickListener {

    @BindView(R.id.recycle_list)
    RecyclerView nowPlayingView;
    @BindView(R.id.progressBar)
    ProgressBar loadingBar;
    @BindView(R.id.feature_spinner)
    Spinner feature_spinner;
    GridLayoutManager layoutManager;
    CardViewAdapter mCardViewAdapter;
    List<DiscoverMovieData.ResultsBean> list = new ArrayList<>();
    Map<String, String> query;
    Call<DiscoverMovieData> call;

    private ScrollListener mScrollListener;
    private Context context;
    private Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    private void loadDataIntoAdapter(DiscoverMovieData data) {
        list = data.getResults();

        if (mCardViewAdapter == null) {
            mCardViewAdapter = new CardViewAdapter(context, list, NowPlayingListFragment.this);
        } else {
            mCardViewAdapter.listDataChanged(list);
        }
        nowPlayingView.setAdapter(mCardViewAdapter);

        loadingBar.setVisibility(View.GONE);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        loadingBar.setVisibility(View.VISIBLE);
        layoutManager = new GridLayoutManager(context, 3);
        nowPlayingView.setLayoutManager(layoutManager);
        callingData();
        mScrollListener = new ScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemCount, RecyclerView view) {
                Timber.d("loading next page : " + page + "totalItemCount : " + totalItemCount);
                loadNextList(page);
            }
        };
        nowPlayingView.addOnScrollListener(mScrollListener);
        feature_spinner.setVisibility(View.GONE);

        return view;
    }

    private void loadNextList(int page) {
        String language = Locale.getDefault().getLanguage();
        call = NetworkManager.loadNowPlayingData(context, language, String.valueOf(page));
        call.enqueue(new Callback<DiscoverMovieData>() {
            @Override
            public void onResponse(Call<DiscoverMovieData> call, Response<DiscoverMovieData> response) {
                list = response.body().getResults();
                mCardViewAdapter.addAll(list);
            }

            @Override
            public void onFailure(Call<DiscoverMovieData> call, Throwable t) {

            }
        });
    }

    private void callingData() {
        String language = Locale.getDefault().getLanguage();
        call = NetworkManager.loadNowPlayingData(context, language, "1");
        call.enqueue(new Callback<DiscoverMovieData>() {
            @Override
            public void onResponse(Call<DiscoverMovieData> call, Response<DiscoverMovieData> response) {
                loadDataIntoAdapter(response.body());

            }

            @Override
            public void onFailure(Call<DiscoverMovieData> call, Throwable t) {

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void PostClicked(View v, int movieId) {

        Toast.makeText(context, "poster clicked : " + movieId, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("movieId", movieId);
        startActivity(intent);
    }
}
