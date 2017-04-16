package com.androidnerdcolony.movietime_enjoytheshow.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.androidnerdcolony.movietime_enjoytheshow.R;

import java.util.Locale;

/**
 * Created by tiger on 4/10/2017.
 */

public class MoviePreferenceManager {

    public static String getSortBy(Context context) {
        String sortBy = "";
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        sortBy = preferences.getString(context.getString(R.string.sort_by), context.getString(R.string.popularity_desc));
        return sortBy;
    }

    public static String getLanguage(Context context) {
        String language;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (!preferences.contains(context.getString(R.string.language))){
            language = Locale.getDefault().getLanguage();
        }
        else{
            language = preferences.getString(context.getString(R.string.language), Locale.getDefault().getDisplayCountry());
        }
        return language;
    }

    public static String isIncludeAdult(Context context) {
        boolean isIncludeAdult;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        isIncludeAdult = preferences.contains(context.getString(R.string.include_adult)) &&
                preferences.getBoolean(context.getString(R.string.include_adult), false);
        String includeAdult;
        if (isIncludeAdult) {
            includeAdult = "true";
        } else {
            includeAdult = "false";
        }

        return includeAdult;
    }
    public static void setIncludeAdult(Context context, boolean isIncludedAdult){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(context.getString(R.string.include_adult), isIncludedAdult);
        editor.apply();
    }
    public static String isIncludeVideo(Context context) {
        boolean isIncludeVideo;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        isIncludeVideo = preferences.contains(context.getString(R.string.include_video)) &&
                preferences.getBoolean(context.getString(R.string.include_video), false);
        String includeVideo;
        if (isIncludeVideo){
            includeVideo = "true";
        }else {
            includeVideo = "false";
        }
        return includeVideo;
    }
    public static void setIncludeVideo(Context context, boolean isIncludeVideo){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(context.getString(R.string.include_video), isIncludeVideo);
        editor.apply();
    }
}
