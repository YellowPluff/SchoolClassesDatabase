package com.example.schoolclassesdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout scrollViewLayout;

    //Used for onTouchListener()
    private float startX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.scrollViewLayout = findViewById(R.id.classLayout);
        showAllClassesInDatabase();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showAllClassesInDatabase();
    }

    private void showAllClassesInDatabase() {
        //This needs to be called everytime app returns to this Activity
        scrollViewLayout.removeAllViews();
        DatabaseManager dbm = new DatabaseManager(this);
        ArrayList<String> allClasses = dbm.getAllClasses();
        for(String classObj : allClasses) {
            TextView temp = makeTextView(classObj);
            temp.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int action = event.getAction();
                    switch(action) {
                        case MotionEvent.ACTION_DOWN:
                            startX = event.getX();
                            break;
                        case MotionEvent.ACTION_UP:
                            if(event.getX() < startX) {
                                //They moved their finger to the left
                                Intent i = new Intent(getApplicationContext(), ViewClassInformationActivity.class);
                                i.putExtra("CLASSNAME", ((TextView) v).getText().toString());
                                startActivity(i);
                            }
                            break;
                    }
                    return true;
                }
            });
//            temp.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent i = new Intent(getApplicationContext(), ViewClassInformationActivity.class);
//                    i.putExtra("CLASSNAME", ((TextView) v).getText().toString());
//                    startActivity(i);
//                }
//            });
            scrollViewLayout.addView(temp);
        }
    }

    private TextView makeTextView(String className) {
        TextView tempView = new TextView(this);
        tempView.setText(className);
        tempView.setPadding(10, 10, 10, 10);
        tempView.setTextSize(26);
        return tempView;
    }

    public void addClassButtonClicked(View view) {
        Intent i = new Intent(this, AddClassActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void searchProfessorButtonClicked(View view) {
        Intent i = new Intent(this, SearchByProfessorActivity.class);
        startActivity(i);
    }
}
