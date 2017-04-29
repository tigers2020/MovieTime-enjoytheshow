package com.androidnerdcolony.movietime_enjoytheshow.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnerdcolony.movietime_enjoytheshow.R;

import butterknife.BindView;

public class DetailActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.detail_tabs)
    TabLayout detailTabsView;
    @BindView(R.id.images_pager)
    ViewPager imagePagerView;
    @BindView(R.id.poster)
    ImageView posterView;
    @BindView(R.id.movie_title)
    TextView movieTitleView;
    @BindView(R.id.age_rate)
    TextView ageRateView;
    @BindView(R.id.genres)
    TextView genresView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }
}
