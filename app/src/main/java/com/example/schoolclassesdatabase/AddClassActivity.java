package com.example.schoolclassesdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddClassActivity extends AppCompatActivity {

    private EditText courseNameInput;
    private EditText professorNameInput;
    private EditText courseDescriptionInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        courseNameInput = findViewById(R.id.courseNameInputEditText);
        professorNameInput = findViewById(R.id.professorNameInputEditText);
        courseDescriptionInput = findViewById(R.id.courseDescriptionInputEditText);
    }

    public void submitClassButtonClicked(View view) {
        //TODO Bit broken... Can't have ' in the text
        String courseName = courseNameInput.getText().toString();
        String professorName = professorNameInput.getText().toString();
        String courseDescription = courseDescriptionInput.getText().toString();
        DatabaseManager dbm = new DatabaseManager(this);
        dbm.insert(courseName, professorName, courseDescription);
        finish();
    }
}
