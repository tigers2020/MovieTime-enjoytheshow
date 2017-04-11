package com.androidnerdcolony.movietime_enjoytheshow.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tiger on 4/10/2017.
 */

public class MovieDbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "movieshow.db";
    public static final int DB_VERSION = 1;

    public MovieDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_DATABASE = "CREATE TABLE ";

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
