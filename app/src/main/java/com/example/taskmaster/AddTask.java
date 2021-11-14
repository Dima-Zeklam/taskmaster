package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


         Button SubmitButton = findViewById(R.id.Submit);
         SubmitButton.setOnClickListener(new View.OnClickListener() {
             int countTasks = 0 ;
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),"submitted!",Toast.LENGTH_LONG).show();
                        countTasks++;
                        TextView tasksNum = findViewById(R.id.taskNum);

                        tasksNum.setText(String.valueOf(countTasks));


                   }
                });
    }
}

































