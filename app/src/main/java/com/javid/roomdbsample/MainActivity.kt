package com.javid.roomdbsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.javid.roomdbsample.databinding.ActivityMainBinding
import com.javid.roomdbsample.room.MainEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    lateinit var adapter : MainAdapter

    companion object{
        private var isUpdate = false
        private var id = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            btnSave.setOnClickListener {
                if (isUpdate) {
                    if (etName.text.toString().isNotEmpty() && etPosition.text.toString().isNotEmpty()) {
                        mainViewModel.update(MainEntity(id,etName.text.toString(),etPosition.text.toString()))
                        resetUi()
                    } else {
                        resetUi()
                        Toast.makeText(this@MainActivity, "Empty name or Position", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    if (etName.text.toString().isNotEmpty() && etPosition.text.toString().isNotEmpty()) {
                        mainViewModel.insert(MainEntity(0,etName.text.toString(),etPosition.text.toString()))
                        resetUi()
                    } else {
                        resetUi()
                        Toast.makeText(this@MainActivity, "Empty name or Position", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            btnDelete.setOnClickListener {
                if (isUpdate) {
                    if (etName.text.toString().isNotEmpty() && etPosition.text.toString().isNotEmpty()) {
                        mainViewModel.delete(MainEntity(id,etName.text.toString(),etPosition.text.toString()))
                        resetUi()
                    } else {
                        resetUi()
                        Toast.makeText(this@MainActivity, "Empty name or Position", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    mainViewModel.deleteAll()
                    resetUi()
                }
            }

        }

        mainViewModel.status.observeForever {
            if (it != null) {
                adapter = MainAdapter(it) {selectedItem: MainEntity -> listItemClicked(selectedItem)}
                binding.rclrView.adapter = adapter
                adapter.notifyDataSetChanged()
                for (i in it) {
                    Log.d("RoomData", "Emp-ID : ${i.empId} | Name : ${i.empName} | Position : ${i.empPosition}")
                }
            }
        }

        mainViewModel.dataStatus.observeForever {
            if (it != null) {
                if (it >=0 ) {
                    Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Data Already Exist", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun listItemClicked(mainEntity: MainEntity) {
        isUpdate = true
        id = mainEntity.empId
        binding.etName.setText(mainEntity.empName)
        binding.etPosition.setText(mainEntity.empPosition)
        binding.btnSave.text = "Update"
        binding.btnDelete.text = "Delete"
    }

   private fun resetUi() {
       isUpdate = false
       id = 0
       binding.etName.text.clear()
       binding.etPosition.text.clear()
       binding.btnSave.text = "Save"
       binding.btnDelete.text = "Delete All"
   }
}