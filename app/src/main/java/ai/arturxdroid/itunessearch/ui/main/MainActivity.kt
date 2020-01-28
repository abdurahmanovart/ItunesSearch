package ai.arturxdroid.itunessearch.ui.main

import ai.arturxdroid.itunessearch.R
import ai.arturxdroid.itunessearch.databinding.MainActivityBinding
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.activity_main)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel



    }
}
