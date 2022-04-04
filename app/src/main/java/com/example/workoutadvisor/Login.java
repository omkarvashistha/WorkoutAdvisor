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

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
    EditText Username,password;
    Button login;
    TextView register_user,forgot_pass,login_errorMsg;
    ArrayList<String> exercises = new ArrayList<>();
    private SqlDbHelper sqlDbHelper;
    private SQLiteDatabase database;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sqlDbHelper.close();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Code to check if user is already logged in
        SessionManagement sessionManagement = new SessionManagement(Login.this);
        int userId = sessionManagement.getSession();
        String userName = sessionManagement.getSessionUser();
        String Role = getRole(userName);
        if(userId!=-1){
            movetoActivity(Role,userName);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Username = findViewById(R.id.Register_Username);
        password = findViewById(R.id.Register_password);
        register_user = findViewById(R.id.register_user);
        login = findViewById(R.id.register);
        forgot_pass = findViewById(R.id.forgot_password);
        login_errorMsg = findViewById(R.id.login_errorMsg);

        String DbName = String.valueOf(R.style.Database_name);
        sqlDbHelper = new SqlDbHelper(this,DbName,null,1);
        database = sqlDbHelper.getWritableDatabase();



        register_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                //overridePendingTransition(R.anim.slide_in_down,R.anim.slide_out_up); this is for particular workout transition
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if(Username.getText().toString().trim().length()==0){
                      Username.setError("Enter Username");
                  }
                  else if(password.getText().toString().trim().length()==0){
                      password.setError("Enter Password");
                  }
                  else {
                      String user = Username.getText().toString();
                      String pass = password.getText().toString();
                      String Role = getRole(user);
                      int Id = getId(user);
                      Users user1 = new Users(Id,user);
                      SessionManagement sessionManagement = new SessionManagement(Login.this);
                      sessionManagement.saveSession(user1);
                      if (CheckUser(user,pass)) {
                          Toast.makeText(getApplicationContext(), "Correct username and password", Toast.LENGTH_SHORT).show();
                          if(Role.equals("ADMIN")){
                              Intent  intent = new Intent(getApplicationContext(),AdminView.class);
                              intent.putExtra("Name",user);
                              intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                              startActivity(intent);
                              overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                          }
                          else{
                              Intent  intent = new Intent(getApplicationContext(),UserHome2.class);
                              intent.putExtra("Name",user);
                              intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                              startActivity(intent);
                              overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                          }
                      } else {
                          login_errorMsg.setVisibility(View.VISIBLE);
                          login_errorMsg.setText("This user does not exists.\nIf not a user SignUp");
                      }
                  }
              }
        });
    }
//-----------------------------------------------------------------------------------------
    public boolean UsernameExists(String Username){
        Username = "\""+Username+"\"";
        try {
            String q = "SELECT UId  FROM Users WHERE Username ="+Username;
            Cursor c = database.rawQuery(q,null);
            c.moveToLast();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
//----------------------------------------------------------------------------------
    public boolean CheckUser(String user,String pass){
        user = "\""+user+"\"";
        pass = "\""+pass+"\"";
        try {
            String q = "SELECT UId FROM Users WHERE UserName = "+user+" AND Password = "+pass;
            Cursor c = database.rawQuery(q,null);
            c.moveToFirst();
            int ans = c.getInt(0);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
//----------------------------------------------------------------------------------
    public String getRole(String user){
        String role = "Not Found";
        user="\""+user+"\"";
        try{
            String q = "SELECT Role FROM Users WHERE Username ="+user;
            Cursor c = database.rawQuery(q,null);
            c.moveToFirst();
            role = c.getString(0);
            c.close();
            return role;
        }
        catch (Exception e){
            return role;
        }
    }
//----------------------------------------------------------------------------------------
    public int getId(String user){
        int Id = -1;
        user="\""+user+"\"";
        try{
            String q = "SELECT UId FROM Users WHERE Username ="+user;
            Cursor c = database.rawQuery(q,null);
            c.moveToFirst();
            Id = c.getInt(0);
            c.close();
            return Id;
        }
        catch (Exception e){
            return Id;
        }
    }
//----------------------------------------------------------------------------------------
    public void movetoActivity(String Role,String user){
        if(Role.equals("ADMIN")){
            Intent  intent = new Intent(getApplicationContext(),AdminView.class);
            intent.putExtra("Name",user);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            finish();
        }
        else{
            Intent  intent = new Intent(getApplicationContext(),UserHome2.class);
            intent.putExtra("Name",user);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            finish();
        }
    }
}


