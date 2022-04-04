package com.example.workoutadvisor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workoutadvisor.data.PhotoCursorAdapter;
import com.example.workoutadvisor.data.WorkoutContract.ProgressEntry;

import com.example.workoutadvisor.data.WorkoutContract;

public class ViewProgress extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int PHOTO_LOADER = 0;

    PhotoCursorAdapter mCursorAdapter;

    TextView textView;
    ListView photoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_progress);
        photoList = findViewById(R.id.list);
        mCursorAdapter = new PhotoCursorAdapter(this,null);
        View emptyView = findViewById(R.id.empty_view);
        photoList.setEmptyView(emptyView);
        photoList.setAdapter(mCursorAdapter);
        LoaderManager.getInstance(this).initLoader(PHOTO_LOADER,null,this);
    }



    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String projection[] = {ProgressEntry._ID,
                                ProgressEntry.COLUMN_PHOTO_DATE,
                                ProgressEntry.COLUMN_PHOTO, };
        return new CursorLoader(getApplicationContext(),
                ProgressEntry.CONTENT_URI,
                projection,
                null,
                null,null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
            mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
            mCursorAdapter.swapCursor(null);
    }
}