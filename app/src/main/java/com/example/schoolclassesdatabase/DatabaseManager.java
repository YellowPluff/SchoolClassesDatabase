package com.example.schoolclassesdatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {

    public DatabaseManager(Context context) {
        super(context, "ClassesDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCommand = "create table ClassesTable(";
        sqlCommand += "id integer primary key autoincrement, name text, professor text, description text)";
        db.execSQL(sqlCommand);
    }

    public void insert(String courseName, String professorName, String courseDescription) {
        SQLiteDatabase db = getWritableDatabase();
        String sqlCommand = "insert into ClassesTable values(";
        sqlCommand += "null, '" + courseName + "', '" + professorName + "', '" + courseDescription + "')";
        db.execSQL(sqlCommand);
        db.close();
    }

    public ArrayList<String> getAllClasses() {
        ArrayList<String> classNames = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();
        String sqlCommand = "select * from ClassesTable";
        Cursor cursor = db.rawQuery(sqlCommand, null);
        while(cursor.moveToNext()) {
            String className = cursor.getString(1);
            classNames.add(className);
        }
        db.close();
        return classNames;
    }

    public ArrayList<String> getClassesByProfessor(String professorName) {
        ArrayList<String> classNames = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();
        String sqlCommand = "select * from ClassesTable";
        Cursor cursor = db.rawQuery(sqlCommand, null);
        while(cursor.moveToNext()) {
            if(cursor.getString(2).equals(professorName)) {
                String className = cursor.getString(1);
                classNames.add(className);
            }
        }
        db.close();
        return classNames;
    }

    public String[] get(String className) {
        SQLiteDatabase db = getReadableDatabase();
        String sqlCommand = "select * from ClassesTable where name = '" + className + "'";
        Cursor cursor = db.rawQuery(sqlCommand, null);
        String[] classDetails = new String[3];
        if(cursor.moveToFirst()) {
            String professorName = cursor.getString(2);
            String courseDescription = cursor.getString(3);
            classDetails[0] = className;
            classDetails[1] = professorName;
            classDetails[2] = courseDescription;
        }
        db.close();
        return classDetails;
    }

    public void updateByClassName(String className, String professorName, String classDescription) {
        SQLiteDatabase db = getWritableDatabase();
        String sqlCommand = "update ClassesTable set professor = '" + professorName + "' where name = '" + className + "'";
        db.execSQL(sqlCommand);
        sqlCommand = "update ClassesTable set description = '" + classDescription + "' where name = '" + className + "'";
        db.execSQL(sqlCommand);
        db.close();
    }

    public void delete(String className) {
        SQLiteDatabase db = getWritableDatabase();
        String sqlCommand = "delete from ClassesTable where name = '" + className + "'";
        db.execSQL(sqlCommand);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
