package com.example.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //val nums = mapOf("one" to 1,"two" to 2, "three" to 3, "four" to 4, "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9)
    var stroke : String = ""
    var number : Double? = null
    var newNumber : Double? = null
    var lastNumber : Double? = null
    var isFloat : Boolean = false
    var method : String? = null
    var previousMethod : String? = null

    fun input(view: View) {
        var temp : String = ""
        if(stroke == "")
            temp = findViewById<TextView>(R.id.resultText).text.toString()
        with (view as Button) {
            if (view.text == "±") {
                if( stroke == ""){
                    stroke = temp
                    temp = ""
                }
                newNumber = null
                if(stroke.contains('-')){
                    stroke = stroke.replace("-", "")
                }
                else{
                    stroke = "-$stroke"
                }
            }
            else {
                stroke += view.text
            }
        }
        findViewById<TextView>(R.id.resultText).text = stroke
    }

    fun calculate(view: View){
        var result : Double
        with(view as Button) {
            if (method != null && previousMethod != method){
                previousMethod = method
            }
            method = ""
            method += view.text
        }
        if (number == null){
            if(stroke != ""){
                number = stroke.toDouble()
                stroke = ""
                findViewById<TextView>(R.id.resultText).text = "0"
                return
            }
            else{
                number = null
                stroke = ""
                findViewById<TextView>(R.id.resultText).text = "0"
                return
            }
        }
        var replace : Boolean = false
        do{
            if(method == previousMethod && method == "="){
                break
            }
            if(replace){
                method = previousMethod
                replace = false
            }
            when (method) {
                "%" -> {
                    if (stroke != "" && newNumber == null){
                        newNumber = stroke.toDouble()
                    }
                    if(newNumber == null){
                        stroke = ""
                        findViewById<TextView>(R.id.resultText).text = "0"
                    }
                    else {
                        result = (number!! / 100) * newNumber!!
                        if (result % 1 == 0.0) findViewById<TextView>(R.id.resultText).text =
                            result.toInt().toString()
                        else findViewById<TextView>(R.id.resultText).text = result.toString()
                        stroke = ""
                        lastNumber = newNumber
                        newNumber = null
                        number = result
                    }
                }

                "/" -> {
                    if (stroke != "" && newNumber == null){
                        newNumber = stroke.toDouble()
                    }
                    if(newNumber == null){
                        stroke = ""
                        findViewById<TextView>(R.id.resultText).text = "0"
                    }
                    else {
                        if(newNumber == 0.0){
                            findViewById<TextView>(R.id.resultText).text = "Err"
                            stroke = ""
                            number = null
                            newNumber = null
                            isFloat = false
                            method = null
                            previousMethod = null
                            return
                        }
                        result = number!! / newNumber!!
                        if (result % 1 == 0.0) findViewById<TextView>(R.id.resultText).text =
                            result.toInt().toString()
                        else findViewById<TextView>(R.id.resultText).text = result.toString()
                        stroke = ""
                        lastNumber = newNumber
                        newNumber = null
                        number = result
                    }
                }

                "×" -> {
                    if (stroke != "" && newNumber == null){
                        newNumber = stroke.toDouble()
                    }
                    if(newNumber == null){
                        stroke = ""
                        findViewById<TextView>(R.id.resultText).text = "0"
                    }
                    else {
                        result = number!! * newNumber!!
                        if (result % 1 == 0.0) findViewById<TextView>(R.id.resultText).text =
                            result.toInt().toString()
                        else findViewById<TextView>(R.id.resultText).text = result.toString()
                        stroke = ""
                        lastNumber = newNumber
                        newNumber = null
                        number = result
                    }
                }

                "-" -> {
                    if (stroke != "" && newNumber == null){
                        newNumber = stroke.toDouble()
                    }
                    if(newNumber == null){
                        stroke = ""
                        findViewById<TextView>(R.id.resultText).text = "0"
                    }
                    else {
                        result = number!! - newNumber!!
                        if (result % 1 == 0.0) findViewById<TextView>(R.id.resultText).text =
                            result.toInt().toString()
                        else findViewById<TextView>(R.id.resultText).text = result.toString()
                        stroke = ""
                        lastNumber = newNumber
                        newNumber = null
                        number = result
                    }
                }

                "+" -> {
                    if (stroke != "" && newNumber == null){
                        newNumber = stroke.toDouble()
                    }
                    if(newNumber == null){
                        stroke = ""
                        findViewById<TextView>(R.id.resultText).text = "0"
                    }
                    else {
                        result = number!! + newNumber!!
                        if (result % 1 == 0.0) findViewById<TextView>(R.id.resultText).text =
                            result.toInt().toString()
                        else findViewById<TextView>(R.id.resultText).text = result.toString()
                        stroke = ""
                        lastNumber = newNumber
                        newNumber = null
                        number = result
                    }
                }

                "=" -> {
                    if (newNumber == null && stroke != "") {
                        newNumber = stroke.toDouble()
                        replace = true
                    }
                    else{
                        if(newNumber == null)
                            newNumber = lastNumber
                        replace = true
                    }
                }
            }
        }while(method == "=")
    }

    fun reset(view: View){
        stroke = ""
        number = null
        newNumber = null
        isFloat = false
        method = null
        previousMethod = null
        findViewById<TextView>(R.id.resultText).text = "0"
    }
}