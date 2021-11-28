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
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnTasksListener{

    AtomicReference<List<Task>> tasksList = new AtomicReference<>(new ArrayList<>());
    private RecyclerView recyclerView;
    private Handler handler;
    ArrayList<Task> team = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configure();

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

    }//OnCreate

    ArrayList<Task> tasksArray = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userName = sharedPreferences.getString("userName","no username yet!");
        TextView userNameText = findViewById(R.id.userName);
        String teamNameString = sharedPreferences.getString("teamName", "team name");
        TextView teamNameView = findViewById(R.id.teamNameId);
        teamNameView.setText(teamNameString);

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
                    tasksArray.clear();
                            team.clear();
                    for (Task task : response.getData()) {
                        System.out.println("size  " +  tasksArray.size());
                        Log.i("MyAmplifyApp", task.getTitle());
                        tasksArray.add(task);
                    }

                    // Index 5 & 6 & 7 & 10 & 11 & 12 skipped because they are null Team Object at the database (from Previous lab )

                    for (int i = 1;  i < 5 ; i++) {
//                        System.out.println("Name222222#################  " +  tasksArray.get(i).getTeam().getName());
//                        System.out.println("Name#################  " + tasksArray.get(i).getTeam().getName().equals(teamNameString));
                        if (tasksArray.get(i).getTeam().getName().equals(teamNameString)) {
//                            Log.i("ADDING ", "ADDDDD");
                            team.add(tasksArray.get(i));
                        }
//                            }
//                        System.out.println("Name222222#################  " +  tasksArray.get(i).getTeam().getName());
//                        System.out.println("Name#################  " + tasksArray.get(i).getTeam().getName().equals(teamNameString));
                    }
                    for (int i = 8;  i < 10 ; i++) {
                        if (tasksArray.get(i).getTeam().getName().equals(teamNameString)) {
                            team.add(tasksArray.get(i));
                        }

                    }
                    for (int i = 13;  i < tasksArray.size() ; i++) {
                        if (tasksArray.get(i).getTeam().getName().equals(teamNameString)) {
                            team.add(tasksArray.get(i));
                        }

                    }

                    tasksList.set(tasksArray);
                    System.out.println(tasksList);
                    Log.i("MyAmplifyApp", "outside the loop");
                    handler.sendEmptyMessage(1);

                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        setTaskAdapter();
//                team.clear();
        System.out.println("TEamssss::::::::::::"+ team);
        System.out.println("tasks:::::::::::: "+tasksArray);
        System.out.println("tasks:::::::::::: "+tasksArray.size());
    }

    public void setTaskAdapter(){
        recyclerView = findViewById(R.id.allTasksRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskAdapter(team, this));

    }

    @Override
    public void onTaskClick(int position, Task task) {
        Intent intent = new Intent(this, TaskDetail.class);
        intent.putExtra("title",task.getTitle());
        intent.putExtra("body",task.getBody());
        intent.putExtra("state",task.getState());
        startActivity(intent);
    }

    private void configure() {
        try {
//        Log.i("MyAmplifyApp", "Initialized Amplify");
//        Team team = Team.builder()
//                .name("team 1 ")
//                .build();
//
//        Amplify.API.mutate(
//                ModelMutation.create(team),
//                response -> Log.i("MyAmplifyApp", "Added Task with id: " + response.getData().getId()),
//                error -> Log.e("MyAmplifyApp", "Create failed", error)
//        );
//
//        ///second team
//
//        Team teamTow = Team.builder()
//                .name("team 2")
//                .build();
//
//        Amplify.API.mutate(
//                ModelMutation.create(teamTow),
//                response -> Log.i("MyAmplifyApp", "Added Task with id: " + response.getData().getId()),
//                error -> Log.e("MyAmplifyApp", "Create failed", error)
//        );
//
//        ////third team hard coby
//
//        Team teamThree = Team.builder()
//                .name("team 3")
//                .build();
//
//        Amplify.API.mutate(
//                ModelMutation.create(teamThree),
//                response -> Log.i("MyAmplifyApp", "Added Task with id: " + response.getData().getId()),
//                error -> Log.e("MyAmplifyApp", "Create failed", error)
//        );

            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }
    }
}

