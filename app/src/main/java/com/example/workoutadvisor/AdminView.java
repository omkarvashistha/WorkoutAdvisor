
package com.example.workoutadvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AdminView extends AppCompatActivity {
    TextView ViewWorkouts_admin,Register_workout_admin,Change_workout_admin,logout;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);
        ViewWorkouts_admin = findViewById(R.id.ViewWorkouts_admin);
        Register_workout_admin = findViewById(R.id.Register_workout_admin);
        Change_workout_admin = findViewById(R.id.Change_workout_admin);
        logout = findViewById(R.id.logout);

        ViewWorkouts_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminView.this,AdminView_workout.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });

        Register_workout_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminView.this,AddWorkouts.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });

        Change_workout_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminView.this,AddWorkouts.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManagement sessionManagement = new SessionManagement(AdminView.this);
                sessionManagement.RemoveSession();
                Intent intent1 = new Intent(getApplicationContext(),Login.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
    }
}