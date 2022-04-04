package com.example.workoutadvisor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapter extends SliderViewAdapter<SliderViewAdapter.ViewHolder> {

    int[] images;

    public SliderAdapter(int[] images) {
        this.images = images;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slider_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        ImageView imageView = viewHolder.itemView.findViewById(R.id.slider_image_view);
        imageView.setImageResource(images[position]);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    public class Holder extends SliderViewAdapter.ViewHolder{
        ImageView imageView;

        public Holder (View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.slider_image_view);
        }
    }

}
