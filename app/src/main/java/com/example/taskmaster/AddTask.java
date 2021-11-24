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
import com.amplifyframework.datastore.generated.model.Task;

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
                        String GetTitle = title.getText().toString();
                        EditText body = findViewById(R.id.editBody);
                        String GetBody = body.getText().toString();
                        EditText state = findViewById(R.id.editState);
                        String GetState = state.getText().toString();
                        Toast.makeText(getApplicationContext(),"submitted!",Toast.LENGTH_LONG).show();
                        countTasks++;
                        TextView tasksNum = findViewById(R.id.taskNum);
//                        Intent intent = new Intent();
                        TaskModel task = new TaskModel(GetTitle,GetBody,GetState);
                        tasksNum.setText(String.valueOf(countTasks));
//AppDatabase appDb = AppDatabase.getInstance(getApplicationContext());
//appDb.taskDao().insertAll(task);
                        Task item = Task.builder()
                                .title(title.getText().toString())
                                .body(body.getText().toString())
                                .state(state.getText().toString())
                                .build();
                        Amplify.DataStore.save(
                                item,
                                success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
                                error -> Log.e("Amplify", "Could not save item to DataStore", error)
                        );
                   }

                });
    }
}

































