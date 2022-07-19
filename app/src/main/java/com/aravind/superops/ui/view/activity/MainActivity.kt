package com.aravind.superops.ui.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aravind.superops.R
import com.aravind.superops.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}