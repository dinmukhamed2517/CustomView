package kz.just_code.customviewweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kz.just_code.customviewweather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}