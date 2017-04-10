package com.androidnerdcolony.movietime_enjoytheshow.fragments.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnerdcolony.movietime_enjoytheshow.R;
import com.androidnerdcolony.movietime_enjoytheshow.data.DiscoverData;
import com.androidnerdcolony.movietime_enjoytheshow.util.ApiUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.androidnerdcolony.movietime_enjoytheshow.R.id.poster;

/**
 * Created by tiger on 4/9/2017.
 */

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {
    Context context;
    List<DiscoverData.ResultsBean> mDiscoverDataList;

    public CardViewAdapter(Context context, List<DiscoverData.ResultsBean> discoverDataList){
        this.context = context;
        mDiscoverDataList = discoverDataList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card_view, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DiscoverData.ResultsBean discover = mDiscoverDataList.get(position);
        String posterUrl = ApiUtils.getImageUrl(discover.getBackdrop_path());
        String backdropUrl = ApiUtils.getImageUrl(discover.getBackdrop_path());
        int posterWidth = holder.posterView.getWidth();
        int posterHeight = (int)(posterWidth * 1.5);
        holder.posterView.setMinimumHeight(posterHeight);
        int backdropWidth = holder.backdropView.getWidth();
        int backdropHeight = (int)(backdropWidth * 0.78);
        holder.backdropView.setMinimumHeight(backdropHeight);

        Picasso.with(context).load(posterUrl).error(R.drawable.ic_powered_by_square_blue).into(holder.posterView);
        Picasso.with(context).load(backdropUrl).error(R.drawable.ic_powered_by_rectangle_blue).into(holder.backdropView);
        holder.titleView.setText(discover.getTitle());
        holder.releaseDateView.setText(discover.getRelease_date());
        holder.subTitleView.setText(discover.getOriginal_title());

        Log.d("CardViewAdapter", "poster Url : " + posterUrl);
        Log.d("CardViewAdapter","backdrop Url : " + backdropUrl);


    }

    @Override
    public int getItemCount() {
        return mDiscoverDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.backdrop)
        ImageView backdropView;
        @BindView(poster)
        ImageView posterView;
        @BindView(R.id.sub_title)
        TextView subTitleView;
        @BindView(R.id.movie_title)
        TextView titleView;
        @BindView(R.id.release_date)
        TextView releaseDateView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
