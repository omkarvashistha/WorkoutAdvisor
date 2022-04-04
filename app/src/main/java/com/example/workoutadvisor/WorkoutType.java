package com.example.workoutadvisor;

public class WorkoutType {
    int Workout_Id;
    String Workout_Name;

    public WorkoutType(String workout_Name) {
        Workout_Name = workout_Name;
    }

    public int getWorkout_Id() {
        return Workout_Id;
    }

    public void setWorkout_Id(int workout_Id) {
        Workout_Id = workout_Id;
    }

    public String getWorkout_Name() {
        return Workout_Name;
    }

    public void setWorkout_Name(String workout_Name) {
        Workout_Name = workout_Name;
    }



}
