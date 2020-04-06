package com.example.schoolclassesdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchByProfessorActivity extends AppCompatActivity {

    private LinearLayout layout;
    private EditText professorNameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_professor);
        layout = findViewById(R.id.professorClassesLayout);
        professorNameInput = findViewById(R.id.professorNameInputForClassSearchEditText);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        searchAllClassesByProfessor(professorNameInput.getText().toString());
    }

    public void searchProfessorsClassesClicked(View view) {
        searchAllClassesByProfessor(professorNameInput.getText().toString());
    }

    private void searchAllClassesByProfessor(String professorName) {
        layout.removeAllViews();
        DatabaseManager dbm = new DatabaseManager(this);
        ArrayList<String> allClassesByProfessor = dbm.getClassesByProfessor(professorName);
        for(String className : allClassesByProfessor) {
            TextView temp = makeTextView(className);
            temp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), ViewClassInformationActivity.class);
                    i.putExtra("CLASSNAME", ((TextView) v).getText().toString());
                    startActivity(i);
                }
            });
            layout.addView(temp);
        }
    }

    private TextView makeTextView(String className) {
        TextView tempView = new TextView(this);
        tempView.setText(className);
        tempView.setPadding(10, 10, 10, 10);
        tempView.setTextSize(26);
        return tempView;
    }
}
