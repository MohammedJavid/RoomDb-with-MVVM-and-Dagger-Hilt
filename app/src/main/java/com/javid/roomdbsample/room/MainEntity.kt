package com.javid.roomdbsample.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "main_table", indices = [Index(value = ["emp_name"],unique = true)])
data class MainEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "emp_id")
    var empId: Int,
    @ColumnInfo(name = "emp_name")
    var empName: String,
    @ColumnInfo(name = "emp_position")
    var empPosition: String

)