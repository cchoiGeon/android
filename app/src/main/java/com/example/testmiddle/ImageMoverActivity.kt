package com.example.testmiddle

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.testmiddle.databinding.ActivityImageMoverBinding

class ImageMoverActivity : AppCompatActivity() {
    lateinit var binding: ActivityImageMoverBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityImageMoverBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        title = "ImageMover"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnUp.setOnClickListener {
            Log.d("test123", "${binding.imageTarget.y}")
            if (binding.imageTarget.y - 100f >= 0) {
                binding.imageTarget.translationY -= 100f
            }
        }
        binding.btnDown.setOnClickListener {
            var maxY = binding.moveImageView.height - binding.imageTarget.height
            if (binding.imageTarget.y + 100f <= maxY) {
                binding.imageTarget.translationY += 100f
            }
        }

        binding.btnR.setOnClickListener {
            var maxX = binding.moveImageView.width - binding.imageTarget.width
            if (binding.imageTarget.x + 100f <= maxX) {
                binding.imageTarget.translationX += 100f
            }
        }

        binding.btnL.setOnClickListener {
            if (binding.imageTarget.x - 100f >= 0) {
                binding.imageTarget.translationX -= 100f
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}