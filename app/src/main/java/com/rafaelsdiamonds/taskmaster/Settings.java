package com.rafaelsdiamonds.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taskmaster.R;
import com.google.android.material.textfield.TextInputLayout;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button button = findViewById(R.id.saveButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // capture input username
                EditText username = findViewById(R.id.username);
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPref.edit();
                // convert input to String type and save into shared preferences
                editor.putString("userName", username.getText().toString());
                editor.apply();

                Toast.makeText(getApplicationContext(), "Username saved!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
