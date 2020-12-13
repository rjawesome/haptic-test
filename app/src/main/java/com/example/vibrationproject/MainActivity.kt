package com.example.vibrationproject

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    //EditText Widget
    private var txt: EditText? = null

    //Submit Buttons
    private var btnLong: Button? = null
    private var btnShort: Button? = null

    //Counter Text
    private var counter: TextView? = null

    //Vibrator Service
    private var vibrator: Vibrator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get Widgets from Layout
        txt = findViewById(R.id.input)
        btnShort = findViewById(R.id.button_short)
        btnLong = findViewById(R.id.button_long)
        counter = findViewById(R.id.counter)

        //Get Vibrator Service
        vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
    }

    fun onSubmit (view: View) {
        //get Text from EditText Widget
        val text = txt?.text.toString()

        //Convert Text to to int (If text is blank default to 35)
        val num: Long = if (text != "") text.toLong() else 35

        //Calculate Vibration Time based on which button is pressed
        val vibrationTime: Long = if (view.id == R.id.button_short) 100 else 500

        //vibrate "num" amount of times
        vibrate(num,vibrationTime)
    }


    private fun vibrate (vibrations: Long, vibrationTime: Long) {
        var count = 0

        //Disable Vibrate Button
        setButtonsEnabled(false)

        //Timer to vibrate every 1 second
        object : CountDownTimer(vibrations * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                //Increment count and update counter
                count++
                counter?.text = count.toString()

                //Vibrate (Using max amplitude which is 255)
                vibrator?.vibrate(VibrationEffect.createOneShot(vibrationTime, 255))
            }
            override fun onFinish() {
                //Re-enable Vibrate Button
                setButtonsEnabled(true)

                //Update Counter
                counter?.text = "Finished"
            }
        }.start()
    }

    //Enables/Disables Buttons
    private fun setButtonsEnabled (state: Boolean) {
        btnShort?.isEnabled = state
        btnShort?.isClickable = state
        btnLong?.isEnabled = state
        btnLong?.isClickable = state
    }
}
