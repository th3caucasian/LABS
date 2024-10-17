package com.example.labs

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.labs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var emailfld: EditText
    lateinit var passfld: EditText
    lateinit var btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        emailfld = binding.editTextTextEmailAddress
        passfld = binding.editTextTextPassword
        btn = binding.button
        setContentView(binding.root)
    }

    fun btnClick(view: View?) {
        val arr = resources.getStringArray(R.array.data)
        if (emailfld.text.toString() == arr[0] && passfld.text.toString() == arr[1])
            startActivity(Intent(this, MainActivity2::class.java))
        else
            btn.setBackgroundColor(Color.RED)
    }
}
