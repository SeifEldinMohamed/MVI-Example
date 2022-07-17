package com.seif.mviplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.seif.mviplus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddNumber.setOnClickListener {
            // send intent

        }


    }
    private fun render() {
        // render result

    }
}