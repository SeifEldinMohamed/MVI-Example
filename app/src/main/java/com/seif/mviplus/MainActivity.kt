package com.seif.mviplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.seif.mviplus.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: AddNumberViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        render()
        binding.btnAddNumber.setOnClickListener {
            // send intent
            lifecycleScope.launch {
                viewModel.intentChannel.send(MainIntent.AddNumber)
            }
        }
    }

    private fun render() { // observe on this data
        // render result we need to collect data came from stateFlow coming from viewModel
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                when(it){
                    is MainViewState.Idle -> binding.txtNumber.text = getString(R.string.idle)
                    is MainViewState.Number -> binding.txtNumber.text = it.number.toString()
                    is MainViewState.Error -> binding.txtNumber.text = it.error
                }
            }
        }
    }
}