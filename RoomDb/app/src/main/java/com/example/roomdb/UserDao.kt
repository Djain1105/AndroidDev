package com.example.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)

    @Insert
    fun insertAll(users: List<User>)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM User")
    fun getAllUser(): LiveData<List<User>>

    @Query("SELECT * FROM User WHERE age >= :minAge")
    fun getUserWithAge(minAge: Int): List<User>
}