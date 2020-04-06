package com.example.schoolclassesdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ViewClassInformationActivity extends AppCompatActivity {

    private TextView classNameView;
    private TextView professorNameView;
    private TextView classDescriptionView;

    private String[] classDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_class_information);

        classNameView = findViewById(R.id.classNameTextView);
        professorNameView = findViewById(R.id.professorNameTextView);
        classDescriptionView = findViewById(R.id.classDescriptionTextView);

        DatabaseManager dbm = new DatabaseManager(this);
        Intent i = getIntent();
        String name = i.getStringExtra("CLASSNAME");
        classDetails = dbm.get(name);

        classNameView.setText("Course: " + classDetails[0]);
        professorNameView.setText("Professor: " + classDetails[1]);
        classDescriptionView.setText("Description:\n" + classDetails[2]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_class_manipulation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_edit) {
            Intent i = new Intent(this, UpdateClassActivity.class);
            i.putExtra("CLASSNAME", classDetails[0]);
            startActivity(i);
            finish();
            return true;
        } else if(id == R.id.action_delete)
        {
            DatabaseManager dbm = new DatabaseManager(this);
            dbm.delete(classDetails[0]);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
