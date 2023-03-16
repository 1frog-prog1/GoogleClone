package com.example.googletask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val curFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (curFragment == null) {
            val fragment = TaskFragment()

            Log.d(TAG, "TaskFragment there")

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()

            Log.d(TAG, "supportFragmentManager")
        }

    }
}