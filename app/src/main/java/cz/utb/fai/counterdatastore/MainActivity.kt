package cz.utb.fai.counterdatastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import cz.utb.fai.counterdatastore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var counter = 0
    var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textName = binding.textName
        val editTextName = binding.editTextName

        val textCounter = binding.textCounter


        val btnSave = binding.btnSave

        // btnSave click event handler
        btnSave.setOnClickListener {
            // get the user input from editTextName
            name = editTextName.text.toString()
            // set the input into textName text field
            textName.text = name
        }


        val btnIncrement = binding.btnIncrement

        // btnIncrement click event handler, will increment the counter and show it
        btnIncrement.setOnClickListener {
            counter += 1
            textCounter.text = counter.toString()
        }
    }
}