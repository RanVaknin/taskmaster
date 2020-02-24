package com.rafaelsdiamonds.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.amazonaws.amplify.generated.graphql.CreateTaskMutation;
import com.amazonaws.amplify.generated.graphql.ListTeamsQuery;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.taskmaster.R;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nonnull;

import type.CreateTaskInput;

public class TaskDetail extends AppCompatActivity {

    private AWSAppSyncClient mAWSAppSyncClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);


        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();


        final TextView addTask = findViewById(R.id.addTask);
        addTask.setText(getIntent().getStringExtra("Task"));
        final TextView description = findViewById(R.id.description);
        description.setText(getIntent().getStringExtra("TaskDetails"));
        final TextView teamForThisTask = findViewById(R.id.teamForThisTask);
        teamForThisTask.setText(getIntent().getStringExtra("TaskTeam"));





        Button deleteTaskButton = findViewById(R.id.deleteTaskButton);

        deleteTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
