package com.example.workoutadvisor;

import android.content.Context;
import android.content.SharedPreferences;

import kotlinx.coroutines.channels.ActorKt;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "session_Id";
    String SESSION_USER = "session_User";

    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(Users user){
        int id = user.getId();
        String userName = user.getUserName();
        editor.putInt(SESSION_KEY,id).commit();
        editor.putString(SESSION_USER,userName).commit();
    }

    public int getSession(){
        return sharedPreferences.getInt(SESSION_KEY,-1);
    }

    public String getSessionUser(){
        return sharedPreferences.getString(SESSION_USER,"NO_USER");
    }

    public void RemoveSession(){
        editor.putInt(SESSION_KEY,-1).commit();
    }
}
