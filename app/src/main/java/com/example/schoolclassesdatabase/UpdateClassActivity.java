package com.example.schoolclassesdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateClassActivity extends AppCompatActivity {

    private TextView classNameDisplay;
    private EditText classProfessorInput;
    private EditText classDescriptionInput;

    private String[] classDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_class);
        classNameDisplay = findViewById(R.id.editCourseClassNameTextView);
        classProfessorInput = findViewById(R.id.editCourseProfessorNameEditText);
        classDescriptionInput = findViewById(R.id.editCourseClassDescriptionEditText);

        Intent i = getIntent();
        String className = i.getStringExtra("CLASSNAME");
        DatabaseManager dbm = new DatabaseManager(this);
        classDetails = dbm.get(className);
        classNameDisplay.setText(classDetails[0]);
        classProfessorInput.setText(classDetails[1]);
        classDescriptionInput.setText(classDetails[2]);
    }

    public void saveClassChangesButtonClicked(View view) {
        String className = classNameDisplay.getText().toString();
        String classProfessor = classProfessorInput.getText().toString();
        String classDescription = classDescriptionInput.getText().toString();
        DatabaseManager dbm = new DatabaseManager(this);
        dbm.updateByClassName(className, classProfessor, classDescription);
        finish();
    }
}
