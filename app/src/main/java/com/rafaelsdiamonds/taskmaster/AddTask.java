package com.rafaelsdiamonds.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amazonaws.amplify.generated.graphql.CreateTaskMutation;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.taskmaster.R;

import javax.annotation.Nonnull;

import type.CreateTaskInput;

public class AddTask extends AppCompatActivity {

    private AWSAppSyncClient mAWSAppSyncClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);



        Button b = findViewById(R.id.saveTaskToDbButton);

        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText taskTitle = findViewById(R.id.addTaskTitle);
                EditText taskBody = findViewById(R.id.addTaskBody);
                setContentView(R.layout.activity_main);

                mAWSAppSyncClient = AWSAppSyncClient.builder()
                        .context(getApplicationContext())
                        .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                        .build();

                CreateTaskInput createTaskInput = CreateTaskInput.builder()
                        .title(taskTitle.getText().toString())
                        .body(taskBody.getText().toString())
                        .state("new")
                        .build();

                mAWSAppSyncClient.mutate(CreateTaskMutation.builder().input(createTaskInput).build()).enqueue(
                        new GraphQLCall.Callback<CreateTaskMutation.Data>() {
                            @Override
                            public void onResponse(@Nonnull Response<CreateTaskMutation.Data> response) {
                                Log.i("ran", response.data().toString());
                            }

                            @Override
                            public void onFailure(@Nonnull ApolloException e) {
                                Log.i("ran", "failed to save to database");
                            }
                        }
                );
                Intent i = new Intent(getApplicationContext(),AllTasks.class);
                startActivity(i);
            }


        });

    }

}
