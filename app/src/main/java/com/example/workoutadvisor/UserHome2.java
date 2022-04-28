package com.example.workoutadvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workoutadvisor.data.WorkoutContract;

public class UserHome2 extends AppCompatActivity {

    TextView  startText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home2);
        startText = findViewById(R.id.start_text);



//        Intent intent = getIntent();
//        String Username = intent.getStringExtra("Name");
//        Username = Username.toUpperCase();
//        startText.setText("Hi "+Username);

    }


    public void ViewWorkouts(View view) {
        Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent1);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    public void Warmup(View view) {
        Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent1);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }


    public void CapturePic(View view) {
        Intent intent1 = new Intent(getApplicationContext(),CaptureView.class);
        startActivity(intent1);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    public void logout(View view) {
        SessionManagement sessionManagement = new SessionManagement(UserHome2.this);
        sessionManagement.RemoveSession();
        Intent intent1 = new Intent(getApplicationContext(),Login.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent1);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}