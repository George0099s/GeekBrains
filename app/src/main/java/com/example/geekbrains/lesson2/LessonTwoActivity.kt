package com.example.geekbrains.lesson2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.geekbrains.R
import com.example.geekbrains.databinding.ActivityLessonTwoBinding

class LessonTwoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLessonTwoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().add(R.id.cont, Lesson2()).commit()
    }
}