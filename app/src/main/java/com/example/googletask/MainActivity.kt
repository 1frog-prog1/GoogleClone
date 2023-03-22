package com.example.googletask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(),
    ListFragment.Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val curFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (curFragment == null) {
            val fragment = ListFragment.newInstance()

            Log.d(TAG, "ListFragment there")

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()

            Log.d(TAG, "supportFragmentManager")
        }

    }

    override fun onTaskSelected(taskId: UUID) {
        val fragment = TaskFragment.newInstance(taskId)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}