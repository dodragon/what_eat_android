package com.dod.whateat.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.dod.whateat.databinding.ActivitySplashBinding
import com.dod.whateat.service.FoodService
import com.dod.whateat.viewmodel.FoodViewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this,
        FoodViewModel.FoodFactory(FoodService()))[FoodViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setObserver()
        requestFoodCount()
    }

    private fun requestFoodCount(){
        lifecycleScope.launchWhenStarted {
            viewModel.foodCount()
        }
    }

    private fun saveFoodCount(count: Int){
        val spf = getSharedPreferences("food", MODE_PRIVATE)
        spf.edit().putInt("food_count", count).apply()
        goMain()
    }

    private fun goMain(){
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, 1500)
    }

    private fun setObserver(){
        viewModel.foodCnt.observe(this) {
            saveFoodCount(it)
        }
    }
}