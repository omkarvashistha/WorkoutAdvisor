package com.example.workoutadvisor.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.workoutadvisor.R;
import com.example.workoutadvisor.SqlDbHelper;
import com.example.workoutadvisor.data.WorkoutContract.ProgressEntry;

public class WorkoutProvider extends ContentProvider {

    private static final int PROGRESS = 100;
    private static final int PROGRESS_ID = 101;


    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(WorkoutContract.CONTENT_AUTHORITY,WorkoutContract.PATH_PROGRESS,PROGRESS);
        sUriMatcher.addURI(WorkoutContract.CONTENT_AUTHORITY,WorkoutContract.PATH_PROGRESS+"/#",PROGRESS_ID);
    }

    private SqlDbHelper sqlDbHelper;
    SQLiteDatabase database;
    public static final String LOG_TAG = WorkoutProvider.class.getSimpleName();

    @Override
    public boolean onCreate() {
        String DbName = String.valueOf(R.style.Database_name);
        sqlDbHelper =  new SqlDbHelper(getContext(),DbName,null,1);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        database = sqlDbHelper.getReadableDatabase();
        Cursor cursor;
        int match = sUriMatcher.match(uri);
        switch(match){
            case PROGRESS:
                cursor = database.query(ProgressEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case PROGRESS_ID:
                selection = WorkoutContract.ProgressEntry._ID+"=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(ProgressEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI");
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int match = sUriMatcher.match(uri);
        switch (match){
            case PROGRESS:
                return insertProgress(uri,values);
            default:
                throw new IllegalArgumentException("Cannot query unknown URI");
        }
    }

    private Uri insertProgress(Uri uri,ContentValues values){
        database = sqlDbHelper.getWritableDatabase();
        long id = database.insert(ProgressEntry.TABLE_NAME,null,values);

        if(id==-1){
            Log.e(LOG_TAG, "Failed to insert row for "+uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return ContentUris.withAppendedId(uri,id);

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
