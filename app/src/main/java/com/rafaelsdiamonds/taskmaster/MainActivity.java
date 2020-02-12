package com.rafaelsdiamonds.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.taskmaster.R;

public class MainActivity extends AppCompatActivity {
    Button laundryButton;
    Button cleanButton;
    Button cookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button addTaskButton = findViewById(R.id.add_task);

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddTask.class);
                startActivity(i);

            }
        });


        Button allTasksButton = findViewById(R.id.button3);
        allTasksButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i2 = new Intent(MainActivity.this, AllTasks.class);
                startActivity(i2);
            }
        });

        laundryButton = findViewById(R.id.laundryButton);
        laundryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TaskDetail.class).putExtra("Task",laundryButton.getText().toString());
                startActivity(i);
            }
        });

        cleanButton = findViewById(R.id.cleanButton);
        cleanButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TaskDetail.class).putExtra("Task",cleanButton.getText().toString());
                startActivity(i);
            }
        });

        cookButton = findViewById(R.id.cookButton);
        cookButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TaskDetail.class).putExtra("Task",cookButton.getText().toString());
                startActivity(i);
            }
        });


        Button settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Settings.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        TextView myTasks = findViewById(R.id.myTasks);
        String usernameDisplay = sharedPref.getString("userName","My Tasks");

        if(!usernameDisplay.equals("My Tasks")){
            myTasks.setText(usernameDisplay + "'s Tasks");

        }
    }
}
