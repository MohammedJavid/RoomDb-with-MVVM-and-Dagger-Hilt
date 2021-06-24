package com.javid.roomdbsample.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.javid.roomdbsample.room.MainDao
import com.javid.roomdbsample.room.MainEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepository @Inject constructor(private val mainDao: MainDao) {

    suspend fun insert(mainEntity: MainEntity): Long {
        val temp = mainDao.insert(mainEntity)
        Log.d("RoomRepo", temp.toString())
        return temp
    }

    suspend fun update(mainEntity: MainEntity) {
       val temp =  mainDao.update(mainEntity)
        Log.d("RoomRepoUpdate", temp.toString())
    }

    suspend fun delete(mainEntity: MainEntity) {
        mainDao.delete(mainEntity)
    }

    suspend fun deleteById(empId: Int) {
        mainDao.deleteById(empId)
    }

    suspend fun deleteAll() {
        mainDao.deleteAll()
    }

    fun getAllEmployee():LiveData<List<MainEntity>> {
        return mainDao.getAllEmployee()
    }

}