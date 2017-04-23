package com.androidnerdcolony.movietime_enjoytheshow.fragments.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidnerdcolony.movietime_enjoytheshow.R;
import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverMovieData;
import com.androidnerdcolony.movietime_enjoytheshow.util.MoviePreferenceManager;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tiger on 4/15/2017.
 */

public class HomePopularImagePagerAdapter extends PagerAdapter {

    private Context context;
    private final List<DiscoverMovieData.ResultsBean> list;

    public HomePopularImagePagerAdapter(Context context, List<DiscoverMovieData.ResultsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }

    @BindView(R.id.backdrop)
    ImageView backdropView;
    @BindView(R.id.movie_title)
    TextView movieTitleView;
    @BindView(R.id.movie_description)
    TextView movieDescriptionView;
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        DiscoverMovieData.ResultsBean data = list.get(position);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.popular_card_view, container, false);
        ButterKnife.bind(this, view);

        List<String> list = MoviePreferenceManager.getBackdropSize(context);

        String backdropImageUrl = MoviePreferenceManager.getImageSecureBaseUrl(context)
                + list.get(3)
                + data.getBackdrop_path();
        Picasso.with(context).load(backdropImageUrl).into(backdropView);
        movieTitleView.setText(data.getTitle());
        movieDescriptionView.setText(data.getOverview());

        container.addView(view);
        return view;
    }
}
