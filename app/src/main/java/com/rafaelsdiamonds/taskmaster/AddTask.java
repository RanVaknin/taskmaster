package com.rafaelsdiamonds.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.taskmaster.R;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);

        Button b = findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(AddTask.this, "submitted!", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
