package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                        String getTitle = title.getText().toString();
                        EditText body = findViewById(R.id.editBody);
                        String getBody = body.getText().toString();
                        EditText state = findViewById(R.id.editState);
                        String getState = state.getText().toString();
                        Toast.makeText(getApplicationContext(),"submitted!",Toast.LENGTH_LONG).show();
                        countTasks++;
                        TextView tasksNum = findViewById(R.id.taskNum);
 TaskModel task = new TaskModel(getTitle,getBody,getState);
                        tasksNum.setText(String.valueOf(countTasks));
AppDatabase appDb = AppDatabase.getInstance(getApplicationContext());
appDb.taskDao().insertAll(task);

                   }
                });
    }
}

































