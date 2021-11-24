package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnTasksListener{
    private ArrayList<Task> tasksList = new ArrayList<>();
    AtomicReference<List<com.amplifyframework.datastore.generated.model.Task>> tasks1 = new AtomicReference<>(new ArrayList<>());
    private RecyclerView recyclerView;

    //OnCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Add task button
        Button AddTask= findViewById(R.id.AddTaskButton);
        AddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AddTaskIntent = new Intent(MainActivity.this,AddTask.class);
                startActivity(AddTaskIntent);
            }
        });

        //allTasks
        Button AllTasks= findViewById(R.id.AllTasksButton);
        AllTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AllTaskIntent = new Intent(MainActivity.this,AllTasks.class);
                startActivity(AllTaskIntent);
            }
        });

        //Setting button
        Button settingButton = findViewById(R.id.settingButton);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AllTaskIntent = new Intent(MainActivity.this,Settings.class);
                startActivity(AllTaskIntent);
            }
        });

//        List<TaskModel> allTasks = new ArrayList<TaskModel>();
//        allTasks.add(new TaskModel("Task one ","Do workout","new"));
//        allTasks.add(new TaskModel("Task two","Do Assignments","assigned"));
//        allTasks.add(new TaskModel("Task three","Write the blog","progress"));
//        allTasks.add(new TaskModel("Task four","Drive the car","complete"));
//        AppDatabase appDb = AppDatabase.getInstance(getApplicationContext());
//        TaskDAO taskDao = appDb.taskDao();
//        List<Task> tasks = taskDao.getAll();

//        try {
//            // Add these lines to add the AWSApiPlugin plugins
//            Amplify.addPlugin(new AWSApiPlugin());
//            Amplify.configure(getApplicationContext());
//
//            Log.i("MyAmplifyApp", "Initialized Amplify");
//        } catch (AmplifyException error) {
//            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
//        }
//        System.out.println("tasks:::::::::::: "+tasks.size());
//        RecyclerView recyclerView = findViewById(R.id.allTasksRecycleView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new TaskAdapter(tasks,this));
//
//        ArrayList<com.amplifyframework.datastore.generated.model.Task> tasksFirst =new  ArrayList<>();
//
//
//        Amplify.API.query(
//                ModelQuery.list(com.amplifyframework.datastore.generated.model.Task.class),
//                response -> {
//                    for (com.amplifyframework.datastore.generated.model.Task task : response.getData()) {
//                        Log.i("MyAmplifyApp", task.getTitle());
//                        tasksFirst.add(task);
//
//                    }
//                    tasks1.set(tasksFirst);
//                    System.out.println(tasks1);
//
//                },
//                error -> Log.e("MyAmplifyApp", "Query failure", error)
//        );
//        try {
//            Amplify.addPlugin(new AWSDataStorePlugin());
//            Amplify.configure(getApplicationContext());
//
//            Log.i("Tutorial", "Initialized Amplify");
//        } catch (AmplifyException e) {
//            Log.e("Tutorial", "Could not initialize Amplify", e);
//        }

// getting data from database .
//        List<Task> Tasks = new ArrayList();
//        Amplify.DataStore.query(
//                Task.class,
//                items -> {
//                    while (items.hasNext()) {
//                        Task item = items.next();
//                        Tasks.add(item);
//                        Log.i("Amplify", "Id " + item.getTitle());
//                    }
//                },
//                failure -> Log.e("Amplify", "Could not query DataStore", failure)
//        );

//        System.out.println("tasks:::::::::::: ");
//
//        System.out.println("tasks:::::::::::: "+Tasks.size());
//
//        TaskAdapter taskAdapter = new TaskAdapter(Tasks, this);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.canScrollVertically();
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(taskAdapter);
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
    public void onTaskClick(int position, Task task) {
        Intent intent = new Intent(this, TaskDetail.class);
        intent.putExtra("title",task.getTitle());
        intent.putExtra("body",task.getBody());
        intent.putExtra("state",task.getState());
        startActivity(intent);
    }
}



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