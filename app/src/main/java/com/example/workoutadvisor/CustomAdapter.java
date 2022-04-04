package com.example.workoutadvisor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {
    private ArrayList<Integer> exerciseImage;
    private List<String> workouts;
    private Activity context;
    private ArrayList<String> desc;
    private ArrayList<String> link;
    MainActivity main  = new MainActivity();
    private MainActivity.BtnClickListner listner = null;
    public CustomAdapter(@NonNull Activity context, List<String> workouts, ArrayList<Integer> images, ArrayList<String> desc, ArrayList<String> link, MainActivity.BtnClickListner listner) {
        super(context,R.layout.workout_layout,workouts);
        this.exerciseImage = images;
        this.context = context;
        this.listner  = listner;
        this.workouts = workouts;
        this.desc  = desc;
        this.link  = link;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View customView = inflater.inflate(R.layout.workout_layout,parent,false);

        TextView exercise_name =  (TextView) customView.findViewById(R.id.exercise_name);
        ImageView exercise_photo = (ImageView) customView.findViewById(R.id.exercise_photo);
        Button button = (Button) customView.findViewById(R.id.how_to_do);
        button.setTag(position);
        String val = String.valueOf(workouts.size());

        String workoutItem = workouts.get(position);
        exercise_name.setText(workoutItem);
        Integer pic = exerciseImage.get(position);
        exercise_photo.setImageResource(pic);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listner!=null){
                    listner.onBtnClick((Integer) v.getTag());
                }
            }
        });
        return customView;
    }
}
