package com.example.workoutadvisor.data;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.workoutadvisor.R;
import com.example.workoutadvisor.ViewProgress;

import java.sql.Date;

public class PhotoCursorAdapter extends CursorAdapter {
    public PhotoCursorAdapter(Context context, Cursor c) {
        super(context, c,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.progress_photo_layout,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ImageView ProgressImg = (ImageView) view.findViewById(R.id.progressImg);
        TextView photoDate =  (TextView) view.findViewById(R.id.photo_date);

        int photoIndex = cursor.getColumnIndex(WorkoutContract.ProgressEntry.COLUMN_PHOTO);
        int dateIndex = cursor.getColumnIndex(WorkoutContract.ProgressEntry.COLUMN_PHOTO_DATE);

        byte[] photo = cursor.getBlob(photoIndex);
        String date = cursor.getString(dateIndex);

        Bitmap bitmap = BitmapFactory.decodeByteArray(photo,0,photo.length);
        ProgressImg.setImageBitmap(bitmap);
        photoDate.setText(date);
    }
}
