package com.rafaelsdiamonds.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.google.android.material.textfield.TextInputLayout;

public class Settings extends AppCompatActivity {

    private AWSAppSyncClient mAWSAppSyncClient;
    private static final int teamPepsiRadioButtonId = 2131230914;//first radio button id
    private static final int teamSpriteRadioButtonId = 2131230913;//second radio button id
    private static final int teamCokeRadioButtonId = 2131230912;//third radio button id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();

        Button button = findViewById(R.id.saveButton);

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.settingPageTeam);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // capture input team

                int selectedId = radioGroup.getCheckedRadioButtonId();
                String chosenTeam = "";
                if(selectedId == teamPepsiRadioButtonId){
                    chosenTeam = "teamPepsi";
                } else if (selectedId == teamSpriteRadioButtonId){
                    chosenTeam = "teamSprite";
                } else if (selectedId == teamCokeRadioButtonId){
                    chosenTeam = "teamCoke";
                }

                // capture input username
                EditText username = findViewById(R.id.username);
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPref.edit();
                // convert input to String type and save into shared preferences
                editor.putString("teamName", chosenTeam);
                editor.putString("userName", username.getText().toString());
                editor.apply();
                Toast.makeText(getApplicationContext(), "Details saved!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
