

package com.example.workoutadvisor;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    TextView exercises,textView6;
    ListView workout_list;
    WorkoutExercises workoutExercises = new WorkoutExercises();
    Button b;
    Spinner workout;
    SliderView sliderView;
    static int count = 0;
    int[] images = {R.drawable.workoutappmain,R.drawable.admin_layout,R.drawable.workout3, R.drawable.workout4};

    private static final int uniqueId = 40104;

    SqlDbHelper sqlDbHelper;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count = 0;
        workout = findViewById(R.id.workout_type);
        b = findViewById(R.id.button);
        textView6 = findViewById(R.id.textView6);
        sliderView = findViewById(R.id.imageView);
        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();



//--------------------------------------------------------------------------------------------------

        String DbName = String.valueOf(R.style.Database_name);
        sqlDbHelper = new SqlDbHelper(this,DbName,null,1);
        database = sqlDbHelper.getWritableDatabase();

        ArrayList<String> e  = getAllExercises();
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
//---------------------------------TO GET WORKOUTS ON CLICK OF BUTTON------------------------------
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void GetWorkout(View view){
        String workout_name = String.valueOf(workout.getSelectedItem());

//-----------------------------ALERT DIALOG BOX----------------------------------------------------
        if(!workout_name.equals("WARMUP") && count==0){
            final AlertDialog.Builder alert  = new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("WARMUP DONE.?");
            alert.setMessage("Please do a proper warmup of at-least 5 minutes,If not done please go back and choose warmup option");
            alert.setCancelable(false);
            alert.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog = alert.create();
            alertDialog.show();
//-------------------------------------NOTIFICATION-------------------------------------------------
            RemoteViews lay_one = new RemoteViews(getPackageName(),R.layout.notification_layout);
            NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(),"channel_id")
                    .setSmallIcon(R.drawable.splashimg)
                    .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                    .setCustomContentView(lay_one)
                    .setCustomBigContentView(lay_one)
                    .setContentTitle("Workout Started")
                    .setContentText("Drink water broo")
                    .setChannelId("channel_id")
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setAutoCancel(false);
            Intent ii = new Intent(getApplicationContext(),MainActivity.class);
            PendingIntent pi = PendingIntent.getActivity(getApplicationContext(),0,ii,PendingIntent.FLAG_IMMUTABLE);
            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());
            managerCompat.notify(1001,notification.build());
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
                NotificationChannel channel  = new NotificationChannel("channel_id","notofication", NotificationManager.IMPORTANCE_DEFAULT);
                channel.setDescription("Workout Drink water");
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
            }
//---------------------------------------------------------------------------------------------------------------
        }
        count++;
//--------------------------------------------ELSE PART---------------------------------------------

        workout = findViewById(R.id.workout_type);
        exercises = findViewById(R.id.exercises);
        workout_list = findViewById(R.id.workout_list);
        try{
            List<String> workoutList = workoutExercises.getWorkouts(workout_name);
            ArrayList<String> workoutDescription = workoutExercises.getDescription(workout_name);
            ArrayList<String> workoutLink = workoutExercises.getLink(workout_name);
            ArrayList<Integer> workoutImageList = workoutExercises.getImage(workout_name);
            ArrayAdapter<String> adapter = new CustomAdapter(MainActivity.this, workoutList, workoutImageList, workoutDescription, workoutLink, new BtnClickListner() {
                @Override
                public void onBtnClick(int position) {
                    String ExerciseName = workoutList.get(position);
                    String Description = workoutDescription.get(position);
                    String Link = workoutLink.get(position);
                    Intent intent = new Intent(getApplicationContext(),ExerciseDetails.class);
                    intent.putExtra("Name",ExerciseName);
                    intent.putExtra("Description",Description);
                    intent.putExtra("Link",Link);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_down,R.anim.slide_out_up);
                }
            });
            workout_list.setAdapter(adapter);
            workout_list.setNestedScrollingEnabled(true);
            textView6.setVisibility(View.VISIBLE);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }
//---------------------------TO GET ALL EXERCISES FROM DATABASE-------------------------------------
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
 //------------------------------TO GET WORKOUT ID USING WORKOUT NAME-------------------------------
    public int getWorkoutId(String workout_name){
        workout_name = "\""+workout_name+"\"";
        int id = -1;
        try{
            String q = "SELECT Workout_Id FROM WorkoutType WHERE Workout_Name = "+workout_name;
            Cursor c = database.rawQuery(q,null);
            c.moveToFirst();
            id = c.getInt(0);
            return id;
        }
        catch (Exception e){
            return -1;
        }
    }
 //-------------------------------------CUSTOM LAYOUT BUTTON----------------------------------------
    public interface BtnClickListner{
        void onBtnClick(int position);
    }
}