package com.androidnerdcolony.movietime_enjoytheshow.fragments.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnerdcolony.movietime_enjoytheshow.R;
import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverTvData;
import com.androidnerdcolony.movietime_enjoytheshow.util.MoviePreferenceManager;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tiger on 4/19/2017.
 */

public class TvViewAdapter extends RecyclerView.Adapter<TvViewAdapter.ViewHolder> {

    Context context;
    List<DiscoverTvData.ResultsBean> list;
    private static PostClickListener postClickListener;

    public interface PostClickListener{
        public void PostClicked(View v, int position);
    }
    public TvViewAdapter(Context context, List<DiscoverTvData.ResultsBean> list, PostClickListener postClickListener){
        this.context = context;
        this.list = list;
        this.postClickListener = postClickListener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card_view, parent, false);
        ViewHolder vh = new ViewHolder(view);


        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DiscoverTvData.ResultsBean data = list.get(position);

        List<String> posterSize = MoviePreferenceManager.getPosterSize(context);


        String posterUrl = MoviePreferenceManager.getImageSecureBaseUrl(context)
                + posterSize.get(3)
                + data.getPoster_path();
        Picasso.with(context).load(posterUrl).error(R.drawable.ic_powered_by_square_blue).into(holder.posterView);
        holder.titleView.setText(data.getName());
        holder.releaseDateView.setText(data.getFirst_air_date());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.poster)
        ImageView posterView;
        @BindView(R.id.movie_title)
        TextView titleView;
        @BindView(R.id.release_date)
        TextView releaseDateView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View view) {
            postClickListener.PostClicked(view, this.getLayoutPosition());

        }
    }
}
