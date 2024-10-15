package com.example.lessonscalendar

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(vararg users: UserEntity)

    @Update
    fun updateUser(vararg users: UserEntity)

    @Delete
    fun delete(user: UserEntity)

    @Query("SELECT * FROM user_data_table")
    fun getAll(): Flow<List<UserEntity>>

    @Query("SELECT * FROM user_data_table")
    fun deleteAll(): Flow<List<UserEntity>>
}