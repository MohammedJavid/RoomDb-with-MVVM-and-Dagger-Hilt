package com.javid.roomdbsample.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MainDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(mainEntity: MainEntity): Long

    @Update
    suspend fun update(mainEntity: MainEntity) : Int

    @Delete
    suspend fun delete(mainEntity: MainEntity)

    @Query("DELETE FROM main_table WHERE emp_id = :empId")
    suspend fun deleteById(empId: Int)

    @Query("DELETE FROM main_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM main_table")
    fun getAllEmployee(): LiveData<List<MainEntity>>

}