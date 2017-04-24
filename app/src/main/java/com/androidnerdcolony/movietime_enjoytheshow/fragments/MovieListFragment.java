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
import com.androidnerdcolony.movietime_enjoytheshow.fragments.adapters.CardViewAdapter;
import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverMovieData;
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

/**
 * Created by tiger on 4/9/2017.
 */

public class MovieListFragment extends BaseFragment implements CardViewAdapter.PostClickListener {

    @BindView(R.id.recycle_list)
    RecyclerView nowPlayingView;
    @BindView(R.id.progressBar)
    ProgressBar loadingBar;
    @BindView(R.id.feature_spinner)
    Spinner feature_spinner;
    Map<String, String> query;
    Call<DiscoverMovieData> call;
    CardViewAdapter mCardViewAdapter;
    List<DiscoverMovieData.ResultsBean> list = new ArrayList<>();
    private Context context;
    private Unbinder mUnbinder;
    Bundle args;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = getArguments();
        context = getContext();
    }

    private void loadDataIntoAdapter(DiscoverMovieData body) {
        list = body.getResults();
        if (mCardViewAdapter == null){
            mCardViewAdapter = new CardViewAdapter(context, list, MovieListFragment.this);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3);
            nowPlayingView.setLayoutManager(layoutManager);
            nowPlayingView.setAdapter(mCardViewAdapter);
        }else{
            mCardViewAdapter.listDataChanged(list);
        }
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
        query = NetworkManager.getDefaultQuery(context);
        callingData();
        feature_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(adapterView.getContext(), "Selected Feature : " + item, Toast.LENGTH_SHORT).show();
                if (i == 0) {
                    return;
                }
                query.put(context.getString(R.string.sort_by), item);
                loadingBar.setVisibility(View.VISIBLE);
                callingData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return view;
    }
    private void callingData() {
        call = NetworkManager.loadMovieData(context, query);
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
    public void PostClicked(View v, int position) {
        DiscoverMovieData.ResultsBean data = list.get(position);
        int movieId = data.getId();
        Toast.makeText(context, "poster clicked : " + movieId + "\n" + data.getTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("movieId", movieId);
        startActivity(intent);

    }
}
