package com.androidnerdcolony.movietime_enjoytheshow.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by tiger on 4/10/2017.
 */

public class MovieContract {
    public static final String CONTENT_AUTHOURITY = "com.androidnerdcolony.movietime_enjoytheshow";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHOURITY);
    public static final String PATH_QUERY_STRING = "query_string";
    public static final String PATH_HISTORY = "history";

    private MovieContract(){}

    public static final class MovieEntry implements BaseColumns{

        public static final Uri CONTENT_QUERY_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_QUERY_STRING).build();

        public static final String TABLE_QUERY_NAME = PATH_QUERY_STRING;
        public static final String TABLE_HISTORY = PATH_HISTORY;

        public final static String COLUMN_QUERY_KEY = "query_key";
        public final static String COLUMN_QUERY_VALUE = "query_value";

        public final static String COLUMN_HISTORY_ID = "history_id";
        public final static String COLUMN_MOVIE_ID = "movie_id";



    }
}
