package cz.utb.fai.counterdatastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import cz.utb.fai.counterdatastore.data.CounterDataStore
import cz.utb.fai.counterdatastore.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var CounterDataStore: CounterDataStore

    var counter = 0
    var name = ""
    var email = ""

    private fun requireContext(): MainActivity {
        return this
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textName = binding.textName
        val editTextName = binding.editTextName
        val editTextEmail = binding.editTextEmail
        val textCounter = binding.textCounter
        val btnSave = binding.btnSave

        CounterDataStore = CounterDataStore(this)
        CounterDataStore.counterFlow.asLiveData().observe( this, { value ->
            counter = value
            textCounter.text = value.toString()
        })

        CounterDataStore.emailFlow.asLiveData().observe( this, { value ->
            editTextEmail.setText(value)
        })

        CounterDataStore.nameFlow.asLiveData().observe( this, { value ->
            editTextName.setText(value)
        })



        // btnSave click event handler
        btnSave.setOnClickListener {
            // get the user input from editTextName
            name = editTextName.text.toString()
            // set the input into textName text field
            textName.text = name

            // get the user email input
            email = editTextEmail.text.toString()

            lifecycleScope.launch {
                CounterDataStore.saveUserEmailAndName(email, name, requireContext())
            }
        }

        val btnIncrement = binding.btnIncrement

        // btnIncrement click event handler, will increment the counter and show it
        btnIncrement.setOnClickListener {
            counter += 1
            lifecycleScope.launch {
                CounterDataStore.saveCounterValue(counter, requireContext())
            }

            textCounter.text = counter.toString()
        }
    }
}