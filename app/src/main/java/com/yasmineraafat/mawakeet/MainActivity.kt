package com.yasmineraafat.mawakeet

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.yasmineraafat.mawakeet.databinding.ActivityMainBinding
import com.yasmineraafat.mawakeet.viewmodels.MawakeetViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.Disposable
import java.util.Calendar

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var binding:ActivityMainBinding
    val viewModel: MawakeetViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mawakeetViewModel = viewModel

        binding.mawakeetViewModel?.date?.observe(this) {

            Log.e("date",it.toString())


        }
        binding.mawakeetViewModel?.timings?.observe(this) {

            Log.e("timing",it.toString())

        }
        binding.mawakeetViewModel?.errorMessage?.observe(this) {
//            binding.progressBar.visibility = View.GONE
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            Log.e("Error",it)
        }


        binding.mawakeetViewModel?.getDateByTimestamp()
    }



}

