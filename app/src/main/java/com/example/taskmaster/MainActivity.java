package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnTasksListener{

    AtomicReference<List<Task>> tasksList = new AtomicReference<>(new ArrayList<>());
    private RecyclerView recyclerView;
    private Handler handler;


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

//
//        TaskAdapter taskAdapter = new TaskAdapter(Tasks, this);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.canScrollVertically();
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(taskAdapter);
        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }
    }//OnCreate

    ArrayList<Task> tasksArray =new  ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userName = sharedPreferences.getString("userName","no username yet!");
        TextView userNameText = findViewById(R.id.userName);

        userNameText.setText(userName+ "'s Tasks");


        handler = new Handler(Looper.getMainLooper(),
                new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message message) {
                        recyclerView.getAdapter().notifyDataSetChanged();
                        return false;
                    }
                });

        Amplify.API.query(
                ModelQuery.list(Task.class),
                response -> {
                    for (Task task : response.getData()) {
                        Log.i("MyAmplifyApp", task.getTitle());
                        tasksArray.add(task);

                    }
                    tasksList.set(tasksArray);
                    System.out.println(tasksList);

                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
        setTaskAdapter();
        System.out.println("tasks:::::::::::: "+tasksArray);
        System.out.println("tasks:::::::::::: "+tasksArray.size());
    }

    public void setTaskAdapter(){
        recyclerView = findViewById(R.id.allTasksRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskAdapter(tasksArray, this));
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

