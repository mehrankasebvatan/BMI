package test.bmi

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import test.bmi.R
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val height = findViewById<EditText>(R.id.height_et)
        val weight = findViewById<EditText>(R.id.weight_et)
        val calculate = findViewById<Button>(R.id.calculate)
        val result = findViewById<TextView>(R.id.result)
        val statusValue = findViewById<TextView>(R.id.statusValue)
        val bmi = findViewById<TextView>(R.id.bmi)
        val status = findViewById<TextView>(R.id.status)
        val restart = findViewById<Button>(R.id.restart)

        bmi.visibility = View.INVISIBLE
        status.visibility = View.INVISIBLE




        calculate.setOnClickListener {

            closeKeyBoard()
            var weightValue = 0.0
            var heightValue = 0.0
            if (weight.text.toString().isNotEmpty()) {
                weightValue = weight.text.toString().toDouble()
            }
            if (height.text.toString().isNotEmpty()) {
                heightValue = (height.text.toString().toDouble() / 100)
            }
            if (weightValue > 0.0 && heightValue > 0.0) {
                val bmiValue = String.format("%.2f", weightValue / heightValue.pow(2))
                result.text = bmiValue
                bmi.visibility = View.VISIBLE
                status.visibility = View.VISIBLE
            } else {
                Toast.makeText(this, R.string.toast, Toast.LENGTH_SHORT).show()
            }


            var statusValues = 0.0
            if (result.text.toString().isNotEmpty()) {
                statusValues = result.text.toString().toDouble()
            }
            if (statusValues <= 18.5 && statusValues > 0) {
                statusValue.text = getString(R.string.low)
            } else if (statusValues > 18.5 && statusValues <= 24.5) {
                statusValue.text = getString(R.string.normal)
            } else if (statusValues > 24.5 && statusValues <= 30) {
                statusValue.text = getString(R.string.heavy)
            } else if (statusValues > 30) {
                statusValue.text = getString(R.string.fat)
            }


        }

        restart.setOnClickListener {
            finish()
            startActivity(getIntent())

        }


    }


    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}






