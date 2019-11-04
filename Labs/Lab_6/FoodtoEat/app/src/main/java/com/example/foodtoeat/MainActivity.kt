package com.example.foodtoeat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var name: EditText? = null
    private var prepareText: TextView? = null
    private var food: ImageView? = null
    private var message: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name = findViewById(R.id.editText)
        prepareText = findViewById(R.id.message)
        food = findViewById(R.id.imageView)

        // check for recovering the instance state
        if (savedInstanceState != null) {
            message = savedInstanceState.getString("msg")
            val image_id = savedInstanceState.getInt("imageid")

            prepareText!!.text = message
            food!!.setImageResource(image_id)
        }
    }


    fun gotIt(view: View) {
        //edittext

        val nameValue = name!!.text.toString()

        //textview
        message = "We are cooking $nameValue!"
        prepareText!!.text = message

        //imageview
        food!!.setImageResource(R.drawable.gifcook)

    }

    // invoked when the activity may be temporarily destroyed, save the instance state here
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("msg", message)
        outState.putInt("imageid", R.drawable.gifcook)
    }
}
