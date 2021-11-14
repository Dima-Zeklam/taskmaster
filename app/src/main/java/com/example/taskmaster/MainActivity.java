package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button AddTask= findViewById(R.id.AddTaskButton);
        AddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AddTaskIntent = new Intent(MainActivity.this,AddTask.class);
                startActivity(AddTaskIntent);
            }
        });

        Button AllTasks= findViewById(R.id.AllTasksButton);
        AllTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AllTaskIntent = new Intent(MainActivity.this,AllTasks.class);
                startActivity(AllTaskIntent);
            }
        });
    }

}