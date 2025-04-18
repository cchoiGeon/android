package com.example.testmiddle

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.testmiddle.databinding.ActivityAnimationBinding

class AnimationActivity : AppCompatActivity() {
    lateinit var binding: ActivityAnimationBinding
    private var animationThread: Thread? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        var isRunning = false
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        title = "애니메이션"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnStart.setOnClickListener {
            isRunning = true
            binding.btnStart.text = "시작"
            binding.btnStart.isEnabled = false
            binding.btnStop.isEnabled = true
            binding.btnReset.isEnabled = true
            animationThread = Thread {
                while (isRunning) {
                    Thread.sleep(100)
                    var maxX = binding.moveImageView.width - binding.imageTarget.width
                    var maxY = binding.moveImageView.height - binding.imageTarget.height
                    if (binding.imageTarget.x + 100f >= maxX || binding.imageTarget.y + 100f >= maxY) {
                        binding.imageTarget.translationX = 0f
                        binding.imageTarget.translationY = 0f
                    } else {
                        binding.imageTarget.translationX += 100f
                        binding.imageTarget.translationY += 100f
                    }
                }
            }
            animationThread?.start()
        }

        binding.btnStop.setOnClickListener {
            isRunning = false
            binding.btnStart.text = "재시작"
            binding.btnStart.isEnabled = true
            binding.btnStop.isEnabled = false
            binding.btnReset.isEnabled = true
        }

        binding.btnReset.setOnClickListener {
            isRunning = false
            binding.btnStart.text = "시작"
            binding.imageTarget.translationX = 0f
            binding.imageTarget.translationY = 0f
            binding.btnStart.isEnabled = true
            binding.btnStop.isEnabled = false
            binding.btnReset.isEnabled = false
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