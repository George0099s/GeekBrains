package com.example.geekbrains.lesson2

import MainBroadcastReceiver
import MainService
import ThreadsFragment
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import com.example.geekbrains.R
import com.example.geekbrains.databinding.ActivityLessonTwoBinding
import com.example.geekbrains.lesson3.Lesson3Fragment

class LessonTwoActivity : AppCompatActivity() {

    private val receiver = MainBroadcastReceiver()
    private lateinit var binding: ActivityLessonTwoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        registerReceiver(receiver, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
        startService(Intent(this, MainService::class.java))
        supportFragmentManager.beginTransaction().add(R.id.cont, Lesson3Fragment()).commit()
    }
}