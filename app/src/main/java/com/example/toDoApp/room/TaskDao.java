package com.example.toDoApp.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.toDoApp.ui.create.TaskModel;

import java.util.List;

@Dao
public interface  TaskDao {
    @Query("SELECT * FROM taskmodel")
    List<TaskModel> getAll();

    @Insert
    void insert (TaskModel taskModel);

    @Update
    void update (TaskModel taskModel);

    @Delete
    void delete (TaskModel taskModel);
}
