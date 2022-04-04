package com.example.workoutadvisor;

import java.sql.Blob;

public class Exercises {
    int Exercise_Id,Workout_Id;
    Blob Exercise_Image;
    String Exercise_Name,Exercise_Description;

    public Exercises(int workout_Id, Blob exercise_Image, String exercise_Name, String exercise_Description) {
        Workout_Id = workout_Id;
        Exercise_Image = exercise_Image;
        Exercise_Name = exercise_Name;
        Exercise_Description = exercise_Description;
    }

    public int getExercise_Id() {
        return Exercise_Id;
    }

    public void setExercise_Id(int exercise_Id) {
        Exercise_Id = exercise_Id;
    }

    public int getWorkout_Id() {
        return Workout_Id;
    }

    public void setWorkout_Id(int workout_Id) {
        Workout_Id = workout_Id;
    }

    public Blob getExercise_Image() {
        return Exercise_Image;
    }

    public void setExercise_Image(Blob exercise_Image) {
        Exercise_Image = exercise_Image;
    }

    public String getExercise_Name() {
        return Exercise_Name;
    }

    public void setExercise_Name(String exercise_Name) {
        Exercise_Name = exercise_Name;
    }

    public String getExercise_Description() {
        return Exercise_Description;
    }

    public void setExercise_Description(String exercise_Description) {
        Exercise_Description = exercise_Description;
    }
}
