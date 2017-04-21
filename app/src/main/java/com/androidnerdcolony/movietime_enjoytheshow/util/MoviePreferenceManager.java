package com.androidnerdcolony.movietime_enjoytheshow.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.androidnerdcolony.movietime_enjoytheshow.R;
import com.androidnerdcolony.movietime_enjoytheshow.objects.ConfigurationData;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by tiger on 4/10/2017.
 */

public class MoviePreferenceManager {

    public static String getSortBy(Context context) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(context.getString(R.string.sort_by), context.getString(R.string.popularity_desc));
    }

    public static void setSortBy(Context context, String sortBy) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(context.getString(R.string.sort_by), sortBy);
        editor.apply();
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

    public static void setIncludeAdult(Context context, boolean isIncludedAdult) {
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
        if (isIncludeVideo) {
            includeVideo = "true";
        } else {
            includeVideo = "false";
        }
        return includeVideo;
    }

    public static void setIncludeVideo(Context context, boolean isIncludeVideo) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(context.getString(R.string.include_video), isIncludeVideo);
        editor.apply();
    }

    public static boolean isFresh(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean("isFresh", false);
    }

    public static void setFresh(Context context, boolean isFresh) {
        SharedPreferences.Editor preferences = PreferenceManager.getDefaultSharedPreferences(context).edit();
        preferences.putBoolean("isFresh", isFresh);
        preferences.apply();
        preferences.notifyAll();
    }


    public static void setDefaultPreference(final Context context) {


        Call<ConfigurationData> call = NetworkManager.getService().getConfiguration();
        call.enqueue(new Callback<ConfigurationData>() {
            @Override
            public void onResponse(Call<ConfigurationData> call, retrofit2.Response<ConfigurationData> response) {
                ConfigurationData data = response.body();

                //set default ImageSize

                setImageBaseUrl(context, data.getImages().getBase_url());
                setImageSecureBaseUrl(context, data.getImages().getSecure_base_url());
                setBackdropSize(context, data.getImages().getBackdrop_sizes().get(1));
                setLogoSize(context, data.getImages().getLogo_sizes().get(2));
                setPosterSize(context, data.getImages().getPoster_sizes().get(2));
                setProfileSize(context, data.getImages().getProfile_sizes().get(1));
                setStillSize(context, data.getImages().getStill_sizes().get(1));
                setSortBy(context, context.getString(R.string.popularity_desc));
                setRegion(context, Locale.getDefault().getCountry());
                setLanguage(context, Locale.getDefault().getLanguage());
                setIncludeAdult(context, false);
                setIncludeVideo(context, false);
                setFresh(context, false);
            }

            @Override
            public void onFailure(Call<ConfigurationData> call, Throwable t) {

            }
        });
    }

    private static void setLanguage(Context context, String language) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(context.getString(R.string.language), language);
        editor.apply();
    }

    public static String getLanguage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(context.getString(R.string.language), Locale.getDefault().getLanguage());
    }

    private static void setRegion(Context context, String country) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(context.getString(R.string.region), country);
        editor.apply();
    }

    public static String getRegion(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(context.getString(R.string.region), Locale.getDefault().getCountry());
    }

    private static void setImageSecureBaseUrl(Context context, String secure_base_url) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(context.getString(R.string.image_secure_base_url), secure_base_url);
        editor.apply();
    }

    public static String getImageSecureBaseUrl(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(context.getString(R.string.image_secure_base_url), "");
    }

    private static void setStillSize(Context context, String stillSize) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(context.getString(R.string.still_size), stillSize);
        editor.apply();
    }

    public static String getStillSize(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(context.getString(R.string.still_size), "");
    }

    private static void setProfileSize(Context context, String profileSize) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(context.getString(R.string.profile_size), profileSize);
        editor.apply();
    }

    public static String getProfileSize(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(context.getString(R.string.profile_size), "");
    }

    private static void setPosterSize(Context context, String posterSize) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(context.getString(R.string.poster_size), posterSize);
        editor.apply();
    }

    public static String getPosterSize(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(context.getString(R.string.poster_size), "");
    }

    private static void setLogoSize(Context context, String logoSize) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(context.getString(R.string.logo_size), logoSize);
        editor.apply();
    }

    public static String getLogoSize(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(context.getString(R.string.logo_size), "");
    }

    private static void setBackdropSize(Context context, String backdropSize) {
        SharedPreferences.Editor preference = PreferenceManager.getDefaultSharedPreferences(context).edit();
        preference.putString(context.getString(R.string.image_backdrop_size), backdropSize);
        preference.apply();
    }

    public static String getBackdropSize(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(context.getString(R.string.image_backdrop_size), "");
    }

    private static void setImageBaseUrl(Context context, String baseUrl) {
        SharedPreferences.Editor preference = PreferenceManager.getDefaultSharedPreferences(context).edit();
        preference.putString(context.getString(R.string.image_base_url), baseUrl);
        preference.apply();
    }

    private static String getImageBaseUrl(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(context.getString(R.string.image_base_url), "");
    }


}
