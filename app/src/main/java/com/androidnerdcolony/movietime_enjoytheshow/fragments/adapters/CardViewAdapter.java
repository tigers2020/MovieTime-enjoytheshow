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
import com.androidnerdcolony.movietime_enjoytheshow.objects.DiscoverMovieData;
import com.androidnerdcolony.movietime_enjoytheshow.util.MoviePreferenceManager;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static com.androidnerdcolony.movietime_enjoytheshow.R.id.poster;

/**
 * Created by tiger on 4/9/2017.
 */

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {
    private static PostClickListener postClickListener;
    private Context context;
    private List<DiscoverMovieData.ResultsBean> mDiscoverDataList;
    private boolean isLoadingAdded;

    public CardViewAdapter(Context context, List<DiscoverMovieData.ResultsBean> discoverDataList, PostClickListener postClickListener) {

        this.context = context;
        CardViewAdapter.postClickListener = postClickListener;
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
        List<String> posterSizeList = MoviePreferenceManager.getPosterSize(context);
        DiscoverMovieData.ResultsBean discover = mDiscoverDataList.get(position);
        String posterUrl = MoviePreferenceManager.getImageSecureBaseUrl(context)
                + posterSizeList.get(3)
                + discover.getPoster_path();
        Picasso.with(context).load(posterUrl).placeholder(R.drawable.no_poster_found).error(R.drawable.no_poster_found).into(holder.posterView);
        holder.titleView.setText(discover.getTitle());
        holder.releaseDateView.setText(discover.getRelease_date());

        holder.itemView.setTag(discover.getId());
    }

    @Override
    public int getItemCount() {
        return mDiscoverDataList.size();
    }

    public void listDataChanged(List<DiscoverMovieData.ResultsBean> mDiscoverDataList) {
        this.mDiscoverDataList.clear();
        this.mDiscoverDataList = mDiscoverDataList;
        notifyDataSetChanged();

    }
    public void addAll(List<DiscoverMovieData.ResultsBean> list){
        for (DiscoverMovieData.ResultsBean movie : list){
            add(movie);
        }
    }
    public void remove(DiscoverMovieData.ResultsBean movie){
        int position = mDiscoverDataList.indexOf(movie);
        if (position > -1){
            mDiscoverDataList.remove(position);
            notifyItemRemoved(position);
        }
    }
    public void clear(){
        isLoadingAdded = false;
        while (getItemCount() > 0){
            remove(getItem(0));
        }
    }

    public boolean isEmpty(){
        return getItemCount() == 0;
    }
    public void addLoadingFooter(){
        isLoadingAdded = true;
        add(new DiscoverMovieData.ResultsBean());
    }
    public void removeLoadingFooter(){
        isLoadingAdded = false;

        int position = mDiscoverDataList.size() -1;
        DiscoverMovieData.ResultsBean movieItem = getItem(position);

        if (movieItem!= null){
            mDiscoverDataList.remove(position);
            notifyItemRemoved(position);
        }
    }

    private DiscoverMovieData.ResultsBean getItem(int position) {
        return mDiscoverDataList.get(position);
    }

    public void add(DiscoverMovieData.ResultsBean movie){
        mDiscoverDataList.add(movie);
        notifyItemInserted(mDiscoverDataList.size() - 1);
    }

    public void listDataAdded(DiscoverMovieData list) {
        this.mDiscoverDataList.addAll(list.getResults());
        notifyDataSetChanged();
    }

    public interface PostClickListener {
        public void PostClicked(View v, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(poster)
        ImageView posterView;
        @BindView(R.id.movie_title)
        TextView titleView;
        @BindView(R.id.release_date)
        TextView releaseDateView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {

            Log.d("CardView: ", String.valueOf(titleView.getText()));
            Timber.d("CardView: " + String.valueOf(titleView.getText()) + "\n MovieId: " + view.getTag());
            postClickListener.PostClicked(view, (int)view.getTag());
        }
    }
}
