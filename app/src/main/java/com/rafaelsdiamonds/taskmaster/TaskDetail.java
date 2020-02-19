package com.rafaelsdiamonds.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.taskmaster.R;

public class TaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        TextView addTask = findViewById(R.id.addTask);
        addTask.setText(getIntent().getStringExtra("Task"));
        TextView description = findViewById(R.id.description);
        description.setText(getIntent().getStringExtra("TaskDetails"));


    }


}
