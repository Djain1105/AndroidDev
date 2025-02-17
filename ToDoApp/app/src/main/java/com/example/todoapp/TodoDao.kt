package com.example.todoapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {

    @Insert()
    suspend fun insertTask(todo: TodoModel):Long

    @Query("SELECT * FROM TodoModel WHERE isFinished != -1")
    fun getTask(): LiveData<List<TodoModel>>

    @Query("UPDATE TodoModel SET isFinished = 1 WHERE id=:uid")
    fun finishedTask(uid:Long)

    @Query("DELETE FROM TodoModel WHERE id=:uid")
    fun deleteTask(uid:Long)

}