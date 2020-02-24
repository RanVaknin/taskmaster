package com.rafaelsdiamonds.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.amazonaws.amplify.generated.graphql.CreateTaskMutation;
import com.amazonaws.amplify.generated.graphql.ListTeamsQuery;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.taskmaster.R;

import java.util.HashMap;

import javax.annotation.Nonnull;

import type.CreateTaskInput;

public class AddTask extends AppCompatActivity {

    private AWSAppSyncClient mAWSAppSyncClient;
    RadioButton radioButton;
    int[] buttons = new int[3];
    RadioGroup radioGroup;
    int teamPepsiRadioButtonId;
    int teamSpriteRadioButtonId;
    int teamCokeRadioButtonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);
        radioGroup = (RadioGroup) findViewById(R.id.taskTeamRadioGroup);
        int radioGroupSize = radioGroup.getChildCount();
        buttons[0] = teamPepsiRadioButtonId;
        buttons[1] = teamSpriteRadioButtonId;
        buttons[2] = teamCokeRadioButtonId;

        for(int i = 0 ; i < radioGroupSize ; i ++){
            buttons[i] = radioGroup.getChildAt(i).getId();
        }



        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();


        ListTeamsQuery teams = ListTeamsQuery.builder().build();
        final HashMap<String, String> teamHashmap = new HashMap<>();
        mAWSAppSyncClient.query(teams).enqueue(new GraphQLCall.Callback<ListTeamsQuery.Data>() {
            @Override
            public void onResponse(@Nonnull Response<ListTeamsQuery.Data> response) {
                for (ListTeamsQuery.Item team : response.data().listTeams().items()) {
                    teamHashmap.put(team.name(), team.id());
                }
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                Log.i("ran", "failed to pull from db");
            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });

        Button b = findViewById(R.id.saveTaskToDbButton);

        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int selectedId = radioGroup.getCheckedRadioButtonId();
                System.out.println("selectedId = " + selectedId);
                System.out.println("buttons: "+buttons[0]+" "+ buttons[1]+" " + buttons[2]);

                // determining the string value of radio button based on auto generated id number of radio number.
                String selectedTeamTextToChange = "teamPepsi";
                if(selectedId == buttons[0]){
                    selectedTeamTextToChange = "teamPepsi";
                } else if (selectedId == buttons[1]){
                    selectedTeamTextToChange = "teamSprite";
                } else if (selectedId == buttons[2]){
                    selectedTeamTextToChange = "teamCoke";
                }
                EditText taskTitle = findViewById(R.id.addTaskTitle);
                EditText taskBody = findViewById(R.id.addTaskBody);
                setContentView(R.layout.activity_main);
                Log.e("rvrv",selectedTeamTextToChange);
                System.out.println("selectedTeamTextToChange = " + selectedTeamTextToChange);
                System.out.println("this thing is amazing" +teamHashmap.get(selectedTeamTextToChange));

                CreateTaskInput createTaskInput = CreateTaskInput.builder()
                        .title(taskTitle.getText().toString())
                        .body(taskBody.getText().toString())
                        .taskTeamId(teamHashmap.get(selectedTeamTextToChange))
                        .state("new")
                        .build();


                mAWSAppSyncClient.mutate(CreateTaskMutation.builder().input(createTaskInput).build()).enqueue(
                        new GraphQLCall.Callback<CreateTaskMutation.Data>() {
                            @Override
                            public void onResponse(@Nonnull Response<CreateTaskMutation.Data> response) {
                                Log.e("rvrv", response.data().toString());
                            }

                            @Override
                            public void onFailure(@Nonnull ApolloException e) {
                                Log.e("rvrv", "failed to save to database");
                            }
                        }
                );
                finish();
            }


        });

    }

}
