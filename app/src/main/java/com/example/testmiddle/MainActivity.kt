package com.example.testmiddle

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.testmiddle.databinding.ActivityImageMoverBinding
import com.example.testmiddle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        title = "중간고사"
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        var major = savedInstanceState.getString("major")
        var studentNum = savedInstanceState.getString("studentNum")
        var name = savedInstanceState.getString("name")
        binding.major.text = major
        binding.studentNum.text = studentNum
        binding.name.text = name
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("major", binding.major.text.toString())
        outState.putString("studentNum", binding.studentNum.text.toString())
        outState.putString("name", binding.name.text.toString())
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var updateMajor = data?.getStringExtra("updateMajor")
        var updateStudentNum = data?.getStringExtra("updateStudentNum")
        var updateName = data?.getStringExtra("updateName")

        binding.major.text = "학과: $updateMajor"
        binding.studentNum.text = "학번: $updateStudentNum"
        binding.name.text = "이름: $updateName"

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menuu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu1 -> {
                startActivity(Intent(this, ImageMoverActivity::class.java))
            }
            R.id.menu2 -> {
                startActivity(Intent(this, AnimationActivity::class.java))
            }
            R.id.menu3 -> {
                startActivityForResult(Intent(this,ActionActivity::class.java),100)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}