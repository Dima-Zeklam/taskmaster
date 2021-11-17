package com.example.taskmaster;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDAO {
    @Query("SELECT * FROM taskModel")
    List<TaskModel> getAll();

//    @Query("SELECT * FROM taskModel WHERE id IN (id)")
//    TaskModel TaskById(Long id);

    @Insert
    void insertAll(TaskModel... tasks);


}
