package com.codechallenge.programsuggestions.Controller;

import com.codechallenge.programsuggestions.Model.Training;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivityTest {
    @Test
    public void should_return_traning_array() throws IOException {
        MainActivity mainActivity = new MainActivity();
        ArrayList<Training> trainings = mainActivity.getTrainingsData(null); //not get context yet
        Assert.assertTrue(trainings.size() > 0);

    }

}