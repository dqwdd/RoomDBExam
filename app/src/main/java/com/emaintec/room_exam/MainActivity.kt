package com.emaintec.room_exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import com.emaintec.room_exam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries()
            .build()

        binding.textResult.text = db.todoDao().getAll().toString()

        binding.buttonAdd.setOnClickListener {
            db.todoDao().insert(Todo(binding.editTodo.text.toString()))
            binding.textResult.text = db.todoDao().getAll().toString()
        }

    }
}