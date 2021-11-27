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
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnTasksListener{

    AtomicReference<List<Task>> tasksList = new AtomicReference<>(new ArrayList<>());
    private RecyclerView recyclerView;
    private Handler handler;
    HashSet<Task> team = new HashSet<>();
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
    }//OnCreate

    ArrayList<Task> tasksArray = new  ArrayList<>();

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
                    for (Task task : response.getData()) {
                        Log.i("MyAmplifyApp", task.getTitle());
                        tasksArray.add(task);
// Task {id=f623a100-2d6f-457b-a17e-7ae7a5c0a6ca, title=Task six, body=Write a story, state=new, team=Team {id=39f7067e-bc49-4faa-829c-ab3967e1262c, name=team 1 , createdAt=Temporal.DateTime{offsetDateTime='2021-11-27T10:30:36.565Z'}, updatedAt=Temporal.DateTime{offsetDateTime='2021-11-27T10:30:36.565Z'}},
// createdAt=Temporal.DateTime{offsetDateTime='2021-11-27T14:08:44.596Z'}, updatedAt=Temporal.DateTime{offsetDateTime='2021-11-27T14:08:44.596Z'}}, Task {id=964f1c41-ed04-49cd-afb2-d5be13f3a0f2, title=Eight Task, body=Do work, state=inproogress, team=Team {id=4d28a324-b6cf-448c-816a-750f34db6a82, name=team 2, createdAt=Temporal.DateTime{offsetDateTime='2021-11-27T10:29:47.041Z'}, updatedAt=Temporal.DateTime{offsetDateTime='2021-11-27T10:29:47.041Z'}}, createdAt=Temporal.DateTime{offsetDateTime='2021-11-27T15:10:34.139Z'}, updatedAt=Temporal.DateTime{offsetDateTime='2021-11-27T15:10:34.139Z'}}, Task {id=e952c657-d1f5-4d06-a9cb-4beb552055e1, title=My Task, body=Do something, state=state, team=Team {id=4d28a324-b6cf-448c-816a-750f34db6a82, name=team 2, createdAt=Temporal.DateTime{offsetDateTime='2021-11-27T10:29:47.041Z'}, updatedAt=Temporal.DateTime{offsetDateTime='2021-11-27T10:29:47.041Z'}}, createdAt=Temporal.DateTime{offsetDateTime='2021-11-27T14:32:54.903Z'}, updatedAt=Temporal.DateTime{offsetDateTime='2021-11-27T14:32:54.903Z'}}, Task {id=2b5fdac7-e771-48c1-bbfa-12300ba0fa9d, title=Task seven, body=Do Workout, state=completed, team=Team {id=d0d99073-c3f7-4dc9-9f1d-a0793ebfeb2d, name=team 3, createdAt=Temporal.DateTime{offsetDateTime='2021-11-27T10:30:43.555Z'}, updatedAt=Temporal.DateTime{offsetDateTime='2021-11-27T10:30:43.555Z'}}, createdAt=Temporal.DateTime{offsetDateTime='2021-11-27T14:27:52.038Z'}, updatedAt=Temporal.DateTime{offsetDateTime='2021-11-27T14:27:52.038Z'}}, Task {id=1b1f0ae4-6aa7-4199-9646-c1e6d5b31de8, title=Task two, body=Do assignment, state=new, team=null, createdAt=Temporal.DateTime{offsetDateTime='2021-11-24T18:44:49.912Z'}, updatedAt=Temporal.DateTime{offsetDateTime='2021-11-24T18:44:49.912Z'}}, Task {id=8a2d2505-9f23-473e-9d20-fc8b1993e695, title= Task Four, body=Taekwondo class, state=completed, team=null, createdAt=Temporal.DateTime{offsetDateTime='2021-11-24T21:18:02.744Z'}, updatedAt=Temporal.DateTime{offsetDateTime='2021-11-24T21:18:02.744Z'}}, Task {id=bd6e0c48-2a54-4193-bea9-21adf0fd4e43, title=TaskOne, body=Workout, state=new, team=null, createdAt=Temporal.DateTime{offsetDateTime='2021-11-24T18:39:16.523Z'}, updatedAt=Temporal.DateTime{offsetDateTime='2021-11-24T18:39:16.523Z'}}, Task {id=dd527124-26c2-4ab0-a5b1-2dc60a4f9a3c, title=Task five, body=Read book, state=new, team=Team {id=4d28a324-b6cf-448c-816a-750f34db6a82, name=team 2, createdAt=Temporal.DateTime{offsetDateTime='2021-11-27T10:29:47.041Z'}, updatedAt=Temporal.DateTime{offsetDateTime='2021-11-27T10:29:47.041Z'}}, createdAt=Temporal.DateTime{offsetDateTime='2021-11-27T14:05:18.970Z'}, updatedAt=Temporal.DateTime{offsetDateTime='2021-11-27T14:05:18.970Z'}}, Task {id=73e97853-bff7-4095-b05b-a42ad9d1db8e, title=task three, body=ride a hours, state=inprogress, team=null, createdAt=Temporal.DateTime{offsetDateTime='2021-11-24T20:11:25.300Z'}, updatedAt=Temporal.DateTime{offsetDateTime='2021-11-24T20:11:25.300Z'}}, Task {id=f623a100-2d6f-457b-a17e-7ae7a5c0a6ca, title=Task six, body=Write a story, state=new, team=Team {id=39f7067e-bc49-4faa-829c-ab3967e1262c, name=team 1 , createdAt=Temporal.DateTime{offsetDateTime='2021-11-27T10:30:36.565Z'}, updatedAt=Temporal.DateTime{offsetDateTime='2021-11-27T10:30:36.565Z'}}, createdAt=Temporal.DateTime{offsetDateTime='2021-11-27T14:08:44.596Z'},
// updatedAt=Temporal.DateTime{offsetDateTime='2021-11-27T14:08:44.596Z'}},
                        for (int i = 1; i != 5 && i!= 6 && i != 7 && i<tasksArray.size(); i++) {

                            if ( tasksArray.get(i).getTeam().getName().equals(teamNameString) ) {
                                Log.i("IAM ADDING ", "HHHHIIIIIIIIIIIII");

                                team.add(tasksArray.get(i));
                            }
                            System.out.println("Name222222#################  " +  tasksArray.get(i).getTeam().getName());       System.out.println("Name#################  " + tasksArray.get(i).getTeam().getName().equals(teamNameString));
                        }
                    }
                    tasksList.set(tasksArray);
                    System.out.println(tasksList);

                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );


