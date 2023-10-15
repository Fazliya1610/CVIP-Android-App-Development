package com.example.todo;

import com.example.todo.Task;
import com.example.todo.TaskAdapter;
import com.example.todo.TaskListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    private List<Task> taskList = new ArrayList<>();
    private TaskAdapter taskAdapter;
    private EditText taskInput;
    private int selectedTaskPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskInput = findViewById(R.id.taskInput);
        Button addTaskBtn = findViewById(R.id.addTaskBtn);
        Button clearTasksBtn = findViewById(R.id.clearTasksBtn);
        RecyclerView recyclerView = findViewById(R.id.taskList);

        taskAdapter = new TaskAdapter(taskList);

        recyclerView.setAdapter(taskAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskText = taskInput.getText().toString().trim();
                if (!taskText.isEmpty()) {
                    if (selectedTaskPosition == -1) {
                        Task newTask = new Task(taskText);
                        taskList.add(newTask);
                    } else {
                        Task editedTask = taskList.get(selectedTaskPosition);
                        editedTask.setTitle(taskText);
                        selectedTaskPosition = -1;
                    }
                    taskAdapter.notifyDataSetChanged();
                    taskInput.setText("");
                }
            }
        });

        clearTasksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskList.clear();
                taskAdapter.notifyDataSetChanged();
            }
        });

        taskAdapter.setOnItemClickListener(new TaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Task selectedTask = taskList.get(position);
                taskInput.setText(selectedTask.getTitle());
                selectedTaskPosition = position;
            }

            @Override
            public void onDeleteButtonClick(int position) {
                taskList.remove(position);
                taskAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Task deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
