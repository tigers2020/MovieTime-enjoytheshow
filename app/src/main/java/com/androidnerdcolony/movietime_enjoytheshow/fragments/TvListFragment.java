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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnerdcolony.movietime_enjoytheshow.R;
import com.androidnerdcolony.movietime_enjoytheshow.activities.DetailActivity;
import com.androidnerdcolony.movietime_enjoytheshow.fragments.adapters.ScrollListener;
import com.androidnerdcolony.movietime_enjoytheshow.fragments.adapters.TvViewAdapter;
import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverTvData;
import com.androidnerdcolony.movietime_enjoytheshow.util.NetworkManager;

import java.util.ArrayList;
import java.util.List;
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

public class TvListFragment extends BaseFragment implements TvViewAdapter.PostClickListener{

    @BindView(R.id.recycle_list)
    RecyclerView nowPlayingView;
    @BindView(R.id.progressBar)
    ProgressBar loadingBar;
    @BindView(R.id.feature_spinner)
    Spinner feature_spinner;
    GridLayoutManager layoutManager;
    TvViewAdapter mCardViewAdapter;
    List<DiscoverTvData.ResultsBean> list = new ArrayList<>();
    Map<String, String> query;
    Call<DiscoverTvData> call;

    private ScrollListener mScrollListener;
    private Context context;
    private Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    private void loadDataIntoAdapter(DiscoverTvData data) {
        list = data.getResults();

        if (mCardViewAdapter == null) {
            mCardViewAdapter = new TvViewAdapter(context, list,TvListFragment.this);
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


        String[] featureArray = getResources().getStringArray(R.array.feature);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, featureArray);
        feature_spinner.setAdapter(adapter);
        layoutManager = new GridLayoutManager(context, 3);
        nowPlayingView.setLayoutManager(layoutManager);
        query = NetworkManager.getDefaultTvQuery(context);
        callingData();
        mScrollListener = new ScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemCount, RecyclerView view) {
                Timber.d("loading next page : " + page + "totalItemCount : " + totalItemCount);
                loadNextList(page);
            }
        };
        nowPlayingView.addOnScrollListener(mScrollListener);
        feature_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                list.clear();
                if (mCardViewAdapter != null) {
                    mCardViewAdapter.clear();
                    mCardViewAdapter.notifyDataSetChanged();
                }
                if (mScrollListener != null) {
                    mScrollListener.resetState();
                }
                String item = adapterView.getItemAtPosition(i).toString();
                if (i == 0) {
                    return;
                }
                query.put(context.getString(R.string.query_sort_by), item);
                loadingBar.setVisibility(View.VISIBLE);
                callingData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    private void loadNextList(int page) {

        query.put(getString(R.string.query_page), String.valueOf(page));
        call = NetworkManager.loadTvData(context, query);
        call.enqueue(new Callback<DiscoverTvData>() {
            @Override
            public void onResponse(Call<DiscoverTvData> call, Response<DiscoverTvData> response) {
                list = response.body().getResults();
                mCardViewAdapter.addAll(list);
            }

            @Override
            public void onFailure(Call<DiscoverTvData> call, Throwable t) {

            }
        });
    }

    private void callingData() {
        call = NetworkManager.loadTvData(context, query);
        call.enqueue(new Callback<DiscoverTvData>() {
            @Override
            public void onResponse(Call<DiscoverTvData> call, Response<DiscoverTvData> response) {
                loadDataIntoAdapter(response.body());

            }

            @Override
            public void onFailure(Call<DiscoverTvData> call, Throwable t) {
                Timber.d("loading Failed: " + t.getMessage());

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