//        Amplify.API.query(
//                ModelQuery.list(Task.class),
//                response -> {
//                    ///looping through data to render it
//                    for (Task task : response.getData()) {
//
//                        ///add new data to array
//                        tasksArray.add(task);
////                        for (int i = 0; i < tasksArray.size(); i++) {
////                            Log.i("IAM ADDING ", "HHHHIIIIIIIIIIIII");
////                            if (tasksArray.get(i).getTeam().getName().equals(teamNameString)) {
////                                team.add(tasksArray.get(i));
////
////                            }
////                        }
//                    }
//                                },
//                error -> Log.e("MyAmplifyApp", "Query failure", error)
//        );

//        Amplify.API.query(
//                ModelQuery.list(Team.class),
//                response -> {
//                    for (Team item : response.getData()) {
//                        Log.i("MyAmplifyApp", item.getName());
//                        team.add(item);
//
//                    }
//                    tasksList.set(tasksArray);
//                    System.out.println(tasksList);
//
//                },
//                error -> Log.e("MyAmplifyApp", "Query failure", error)
//        );

        setTaskAdapter();
//        System.out.println("TEamssss::::::::::::"+ teams);
        System.out.println("tasks:::::::::::: "+tasksArray);
        System.out.println("tasks:::::::::::: "+tasksArray.size());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void setTaskAdapter(){
        recyclerView = findViewById(R.id.allTasksRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskAdapter(team, this));
//        team.clear();
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

