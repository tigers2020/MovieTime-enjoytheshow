package com.androidnerdcolony.movietime_enjoytheshow.fragments.adapters;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by tiger on 4/24/2017.
 */

public abstract class ScrollListener extends RecyclerView.OnScrollListener {

    RecyclerView.LayoutManager mLayoutManager;
    private int visibleThreadHold = 5;
    private int currentPage = 1;
    private int previousTotalItemCount = 0;
    private boolean loading = true;
    private int startingPageIndex = 0;

    public ScrollListener(LinearLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    public ScrollListener(GridLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
        visibleThreadHold = visibleThreadHold * layoutManager.getSpanCount();
    }

    public ScrollListener(StaggeredGridLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
        visibleThreadHold = visibleThreadHold * layoutManager.getSpanCount();
    }

    public int getLastVisibleItem(int[] lastVisibleItemPositions) {
        int maxSize = 0;
        for (int i = 0; i < lastVisibleItemPositions.length; i++) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i];
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i];
            }
        }
        return maxSize;
    }

    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {

        int lastVisibleItemPosition = 0;
        int totalItemCount = mLayoutManager.getItemCount();

        if (mLayoutManager instanceof StaggeredGridLayoutManager) {
            int[] lastVisibleItemPositions = ((StaggeredGridLayoutManager) mLayoutManager).findLastVisibleItemPositions(null);

            lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions);
        }  else if (mLayoutManager instanceof LinearLayoutManager) {
            lastVisibleItemPosition = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
        }

        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.loading = true;
            }
        }
        if (dy > 0) {

            if (loading && (totalItemCount > previousTotalItemCount)) {
                loading = false;
                previousTotalItemCount = totalItemCount;
            }

            if (!loading && (lastVisibleItemPosition + visibleThreadHold) > totalItemCount) {
                currentPage++;
                onLoadMore(currentPage, totalItemCount, view);
                loading = true;


            }
        }
    }
    public void resetSate(){
        this.currentPage = this.startingPageIndex;
        this.previousTotalItemCount = 0;
        this.loading = true;
    }

    public abstract void onLoadMore(int page, int totalItemCount, RecyclerView view);
}

