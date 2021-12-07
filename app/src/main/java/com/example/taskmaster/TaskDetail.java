package com.example.taskmaster;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;

import java.io.File;

public class TaskDetail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        TextView title = findViewById(R.id.changableTitle);
        TextView body = findViewById(R.id.bodyText);
        TextView state = findViewById(R.id.stateText);
        String strTitle = getIntent().getExtras().get("title").toString();
        String strbody = getIntent().getExtras().get("body").toString();
        String strState = getIntent().getExtras().get("state").toString();
        title.setText(strTitle);
        body.setText(strbody);
        state.setText(strState);
//        fileName = intent.getExtras().getString("taskFileName");
        Amplify.Storage.downloadFile(
                "image",
                new File(getApplicationContext().getFilesDir() + "/download.jpg"),
                result -> {

                    ImageView image = findViewById(R.id.imgTaskDetails);
                    getIntent().getExtras().getString("file");
                    image.setImageBitmap(BitmapFactory.decodeFile(result.getFile().getPath()));

                    Log.i("MyAmplifyApp", "Successfully downloaded: " + result.getFile());
                },
                error -> Log.e("MyAmplifyApp", "Download Failure", error)
        );
    }

}





































