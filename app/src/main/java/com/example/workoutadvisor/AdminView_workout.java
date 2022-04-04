package com.example.workoutadvisor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class AdminView_workout extends AppCompatActivity {

    Spinner workout;
    TextView exercises;
    ListView workout_list;
    WorkoutExercises workoutExercises = new WorkoutExercises();
    SqlDbHelper sqlDbHelper ;
    SQLiteDatabase database;
    SliderView sliderView;
    int[] images = {R.drawable.workoutappmain,R.drawable.admin_layout,R.drawable.workout3, R.drawable.workout4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_workout);
        workout = findViewById(R.id.workout_type);

        String DbName = String.valueOf(R.style.Database_name);
        sqlDbHelper = new SqlDbHelper(this,DbName,null,1);
        database = sqlDbHelper.getWritableDatabase();

        sliderView = findViewById(R.id.imageView);
        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        ArrayList<String> e  = new ArrayList<String>();
        e = getAllExercises();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_layout,e){
            public View getView(int position, View convertView, ViewGroup parent){
                View v = super.getView(position,convertView,parent);
                ((TextView) v).setTextSize(30);
                ((TextView) v).setTextColor(Color.WHITE);
                ((TextView) v).setGravity(Gravity.CENTER);
                return v;
            }
            public View getDropDownView(int position,View convertView,ViewGroup parent){
                View v = super.getDropDownView(position,convertView,parent);

                ((TextView) v).setTextColor(Color.BLACK);
                ((TextView) v).setTextSize(30);
                return v;
            }
        };
        workout.setAdapter(adapter);
    }

//--------------------------------------------------------------------------------------------------
    public void GetWorkout(View view){
        workout = findViewById(R.id.workout_type);
        exercises = findViewById(R.id.exercises);
        workout_list = findViewById(R.id.workout_list);
        String workout_name = String.valueOf(workout.getSelectedItem());
        List<String> workoutList = workoutExercises.getWorkouts(workout_name);
        ArrayList<String> workoutDescription = workoutExercises.getDescription(workout_name);
        ArrayList<String> workoutLink = workoutExercises.getLink(workout_name);
        ArrayList<Integer> workoutImageList = workoutExercises.getImage(workout_name);
        ArrayAdapter<String> adapter = new CustomAdapter(AdminView_workout.this,workoutList,workoutImageList,workoutDescription,workoutLink,new MainActivity.BtnClickListner(){
            @Override
            public void onBtnClick(int position) {
                String ExerciseName = workoutList.get(position);
                String Description = workoutDescription.get(position);
                String Link = workoutLink.get(position);
                Intent intent = new Intent(getApplicationContext(), ExerciseDetails.class);
                intent.putExtra("Name", ExerciseName);
                intent.putExtra("Description", Description);
                intent.putExtra("Link", Link);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_up);
            }
        });
        
        workout_list.setAdapter(adapter);
        exercises.setText("Your "+workout_name+" Exercises are");
    }
 //-----------------------------------------------------------------------------------------------
        public  ArrayList<String> getAllExercises(){
            ArrayList<String> e1 = new ArrayList<String>();
            try{
                String q = "SELECT Workout_Name FROM WorkoutType ";
                Cursor c = database.rawQuery(q,null);
                c.moveToFirst();
                while(!c.isAfterLast()){
                    String x = c.getString(0);
                    e1.add(x);
                    c.moveToNext();
                }
                c.close();
                return e1;
            }
            catch (Exception e){
                return null;
            }
        }
//----------------------------------------------------------------------------------------------
}