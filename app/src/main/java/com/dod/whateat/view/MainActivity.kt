package com.dod.whateat.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dod.whateat.data.FoodData
import com.dod.whateat.databinding.ActivityMainBinding
import com.dod.whateat.viewmodel.RandomFoodViewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this,
        RandomFoodViewModel.RandomFoodFactory(application))[RandomFoodViewModel::class.java] }

    private lateinit var foodList: MutableList<FoodData>
    private val handler = Handler(Looper.getMainLooper())

    private var changeCtn: Int = 0
    private val lastChangeCnt: Int = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setObserver()
    }

    private fun setView(){
        val range = (1..foodList.size)
        val runnable = Runnable {
            textChange(foodList[range.random() - 1].name)
            binding.btn.performClick()
        }

        binding.btn.setOnClickListener {
            handler.removeCallbacks(runnable)
            Log.e("change count", changeCtn.toString())
            if(changeCtn < lastChangeCnt){
                handler.postDelayed(runnable, 100L)
            }else {
                changeCtn = 0
            }
        }

        binding.btnGo.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun textChange(text: String){
        binding.text.text = text
        changeCtn++
    }

    private fun setObserver(){
        viewModel.foodList.observe(this) {
            foodList = it
            setView()
            Toast.makeText(this, "can start !", Toast.LENGTH_SHORT).show()
        }
    }
}