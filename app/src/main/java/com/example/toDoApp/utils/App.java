package com.example.toDoApp.utils;

import android.app.Application;

import androidx.room.Room;

import com.example.toDoApp.room.Database;
import com.example.toDoApp.ui.create.TaskAdapter;

public class App extends Application {
    public static App instance;
    public static Database dataBase = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        getInstance();
    }

    public static Database getInstance(){
        if (dataBase == null){
            dataBase = Room.databaseBuilder(instance.getApplicationContext(),Database.class, "todo")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return dataBase;
    }

}
