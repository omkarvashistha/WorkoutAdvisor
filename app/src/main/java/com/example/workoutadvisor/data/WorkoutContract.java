package com.example.workoutadvisor.data;

import android.net.Uri;
import android.provider.BaseColumns;

import com.example.workoutadvisor.R;

public final class WorkoutContract {
    private  WorkoutContract() {}

    public static  final String DATABASE_NAME = String.valueOf(R.style.Database_name);
    public static  final String CONTENT_AUTHORITY = "com.example.workoutadvisor";
    public static final Uri BASE_CONTENT_URI  = Uri.parse("content://"+CONTENT_AUTHORITY);
    public static final String PATH_PROGRESS = "Progress";

    public static final class ProgressEntry implements BaseColumns {

        public final  static String TABLE_NAME = "Progress";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,PATH_PROGRESS);

        public final  static String _ID = BaseColumns._ID;
        public final  static String COLUMN_PHOTO = "Photo";
        public final  static String COLUMN_PHOTO_DATE = "Photo_Date";
    }
}
