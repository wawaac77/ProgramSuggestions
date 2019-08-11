package com.codechallenge.programsuggestions.Controller;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codechallenge.programsuggestions.Model.AttributeDetail;
import com.codechallenge.programsuggestions.Model.Training;
import com.codechallenge.programsuggestions.R;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.TrainingViewHolder> {
    private ArrayList<Training> trainings;

    public static class TrainingViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTitleTextView;
        public TextView mSubTitleTextView;
        public LinearLayout mDropLinearLayout;
        public ImageView ivDropOne;
        public ImageView ivDropTwo;
        public ImageView ivDropThree;

        public ProgressBar weightLossProgressBar;
        public ProgressBar muscleProgessBar;
        public ProgressBar strengthProgessBar;
        public ProgressBar fitnessProgressBar;


        public TrainingViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTitleTextView = itemView.findViewById(R.id.titleTextView);
            mSubTitleTextView = itemView.findViewById(R.id.subTitleTextView);
            mDropLinearLayout = itemView.findViewById(R.id.dropLinearLayout);
            ivDropOne = itemView.findViewById(R.id.drop1);
            ivDropTwo = itemView.findViewById(R.id.drop2);
            ivDropThree = itemView.findViewById(R.id.drop3);
            weightLossProgressBar = itemView.findViewById(R.id.weightLossProgressBar);
            muscleProgessBar = itemView.findViewById(R.id.muscleProgressBar);
            strengthProgessBar = itemView.findViewById(R.id.strengthProgressBar);
            fitnessProgressBar = itemView.findViewById(R.id.fitnessProgressBar);
        }
    }

    public TrainingAdapter(ArrayList<Training> trainings) {
        this.trainings = trainings;
    }


    @Override
    public TrainingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.training_item, parent, false);
        TrainingViewHolder trainingViewHolder = new TrainingViewHolder(row);
        return trainingViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingViewHolder holder, int position) {
        Training currentItem = trainings.get(position);
        holder.mTitleTextView.setText(currentItem.name);
        holder.mSubTitleTextView.setText("with " + currentItem.trainer.name);
        Log.d("jsonData", "jsonData content:" + currentItem.image);
        Picasso.get().load(currentItem.image).placeholder(R.drawable.bbg_image).into(holder.mImageView);
        //drop
        if (currentItem.attributes.get(0).value.equals("0.0")) {
            holder.ivDropOne.setImageResource(R.drawable.sweat_drop);
        } else if (currentItem.attributes.get(0).value.equals("1.0")) {
            holder.ivDropOne.setImageResource(R.drawable.sweat_drop_filled);
        } else if (currentItem.attributes.get(0).value.equals("2.0")) {
            holder.ivDropOne.setImageResource(R.drawable.sweat_drop_filled);
            holder.ivDropTwo.setImageResource(R.drawable.sweat_drop_filled);
        } else if (currentItem.attributes.get(0).value.equals("3.0")) {
            holder.ivDropOne.setImageResource(R.drawable.sweat_drop_filled);
            holder.ivDropTwo.setImageResource(R.drawable.sweat_drop_filled);
            holder.ivDropThree.setImageResource(R.drawable.sweat_drop_filled);
        }
        //progress bar
        holder.weightLossProgressBar.setProgress(calculateProgress(currentItem.attributes.get(1)));
        holder.muscleProgessBar.setProgress(calculateProgress(currentItem.attributes.get(2)));
        holder.strengthProgessBar.setProgress(calculateProgress(currentItem.attributes.get(3)));
        holder.fitnessProgressBar.setProgress(calculateProgress(currentItem.attributes.get(4)));

    }

    @Override
    public int getItemCount() {
        return trainings.size();
    }


    static public int calculateProgress(AttributeDetail attributeDetail) {
       float divided =  Float.parseFloat(attributeDetail.value) / Float.parseFloat(attributeDetail.total);
       Log.d("progress", "progress calculate divided" + divided);
       divided = divided * 100;
       int result = (int)divided;
       Log.d("progress", "progress calculate" + result);
       return result;
    }

}
