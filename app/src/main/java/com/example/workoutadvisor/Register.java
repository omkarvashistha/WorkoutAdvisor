package com.example.workoutadvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    @Override
    protected void onDestroy() {
        super.onDestroy();
        sqlDbHelper.close();
    }

    EditText Register_Username, Register_password;
    Button register;
    TextView already_user,register_errorMsg;
    SqlDbHelper sqlDbHelper;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register_errorMsg = findViewById(R.id.register_errorMsg);
        Register_password = findViewById(R.id.Register_password);
        Register_Username = findViewById(R.id.Register_Username);
        register = findViewById(R.id.register);
        already_user = findViewById(R.id.forgot_password);

        String DbName = String.valueOf(R.style.Database_name);
        sqlDbHelper =  new SqlDbHelper(this,DbName,null,1);
        database = sqlDbHelper.getWritableDatabase();

        try {
                // TABLE 1
            String Users_table = "CREATE TABLE Users(" +
                    "UId INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "UserName TEXT NOT NULL," +
                    "Password TEXT NOT NULL," +
                    "Role TEXT NOT NULL);";
            database.execSQL(Users_table);

            String q = "INSERT INTO Users(UserName,Password,Role)" +
                    "VALUES('omkarvashistha','admin','ADMIN')";
            database.execSQL(q);

                // TABLE 2
            String Workout_type = "CREATE TABLE WorkoutType(" +
                    "Workout_Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Workout_Name TEXT NOT NULL);";
            database.execSQL(Workout_type);

            q = "INSERT INTO WorkoutType (Workout_Name)" +
                    "VALUES('PUSH-1')," +
                    "('PUSH-2')," +
                    "('PULL-1')," +
                    "('PULL-2')," +
                    "('LEGS')," +
                    "('ABS/CORE')," +
                    "('CHEST')," +
                    "('TRICEPS')," +
                    "('SHOULDERS')," +
                    "('BACK')," +
                    "('BICEPS')";
            database.execSQL(q);

            String Progress = "CREATE TABLE Progress(" +
                    "Progress_Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Photo BLOB NOT NULL," +
                    "Photo_Date Date NOT NULL);";;
            database.execSQL(Progress);

            //Toast.makeText(getApplicationContext(), "DB CREATED SUCESSFULLY", Toast.LENGTH_LONG).show();
        }
        catch(Exception e){
                e.printStackTrace();
                //Toast.makeText(getApplicationContext(), "DB ALREADY EXISTS", Toast.LENGTH_LONG).show();
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Register_Username.getText().toString().trim().length() == 0){
                    Register_Username.setError("UserName is Compulsory");
                }
                else if(Register_password.getText().toString().trim().length()==0){
                    Register_password.setError("Password is Compulsory");
                }
                else{
                    String Username = Register_Username.getText().toString().toLowerCase();
                    String password = Register_password.getText().toString().toLowerCase();
                    if(Username.contains(" ")){
                        register_errorMsg.setText("Please remove space from Username");
                        register_errorMsg.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "on space", Toast.LENGTH_SHORT).show();
                    }
                    else if(UsernameExists(Username)){
                        register_errorMsg.setText("This Username already Exists Either Log in or try different Username");
                        register_errorMsg.setVisibility(View.VISIBLE);
                    }
                    else{
                        boolean ans  = RegisterUser(Username,password,"User");
                        if(ans){
                            register_errorMsg.setVisibility(View.VISIBLE);
                            Toast.makeText(getApplicationContext(), "User registered sucessfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(),Login.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                        }
                        else{
                            register_errorMsg.setVisibility(View.VISIBLE);
                            Toast.makeText(getApplicationContext(), "Not Sucessfull", Toast.LENGTH_SHORT).show();
                            register_errorMsg.setText("Some problem ocurred please try again");
                        }
                    }
                }
            }
        });

        already_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });




    }

    public boolean UsernameExists(String Username){
        Username = "\""+Username+"\"";
        try {
            String q = "SELECT UId  FROM Users WHERE Username ="+Username;
            Cursor c = database.rawQuery(q,null);
            c.moveToFirst();
            int ans  = c.getInt(0);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public boolean RegisterUser(String user,String pass,String role){
        user = "'"+user+"'";
        pass = "'"+pass+"'";
        role = "'"+role+"'";
        try {
            String q = "INSERT INTO Users (UserName,Password,Role) " +
                    "VALUES("+user+","+pass+","+role+")";
            database.execSQL(q);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}