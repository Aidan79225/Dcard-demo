package com.aidan.dcard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aidan.dcard.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {
    private val vb by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
    }
}