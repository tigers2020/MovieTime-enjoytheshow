package com.androidnerdcolony.movietime_enjoytheshow.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.androidnerdcolony.movietime_enjoytheshow.R;
import com.androidnerdcolony.movietime_enjoytheshow.objects.ConfigurationData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

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
        SharedPreferences.Editor preferences = PreferenceManager.getDefaultSharedPreferences(context).edit();
        preferences.putString(context.getString(R.string.sort_by), sortBy).apply();
    }

    public static String isIncludeAdult(Context context) {
        boolean isIncludeAdult;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        isIncludeAdult = preferences.contains(context.getString(R.string.include_adult)) &&
                preferences.getBoolean(context.getString(R.string.include_adult), false);
        if (isIncludeAdult) {
            return "true";
        } else {
            return "false";
        }
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
        if (isIncludeVideo) {
            return "true";
        } else {
            return "false";
        }
    }

    public static void setIncludeVideo(Context context, boolean isIncludeVideo) {
        SharedPreferences.Editor preferences = PreferenceManager.getDefaultSharedPreferences(context).edit();
        preferences.putBoolean(context.getString(R.string.include_video), isIncludeVideo).apply();

    }

    public static boolean isFresh(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean("isFresh", true);
    }

    public static void setFresh(Context context, boolean isFresh) {
        SharedPreferences.Editor preferences = PreferenceManager.getDefaultSharedPreferences(context).edit();
        preferences.putBoolean("isFresh", isFresh).apply();
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
                setBackdropSize(context, data.getImages().getBackdrop_sizes());
                setLogoSize(context, data.getImages().getLogo_sizes());
                setPosterSize(context, data.getImages().getPoster_sizes());
                setProfileSize(context, data.getImages().getProfile_sizes());
                setStillSize(context, data.getImages().getStill_sizes());
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
        SharedPreferences.Editor preferences = PreferenceManager.getDefaultSharedPreferences(context).edit();
        preferences.putString(context.getString(R.string.language), language).apply();
    }

    public static String getLanguage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(context.getString(R.string.language), Locale.getDefault().getLanguage());
    }

    private static void setRegion(Context context, String country) {
        SharedPreferences.Editor preferences = PreferenceManager.getDefaultSharedPreferences(context).edit();
        preferences.putString(context.getString(R.string.region), country).apply();
    }

    public static String getRegion(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(context.getString(R.string.region), Locale.getDefault().getCountry());
    }

    private static void setImageSecureBaseUrl(Context context, String secure_base_url) {
        SharedPreferences.Editor preferences = PreferenceManager.getDefaultSharedPreferences(context).edit();
        preferences.putString(context.getString(R.string.image_secure_base_url), secure_base_url).apply();
    }

    public static String getImageSecureBaseUrl(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(context.getString(R.string.image_secure_base_url), "");
    }

    private static void setStillSize(Context context, List<String> stillSize) {
        SharedPreferences.Editor preferences = PreferenceManager.getDefaultSharedPreferences(context).edit();
        preferences.putString(context.getString(R.string.still_size), new Gson().toJson(stillSize)).apply();
    }

    public static List<String> getStillSize(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return new Gson().fromJson(preferences.getString(context.getString(R.string.still_size), ""),
                new TypeToken<List<String>>() {
                }.getType());
    }

    private static void setProfileSize(Context context, List<String> profileSize) {
        SharedPreferences.Editor preferences = PreferenceManager.getDefaultSharedPreferences(context).edit();
        preferences.putString(context.getString(R.string.profile_size),
                new Gson().toJson(profileSize)).apply();
    }

    public static List<String> getProfileSize(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return new Gson().fromJson(preferences.getString(context.getString(R.string.profile_size), ""),
                new TypeToken<List<String>>() {
                }.getType());
    }

    private static void setPosterSize(Context context, List<String> posterSize) {
        SharedPreferences.Editor preferences = PreferenceManager.getDefaultSharedPreferences(context).edit();
        preferences.putString(context.getString(R.string.poster_size),
                new Gson().toJson(posterSize)).apply();
    }

    public static List<String> getPosterSize(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return new Gson().fromJson(preferences.getString(context.getString(R.string.poster_size), ""),
                new TypeToken<List<String>>() {
                }.getType());
    }

    private static void setLogoSize(Context context, List<String> logoSize) {
        SharedPreferences.Editor preferences = PreferenceManager.getDefaultSharedPreferences(context).edit();
        preferences.putString(context.getString(R.string.logo_size), new Gson().toJson(logoSize)).apply();

    }

    public static List<String> getLogoSize(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return new Gson().fromJson(preferences.getString(context.getString(R.string.logo_size), ""),
                new TypeToken<List<String>>() {
                }.getType());
    }

    private static void setBackdropSize(Context context, List<String> backdropSize) {
        SharedPreferences.Editor preference = PreferenceManager.getDefaultSharedPreferences(context).edit();
        preference.putString(context.getString(R.string.image_backdrop_size),
                new Gson().toJson(backdropSize)).apply();
    }

    public static List<String> getBackdropSize(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return new Gson().fromJson(preferences.getString(context.getString(R.string.image_backdrop_size), ""), new TypeToken<List<String>>() {
        }.getType());
    }

    private static void setImageBaseUrl(Context context, String baseUrl) {
        SharedPreferences.Editor preference = PreferenceManager.getDefaultSharedPreferences(context).edit();
        preference.putString(context.getString(R.string.image_base_url), baseUrl).apply();

    }

    private static String getImageBaseUrl(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(context.getString(R.string.image_base_url), "");
    }


    public static String getTimezone(Context context) {
        TimeZone tz = TimeZone.getDefault();
        String tzString = tz.getID();
        return tzString;
    }
}
