package com.wdretzer.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var text_number = findViewById<TextView>(R.id.number)
        var number_cronometro = findViewById<TextView>(R.id.number2)
        var input_number = findViewById<EditText>(R.id.edit_text)
        var btn = findViewById<Button>(R.id.btn_add)
        var btn_start = findViewById<Button>(R.id.btn_start)
        var btn_stop = findViewById<Button>(R.id.btn_stop)

        var viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        text_number.text = viewModel.number.toString()

        btn.setOnClickListener {
            viewModel.addNumber()
            text_number.text = viewModel.number.toString()
        }

        var viewModel2 = ViewModelProvider(this).get(MainActivityViewModel2::class.java)

        viewModel2.seconds().observe(this, Observer {
            number_cronometro.text = it.toString()
        })
        viewModel2.finished.observe(this, Observer {
            if (it) {
                Toast.makeText(this, "Finished!", Toast.LENGTH_LONG).show()
            }
        })

        btn_start.setOnClickListener {

            if (input_number.text.isEmpty() || input_number.text.length < 4) {

                Toast.makeText(this, "Invalid Number!", Toast.LENGTH_LONG).show()

            } else {

                viewModel2.timerValue.value = input_number.text.toString().toLong()
                viewModel2.startTimer()
            }
        }

        btn_stop.setOnClickListener {
            viewModel2.stopTimmer()
        }
    }
}