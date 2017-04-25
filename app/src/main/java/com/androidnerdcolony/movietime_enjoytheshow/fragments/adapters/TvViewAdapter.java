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

    private static PostClickListener postClickListener;
    Context context;
    List<DiscoverTvData.ResultsBean> list;

    public TvViewAdapter(Context context, List<DiscoverTvData.ResultsBean> list, PostClickListener postClickListener) {
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
        Picasso.with(context).load(posterUrl).placeholder(R.drawable.no_poster_found).error(R.drawable.no_poster_found).into(holder.posterView);
        holder.titleView.setText(data.getName());
        holder.releaseDateView.setText(data.getFirst_air_date());
    }

    public void listDataChanged(List<DiscoverTvData.ResultsBean> list) {
        this.list.clear();
        this.list = list;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface PostClickListener {
        public void PostClicked(View v, int position);
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
