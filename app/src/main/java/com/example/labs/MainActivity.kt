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
    private lateinit var binding: ActivityMainBinding
    private lateinit var emailfld: EditText
    private lateinit var passfld: EditText
    private lateinit var btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        emailfld = binding.editTextTextEmailAddress
        passfld = binding.editTextTextPassword
        btn = binding.button
        setContentView(binding.root)
    }

    fun btnClick(view: View?) {
        val logins = resources.getStringArray(R.array.data)
        if (emailfld.text.toString() == logins[0] && passfld.text.toString() == logins[1])
            startActivity(Intent(this, MainActivity2::class.java))
        else
            btn.setBackgroundColor(Color.RED)
    }
}
