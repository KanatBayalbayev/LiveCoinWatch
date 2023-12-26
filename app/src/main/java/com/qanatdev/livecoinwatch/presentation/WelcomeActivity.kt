package com.qanatdev.livecoinwatch.presentation

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.qanatdev.livecoinwatch.R
import com.qanatdev.livecoinwatch.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val prefs: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        binding.buttonStart.setOnClickListener {
            val editor: SharedPreferences.Editor = prefs.edit()
            editor.putBoolean(KEY_FIRST_RUN, false)
            editor.apply()
            finish()
        }
    }


    companion object{
        private val PREFS_NAME = "MyPrefsFile"
        private val KEY_FIRST_RUN = "firstRun"
    }
}