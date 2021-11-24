//package com.example.taskmaster;
//
//import androidx.room.Dao;
//import androidx.room.Insert;
//import androidx.room.Query;
//
//import com.amplifyframework.datastore.generated.model.Task;
//
//import java.util.List;
//
//@Dao
//public interface TaskDAO {
//    @Query("SELECT * FROM taskModel")
//    List<Task> getAll();
//
//
////    @Query("Select * FROM taskModel WHERE id = :id")
////    TaskModel getTaskById(long id);
//    @Insert
//    void insertAll(TaskModel... tasks);
//    @Query("SELECT * FROM taskModel WHERE id LIKE :id")
//    TaskModel findTaskById(int id);
//
//}
