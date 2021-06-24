package com.javid.roomdbsample

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javid.roomdbsample.repository.MainRepository
import com.javid.roomdbsample.room.MainEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(private val mainRepository: MainRepository): ViewModel() {

    private var _dataStatus: MutableLiveData<Long> = MutableLiveData()
    val dataStatus:LiveData<Long> = _dataStatus
    val status: LiveData<List<MainEntity>> = mainRepository.getAllEmployee()



    fun insert(mainEntity: MainEntity) {
        viewModelScope.launch {
            _dataStatus.value = mainRepository.insert(mainEntity)
        }
    }

    fun update(mainEntity: MainEntity) {
        viewModelScope.launch {
            mainRepository.update(mainEntity)
        }
    }

    fun delete(mainEntity: MainEntity) {
        viewModelScope.launch {
            mainRepository.delete(mainEntity)
        }
    }

    fun deleteById(empId: Int) {
        viewModelScope.launch {
            mainRepository.deleteById(empId)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            mainRepository.deleteAll()
        }
    }
}