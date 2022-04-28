package com.example.workoutadvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CaptureView extends AppCompatActivity {
    View capturePhoto,viewProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_view);
        capturePhoto = findViewById(R.id.capture_photo_view);
        viewProgress = findViewById(R.id.view_progress_screen);

        capturePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CaptureProgressPhoto.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });

        viewProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ViewProgress.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });
    }
}