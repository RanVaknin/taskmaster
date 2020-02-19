package com.rafaelsdiamonds.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taskmaster.R;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);

        final Button b = findViewById(R.id.button);
        final TaskFragment tf = new TaskFragment();
        final TaskDatabase db = Room.databaseBuilder(getApplicationContext(), TaskDatabase.class, "task").allowMainThreadQueries().build();

        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText taskTitle = findViewById(R.id.addTaskTitle);
                EditText taskBody = findViewById(R.id.addTaskBody);
                db.taskDao().insertTask(new Task(taskTitle.getText().toString(), taskBody.getText().toString()));
                Intent i = new Intent(AddTask.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}
