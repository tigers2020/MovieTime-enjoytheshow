package com.androidnerdcolony.movietime_enjoytheshow;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by tiger on 4/9/2017.
 */

public class MovieShowApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

    }
}
