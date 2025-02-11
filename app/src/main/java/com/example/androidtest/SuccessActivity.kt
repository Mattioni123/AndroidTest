package com.example.androidtest

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SuccessActivity : AppCompatActivity(){
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        textView = findViewById(R.id.textView)

        val name = intent.getStringExtra("name")
        textView.text = "$name uspješno ste došli do 10 koraka."
    }
}