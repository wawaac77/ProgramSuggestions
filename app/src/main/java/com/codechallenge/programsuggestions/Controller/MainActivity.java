package com.codechallenge.programsuggestions.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.codechallenge.programsuggestions.R;
import com.codechallenge.programsuggestions.Model.Training;
import com.codechallenge.programsuggestions.Utils.LocalJsonResolutionUtils;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdaptar;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Training> mTrainings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            mTrainings = getTrainingsData(this.getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdaptar = new TrainingAdapter(mTrainings);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdaptar);

    }

    public ArrayList<Training> getTrainingsData(Context context) throws IOException {

        Type listType = new TypeToken<List<Training>>(){}.getType();
        String resultJson = LocalJsonResolutionUtils.getJson(context, "trainer_programs.json");
        ArrayList<Training> trainings = LocalJsonResolutionUtils.JsonToObject(resultJson, listType);

        for (int i = 0; i < trainings.size(); i ++) {
            Training training = trainings.get(i);
            Log.d("jsonData", "jsonData content:" + training.name);
        }
        Log.d("jsonData", "jsonData size:" + trainings.size());

        return trainings;
    }


}
