package com.example.workoutadvisor;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.Locale;

public class AddWorkouts extends AppCompatActivity {

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sqlDbHelper.close();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    Spinner workout;
    ImageView Exercise_image;
    EditText name_of_exercise,workout_description,Video_link;
    TextView textView4;
    Button register_workout;
    Uri selectedImage;
    //boolean flag = true;
    String ImagePath = "";

    private SqlDbHelper sqlDbHelper;
    private SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workouts);
//----------------------------------------------DECLARING VIEWS---------------------------------------------------------
        workout = findViewById(R.id.workout_type);
        Exercise_image = findViewById(R.id.Exercise_image);
        name_of_exercise = findViewById(R.id.name_of_exercise);
        workout_description = findViewById(R.id.workout_description);
        register_workout = findViewById(R.id.register_workout);
        textView4 = findViewById(R.id.textView4);
        Video_link = findViewById(R.id.Video_link);

        String DbName = String.valueOf(R.style.Database_name);
        sqlDbHelper =  new SqlDbHelper(this,DbName,null,1);
        database = sqlDbHelper.getWritableDatabase();
//----------------------------------------------REQUESTING PERMISSION---------------------------------------------------------
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
//--------------------------------------------------EXERCISES IN SPINNER------------------------------------------------

        try{
            ArrayList<String> e;
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
        catch (Exception e){
            Log.d("test", e.getMessage().toString());
            register_workout.setText(e.getMessage().toString());
        }
//------------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------DATABASE----------------------------------------------------------------------------
//        try {
////            String q = "INSERT INTO Users(UserName,Password,Role)" +
////                    "VALUES('Omkarvashistha','Admin','ADMIN')";
////            database.execSQL(q);
//            String x = "DIAMOND PUSHUPS";
//            x = "\""+x+"\"";
//            String q = "SELECT Exercise_Link FROM Exercise WHERE Exercise_Name = "+x;
//            //String q = "SELECT UserName FROM Users WHERE UId=1";
//            Cursor c = database.rawQuery(q,null);
//            c.moveToFirst();
//            textView4.setText(c.getString(0));
//            c.close();
//        }
//        catch (Exception e){
//            Log.d("test", e.getMessage().toString());
//            textView4.setText(e.getMessage().toString());
//        }
//-----------------------------------------------------------------------------------------------------------------------
        ActivityResultLauncher<String> getaction = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        Exercise_image.setImageURI(result);
                        ImagePath = result.getPath();
                        selectedImage = result;
                    }
                }
        );
        Exercise_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getaction.launch("image/*");
            }
        });

        register_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerWorkout();
            }
        });
    }
//------------------------------------------------MAIN ENDS--------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
    public void registerWorkout(){
        boolean flag1 = true,flag2 = true;
        textView4.setText(ImagePath.toString());
        if(ImagePath.toString().length()==0){
            textView4.setTextColor(getResources().getColor(R.color.spinner_bg_2));
            textView4.setText("Image is Compulsory");
            flag1=false;
        }

        if(name_of_exercise.getText().toString().trim().length()==0 ){
            name_of_exercise.setError("Please enter excercise");
            flag1 = false;
        }
        if(workout_description.getText().toString().trim().length()==0){
            workout_description.setError("Please enter excercise description");
            flag2 = false;
        }

        if(flag1==true && flag2==true ){
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImage);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,0,byteArrayOutputStream);
                textView4.setText("No problem");
                ContentValues contentValues = new ContentValues();
                String workout_type = workout.getSelectedItem().toString();
                int WorkoutId = getWorkoutId(workout_type);
                String ExerciseName = name_of_exercise.getText().toString();
                String ExerciseDescription = workout_description.getText().toString();
                String VideoLink  = Video_link.getText().toString();

                if(VideoLink.trim().length()==0){
                    VideoLink = "No Video Available ";
                }

                byte[] bytesImage  = byteArrayOutputStream.toByteArray();
                ExerciseName = ExerciseName.toUpperCase();

                if(ExerciseExists(ExerciseName)!=-99){
                    textView4.setText("This Exercise Already Exists");
                    Log.d("test","message "+ExerciseExists(ExerciseName) );
                }
                else{
                    contentValues.put("Exercise_Name",ExerciseName);
                    contentValues.put("Exercise_Description",ExerciseDescription);
                    contentValues.put("Exercise_Image",bytesImage);
                    contentValues.put("Exercise_Link",VideoLink);
                    contentValues.put("Workout_Id",WorkoutId);
                    database.insert("Exercise",null,contentValues);
                    Toast.makeText(getApplicationContext(), "Values inserted sucessfully", Toast.LENGTH_LONG).show();
                    finish();
                    overridePendingTransition(0,0);
                    startActivity(getIntent());
                    overridePendingTransition(0,0);
                }

            }
            catch (Exception e){
                Toast.makeText(getApplicationContext(), "Some problem occured", Toast.LENGTH_SHORT).show();
            }
        }
    }
//------------------------------------------------------------------------------
    public int getWorkoutId(String workoutName){
        int id = -1;
        workoutName = "\""+workoutName+"\"";
        try{
            String query = "SELECT Workout_Id from WorkoutType WHERE Workout_Name="+workoutName;
            Cursor cursor = database.rawQuery(query,null);
            cursor.moveToFirst();
            id = cursor.getInt(0);
            cursor.close();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Cursor exception", Toast.LENGTH_SHORT).show();
        }
        return id;
    }
//----------------------------------------------------------------------------------------------------
    public static String getDefaultImagePath(int resourceId){
        return Uri.parse("android.resource://"+R.class.getPackage().getName()+"/"+resourceId).toString();
    }
//------------------------------------------------------------------------------------------------------

    public int ExerciseExists(String Exercise_name){
        Exercise_name = "\""+Exercise_name+"\"";
        try{
            String q = "SELECT Exercise_Id FROM Exercise WHERE Exercise_Name="+Exercise_name;
            Cursor c = database.rawQuery(q,null);
            c.moveToFirst();
            return c.getInt(0);
        }
        catch (Exception e){
            Log.d("test", e.getMessage().toString());
            return -99;
        }

    }
    public ArrayList<String> getAllExercises(){
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

}