package com.rafaelsdiamonds.taskmaster;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TestSetting {

    @Rule
    public ActivityTestRule<Settings> activityActivityTestRule = new ActivityTestRule<Settings>(Settings.class);

    @Test
    public void TestAutoComplete(){
    }
}


