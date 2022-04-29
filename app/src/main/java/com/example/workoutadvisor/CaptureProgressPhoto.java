package com.example.workoutadvisor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.workoutadvisor.data.WorkoutContract;
import com.example.workoutadvisor.data.WorkoutProvider;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CaptureProgressPhoto extends AppCompatActivity {

    Button takePhoto;
    ImageView userPhoto;
    ImageButton correct,wrong;
    Bitmap photo;

    WorkoutProvider provider;

    static int count;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_progress_photo);

        takePhoto = findViewById(R.id.clickPhoto);
        userPhoto = findViewById(R.id.progress_captured_pic);
        correct = findViewById(R.id.accept_photo);
        wrong = findViewById(R.id.reject_photo);
        count = 0;

        if(!hasCamera()){
            takePhoto.setEnabled(false);
        }

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCamera();
            }
        });



        correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPhoto();
            }
        });



    }

    public boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK ){
            count = 1;
            String temp  = String.valueOf(count);
            Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_SHORT).show();
            Bundle extras = data.getExtras();
            Bitmap pic = (Bitmap) extras.get("data");
            photo = pic;
            userPhoto.setImageBitmap(pic);
            correct.setVisibility(View.VISIBLE);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void launchCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
    }

    public void sendPhoto(){
//-----------------------------CONVERTING BITMAP TO BYTE ARRAY -------------------------------------
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG,0,stream);
        byte[] imageBytes = stream.toByteArray();
        photo.recycle();
//------------------------------GETTING CURRENT DATE------------------------------------------------
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM,yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
        Date date = new Date(System.currentTimeMillis());
        String currDate = dateFormat.format(date);


        Log.d("test", currDate);

        ContentValues values = new ContentValues();
        values.put(WorkoutContract.ProgressEntry.COLUMN_PHOTO,imageBytes);
        values.put(WorkoutContract.ProgressEntry.COLUMN_PHOTO_DATE,currDate);

        Uri uri = getContentResolver().insert(WorkoutContract.ProgressEntry.CONTENT_URI,values);

        if(uri==null){
            Toast.makeText(getApplicationContext(), "Sorry Photo cannot be inserted", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Photo inserted Successfully", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(getIntent());
        }

    }

}