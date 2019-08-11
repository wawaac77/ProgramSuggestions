package com.codechallenge.programsuggestions.Controller;

import com.codechallenge.programsuggestions.Model.AttributeDetail;

import org.junit.Assert;
import org.junit.Test;

public class TrainingAdapterTest {

    @Test
    public void should_return_calculated_percent() {
        int result = TrainingAdapter.calculateProgress(new AttributeDetail("3.5", "5.0"));
        Assert.assertEquals(70, result);
    }

}