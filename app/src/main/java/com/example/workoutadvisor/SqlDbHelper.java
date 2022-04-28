package com.example.workoutadvisor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.workoutadvisor.data.WorkoutContract;

public class SqlDbHelper extends SQLiteOpenHelper {
    private  SQLiteDatabase database;
    private SqlDbHelper sqlDbHelper;
    Context context;
    public SqlDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_PROGRESS_TABLE = "CREATE TABLE "+ WorkoutContract.ProgressEntry.TABLE_NAME+"("
                + WorkoutContract.ProgressEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +WorkoutContract.ProgressEntry.COLUMN_PHOTO+" BLOB, "
                +WorkoutContract.ProgressEntry.COLUMN_PHOTO_DATE+" TEXT);";
        db.execSQL(SQL_CREATE_PROGRESS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
