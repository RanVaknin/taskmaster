package com.rafaelsdiamonds.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.squareup.picasso.Picasso;

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
        String imgForThisTask = getIntent().getStringExtra("TaskImg");
        ImageView taskImage = findViewById(R.id.taskImage);

        Picasso.get().load(imgForThisTask).into(taskImage);

        TextView locationText = findViewById(R.id.locationText);
        locationText.setText(getIntent().getStringExtra("TaskLocation"));

//       Log.e("rvrv", "assssss : " + imgForThisTask);

        Button deleteTaskButton = findViewById(R.id.deleteTaskButton);

        deleteTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
