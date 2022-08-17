package com.dod.whateat.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.dod.whateat.R
import com.dod.whateat.data.FoodData
import com.dod.whateat.databinding.ActivityMainBinding
import com.dod.whateat.viewmodel.FoodViewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this,
        FoodViewModel.FoodFactory(application))[FoodViewModel::class.java] }

    private val handler = Handler(Looper.getMainLooper())

    private var changeCtn: Int = 0
    private val lastChangeCnt by lazy { resources.getInteger(R.integer.change_count) }
    private var changeSpeed: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setObserver()
        viewModel.randomSelect()
    }

    private fun setView(foodList: MutableList<FoodData>){
        changeSpeed = resources.getInteger(R.integer.change_speed).toLong()

        val range = (1..foodList.size)
        val runnable = Runnable {
            textChange(foodList[range.random() - 1].name)
            binding.btn.performClick()
        }

        binding.btn.setOnClickListener {
            handler.removeCallbacks(runnable)
            if(changeCtn < lastChangeCnt){
                handler.postDelayed(runnable, changeSpeed)
                changeSpeed /= 2
            }else {
                changeSpeed = resources.getInteger(R.integer.change_speed).toLong()
                changeCtn = 0
            }
        }

        binding.btnGo.setOnClickListener {
            val intent = Intent(this, CategoryListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun textChange(text: String){
        binding.text.text = text
        changeCtn++
    }

    private fun setObserver(){
        viewModel.foodList.observe(this) {
            setView(it)
        }
    }
}