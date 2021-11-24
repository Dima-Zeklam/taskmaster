package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;

import java.util.Objects;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);


         Button SubmitButton = findViewById(R.id.Submit);
         SubmitButton.setOnClickListener(new View.OnClickListener() {
             int countTasks = 0 ;
                    @Override
                    public void onClick(View v) {
                        EditText title = findViewById(R.id.editTitle);
                        String Title = title.getText().toString();
                        EditText body = findViewById(R.id.editBody);
                        String Body = body.getText().toString();
                        EditText state = findViewById(R.id.editState);
                        String State = state.getText().toString();
                        Toast.makeText(getApplicationContext(),"submitted!",Toast.LENGTH_LONG).show();
                        countTasks++;
                        TextView tasksNum = findViewById(R.id.taskNum);
//                        Intent intent = new Intent();
//// TaskModel task = new TaskModel(Title,Body,State);
//                        tasksNum.setText(String.valueOf(countTasks));
//AppDatabase appDb = AppDatabase.getInstance(getApplicationContext());
//appDb.taskDao().insertAll(task);
                        Task task = Task.builder().title(Objects.requireNonNull(title.getText()).toString()).body(Objects.requireNonNull(body.getText()).toString()).state("new").build();
//
//                        Task tasksOrg = Task.builder()
//                                .title(taskTitle)
//                                .body(taskBody)
//                                .state(taskState)
//                                .build();

                        Amplify.API.mutate(
                                ModelMutation.create(task),
                                response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                                error -> Log.e("MyAmplifyApp", "Create failed", error)
                        );
                   }

                });
    }
}

































