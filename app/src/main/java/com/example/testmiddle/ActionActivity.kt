package com.example.testmiddle

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.testmiddle.databinding.ActivityActionBinding

class ActionActivity : AppCompatActivity() {
    lateinit var binding: ActivityActionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityActionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        title = "개인 정보 수정"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.resultBtn.setOnClickListener {
            binding.resultBtn.setBackgroundColor(Color.RED)
            var updateMajor = binding.editMajor.text.toString()
            var updateStudentNum = binding.editStudentNum.text.toString()
            var updateName = binding.editName.text.toString()
            var intent = Intent()
            intent.putExtra("updateMajor",updateMajor)
            intent.putExtra("updateStudentNum",updateStudentNum)
            intent.putExtra("updateName",updateName)
            setResult(RESULT_OK,intent)
            finish()
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