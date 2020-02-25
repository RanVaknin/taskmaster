package com.rafaelsdiamonds.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amazonaws.amplify.generated.graphql.ListTasksQuery;
import com.amazonaws.amplify.generated.graphql.ListTeamsQuery;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.SignOutOptions;
import com.amazonaws.mobile.client.UserState;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.services.cognitoidentityprovider.model.GetUserAttributeVerificationCodeRequest;
import com.example.taskmaster.R;

public class MainActivity extends AppCompatActivity {
    Button laundryButton;
    Button cleanButton;
    Button cookButton;
    RecyclerView recyclerView;
    private AWSAppSyncClient mAWSAppSyncClient;
    Team teamSprite;
    Team teamPepsi;
    Team teamCoke;
    String username = "";

    String[] taskName;
    String[] taskState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);
        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();


        // auth initialization
        AWSMobileClient.getInstance().initialize(getApplicationContext(), new Callback<UserStateDetails>() {

                    @Override
                    public void onResult(UserStateDetails userStateDetails) {
                        Log.i("rvrv", "onResult: " + userStateDetails.getUserState());
                        if (userStateDetails.getUserState().equals(UserState.SIGNED_OUT)) {
                            loginUi();
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e("rvrv", "Initialization error.", e);
                    }
                }
        );



        Button addTaskButton = findViewById(R.id.add_task);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AWSMobileClient.getInstance().signOut(SignOutOptions.builder().signOutGlobally(true).build(), new Callback<Void>() {
                    @Override
                    public void onResult(final Void result) {
                        Log.d("rvrv", "signed-out");
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e("rvrv", "sign-out error", e);
                    }
                });
                AWSMobileClient.getInstance().signOut();
                loginUi();
            }
        });

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddTask.class);
                startActivity(i);
            }
        });


        // link to all tasks window
        Button allTasksButton = findViewById(R.id.button3);
        allTasksButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i2 = new Intent(MainActivity.this, AllTasks.class);
                startActivity(i2);
            }
        });


        // link to setting page.
        Button settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Settings.class);
                startActivity(i);
            }
        });


    }

    public void loginUi(){
        AWSMobileClient.getInstance().showSignIn(MainActivity.this, new Callback<UserStateDetails>() {
            @Override
            public void onResult(UserStateDetails result) {
                Log.d("rvrv", "onResult: " + result.getUserState());
                try {
                    username = AWSMobileClient.getInstance().getUserAttributes().get("given_name");
                }
                catch(Exception e) {
                    Log.e("rvrv", "onError: ", e);
                }

            }

            @Override
            public void onError(Exception e) {
                Log.e("rvrv", "onError: ", e);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        TextView myTasks = findViewById(R.id.myTasks);
        String usernameDisplay = sharedPref.getString("userName", "My Tasks");

        if (!usernameDisplay.equals("My Tasks")) {
            myTasks.setText(usernameDisplay + "'s Tasks");

        }

        setTitle(username + "'s tasks");


    }


}
