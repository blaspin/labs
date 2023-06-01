package com.example.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlin.math.ceil
import kotlin.math.round
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun calculate(view: View){
        val vals : Array<String> =  arrayOf(
            findViewById<EditText>(R.id.a).editableText.toString(),
            findViewById<EditText>(R.id.b).editableText.toString(),
            findViewById<EditText>(R.id.c).editableText.toString()
        )

        if(checkAndShowErrors(vals)) {
            val a : Int = vals[0].toInt()
            val b : Int = vals[1].toInt()
            val c : Int = vals[2].toInt()

            var d : Double = ((b*b) - (4 * a * c)).toDouble()
            if (d < 0){
                findViewById<TextView>(R.id.result).text = "Нет корней"
            }
            if (ceil(d).toInt() == 0){
                findViewById<TextView>(R.id.result).text = (((-1 * b) + sqrt(d))).toString()
            }
            if (ceil(d).toInt() > 0){
                findViewById<TextView>(R.id.result).text = (((-1 * b) + sqrt(d))).toString() + "\n"
                findViewById<TextView>(R.id.result).text = findViewById<TextView>(R.id.result).text.toString() + (((-1 * b) - sqrt(d))).toString()
            }
        }
    }

    fun checkAndShowErrors(vals: Array<String>) : Boolean{
        findViewById<TextView>(R.id.errors).text = " "
        var result: Boolean = false
        run breaking@{
            vals.forEach {
                try {
                    if (it.isEmpty()) {
                        throw java.lang.Exception("empty")
                    }
                    it.toInt()
                    result = true

                } catch (e: Exception) {
                    if (e.message == "empty") {
                        findViewById<TextView>(R.id.errors).text = "Не все поля заполнены"
                        return@breaking
                    }
                    if (e is java.lang.ClassCastException){
                        findViewById<TextView>(R.id.errors).text = findViewById<TextView>(R.id.errors).text.toString() + it + " не число \n"
                    }
                    result = false
                    findViewById<TextView>(R.id.errors).text = "Ошибка: ${e.message}"
                    return result
                }
            }
        }
        return result
    }
}
