package com.example.vibrationproject

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import java.util.*

class MainActivity : AppCompatActivity() {

    //EditText Widget
    var txt: EditText? = null

    //Submit Button
    var btn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get Widgets from Layout
        txt = findViewById(R.id.input)
        btn = findViewById(R.id.button)
    }

    fun onSubmit (@Suppress("UNUSED_PARAMETER") view: View) {
        //get Text from EditText Widget
        val text = txt?.text.toString()

        //Convert Text to to int (If text is blank default to 35)
        val num: Int = if (text != "") text.toInt() else 35

        //Disable Vibrate Button
        btn?.isEnabled = false
        btn?.isClickable = false

        //vibrate "num" amount of times
        vibrate(0,num)
    }

    fun vibrate (timesVibrated: Int, totalVibrations: Int) {

        //Check if we still have more vibrations to do
        if (timesVibrated < totalVibrations) {

            var vibratior = getSystemService(VIBRATOR_SERVICE) as Vibrator
            //Vibrate
            vibratior.vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE))

            //Vibrate Again in 1 second
            Handler(Looper.getMainLooper()).postDelayed({
                vibrate(timesVibrated+1,totalVibrations)
            }, 1000)
        }
        else {
            //Re-enable Vibrate Button
            btn?.isEnabled = true
            btn?.isClickable = true
        }
    }
}
