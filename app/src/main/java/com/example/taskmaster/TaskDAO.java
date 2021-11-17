package com.example.taskmaster;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDAO {
    @Query("SELECT * FROM taskModel")
    List<TaskModel> getAll();


//    @Query("Select * FROM taskModel WHERE id = :id")
//    TaskModel getTaskById(long id);
    @Insert
    void insertAll(TaskModel... tasks);


}
