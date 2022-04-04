package com.example.workoutadvisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.VideoView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class ExerciseDetails extends AppCompatActivity {
    String videoId;
    String ExerciseName;
    String Description;
    TextView Exercise_name,Exercise_description;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_down);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);
        Exercise_name = findViewById(R.id.details_Ename);
        Exercise_description = findViewById(R.id.exerciseDescription);

        Intent intent = getIntent();
        ExerciseName = intent.getStringExtra("Name");
        Description = intent.getStringExtra("Description");
        videoId = intent.getStringExtra("Link");
        Exercise_name.setText(ExerciseName);
        Exercise_description.setText(Description);


        YouTubePlayerView youTubePlayerView = findViewById(R.id.Video_player);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                youTubePlayer.loadVideo(videoId,0);
                youTubePlayer.pause();
            }
        });
    }
}