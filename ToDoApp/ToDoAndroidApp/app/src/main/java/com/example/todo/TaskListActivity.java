package com.example.todo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;

public class TaskListActivity extends AppCompatActivity {
    private List<Task> taskList;
    private TaskAdapter taskAdapter;
    private EditText taskInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        taskInput = findViewById(R.id.taskInput);
        Button addTaskBtn = findViewById(R.id.addTaskBtn);
        RecyclerView recyclerView = findViewById(R.id.taskListRecyclerView);

        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(taskList);

        recyclerView.setAdapter(taskAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskText = taskInput.getText().toString().trim();
                if (!taskText.isEmpty()) {
                    Task newTask = new Task(taskText);
                    taskList.add(newTask);
                    taskAdapter.notifyDataSetChanged();
                    taskInput.setText("");
                }
            }
        });
    }
}
