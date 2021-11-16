package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnTasksListener{
    private RecyclerView mRecyclerView;
    private ArrayList<TaskModel> tasksList = new ArrayList<>();
    private TaskAdapter taskAdapterObject;
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
//        Button button1= findViewById(R.id.button1);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String titleOne = "Title One";
//
//                Intent AllTaskIntent = new Intent(MainActivity.this,TaskDetail.class);
//                AllTaskIntent.putExtra("title",titleOne);
//                startActivity(AllTaskIntent);
//
//            }
//        });
//        Button button2= findViewById(R.id.button2);
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String titleTwo = "Title two";
//                Intent AllTaskIntent = new Intent(MainActivity.this,TaskDetail.class);
//                AllTaskIntent.putExtra("title",titleTwo);
//                startActivity(AllTaskIntent);
//            }
//        });
//        Button button3= findViewById(R.id.button3);
//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String titleThree = "Title Three";
//                Intent AllTaskIntent = new Intent(MainActivity.this,TaskDetail.class);
//                AllTaskIntent.putExtra("title",titleThree);
//                startActivity(AllTaskIntent);
//            }
//        });


    Button settingButton = findViewById(R.id.settingButton);
        settingButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent AllTaskIntent = new Intent(MainActivity.this,Settings.class);
            startActivity(AllTaskIntent);
        }
    });

        List<TaskModel> allTasks = new ArrayList<TaskModel>();
        allTasks.add(new TaskModel("Task one ","Do workout","new"));
        allTasks.add(new TaskModel("Task two","Do Assignments","assigned"));
        allTasks.add(new TaskModel("Task three","Write the blog","progress"));
        allTasks.add(new TaskModel("Task four","Drive the car","complete"));
        RecyclerView recyclerView = findViewById(R.id.allTasksRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskAdapter(allTasks,this));


}
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userName = sharedPreferences.getString("userName","no username yet!");
        TextView userNameText = findViewById(R.id.userName);
        userNameText.setText(userName+ "'s Tasks");

    }

    @Override
    public void onTaskClick(int position, TaskModel task) {
        Intent intent = new Intent(this, TaskDetail.class);
        intent.putExtra("title",task.title);
        intent.putExtra("body",task.body);
        startActivity(intent);
    }
}
