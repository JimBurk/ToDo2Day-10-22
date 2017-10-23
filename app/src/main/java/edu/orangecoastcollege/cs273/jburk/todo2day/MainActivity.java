package edu.orangecoastcollege.cs273.jburk.todo2day;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Task> allTasksList = new ArrayList<>();

    // Reference to the database
    private DBHelper mDB;
    // References to the widgets needed
    private EditText mDescriptionEditText;
    private ListView mTaskListView;

    // Reference to the custom list adapter
    TaskListAdapter mTaskListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDB = new DBHelper(this);
        mDescriptionEditText = (EditText) findViewById(R.id.taskEditText);
        mTaskListView = (ListView) findViewById(R.id.taskListView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Database related "stuff"
        // 1) populate a list from the database (using DBHelper)
        allTasksList = mDB.getAllTasks();
        // 2) connect the list view with the custom List adapter
        mTaskListAdapter = new TaskListAdapter(this, R.layout.task_item, allTasksList);
        mTaskListView.setAdapter(mTaskListAdapter);
    }

    public void addTask(View v) {
        // Check to see if description empty or not
        String description = mDescriptionEditText.getText().toString();
        if (TextUtils.isEmpty(description))
            Toast.makeText(this, "Please enter a description", Toast.LENGTH_LONG).show();
        else {
            //Create the task
            Task newTask = new Task(description, false);
            // add it to database
            mDB.addTask(newTask);
            // add it to the list
            allTasksList.add(newTask);
            // notify list adapter that it's been changed
            mTaskListAdapter.notifyDataSetChanged();
            // clear out the text
            mDescriptionEditText.setText("");
        }
    }

    public void clearAllTasks(View v) {
        mDB.deleteAllTasks();

        allTasksList.clear();
        mTaskListAdapter.notifyDataSetChanged();
    }

    public void changeTaskStatus(View v) {
        CheckBox selectedCheckBox = (CheckBox) v;
        Task selectedTask = (Task) selectedCheckBox.getTag();
        // update the task
        selectedTask.setIsDone(selectedCheckBox.isChecked());
        // Update the database
        mDB.updateTask(selectedTask);
    }

}
