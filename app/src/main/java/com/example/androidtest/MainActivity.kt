package com.example.androidtest

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private var counter = 0
    private lateinit var textViewCounter: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        // The Toolbar defined in the layout has the id "my_toolbar".
        setSupportActionBar(findViewById(R.id.my_toolbar))

        textViewCounter = findViewById(R.id.textViewCounter)
        val UpButton = findViewById<Button>(R.id.buttonUp)
        val downButton = findViewById<Button>(R.id.buttonDown)

        // Učitavanje spremljene vrijednosti iz SharedPreferences
        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        counter = sharedPreferences.getInt("COUNTER_VALUE", 0)
        textViewCounter.text = counter.toString()

        UpButton.setOnClickListener {
            counter++
            if(counter == 10){
                counter = 0
                val intent = Intent(this, SuccessActivity::class.java).apply{
                    putExtra("name", findViewById<TextView>(R.id.plainTextName).text.toString())
                }
                startActivity(intent)
            }
            textViewCounter.text = counter.toString()
        }

        downButton.setOnClickListener {
            if (counter > 0) {
                counter--
                textViewCounter.text = counter.toString()
            }
        }
    }

    // Spremanje trenutnog brojača prilikom promjene orijentacije
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("COUNTER_VALUE", counter)
    }

    // Vraćanje vrijednosti brojača nakon promjene orijentacije
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        counter = savedInstanceState.getInt("COUNTER_VALUE", 0)
        textViewCounter.text = counter.toString()
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(applicationContext, "onStart", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onStart")
    }
    override fun onResume() {
        super.onResume()
        Toast.makeText(applicationContext, "onResume", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onResume")
    }
    override fun onPause() {
        super.onPause()
        Toast.makeText(applicationContext, "onPause", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onPause")
    }
    override fun onStop() {
        super.onStop()
        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putInt("COUNTER_VALUE", counter)
            apply()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext, "onDestroy", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onDestroy")
    }
    override fun onRestart() {
        super.onRestart()
        Toast.makeText(applicationContext, "onRestart", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onRestart")
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.restore_counter -> {
                counter = 0
                textViewCounter.text = counter.toString()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    class MyActivity : AppCompatActivity() {
        // ...
    }

}