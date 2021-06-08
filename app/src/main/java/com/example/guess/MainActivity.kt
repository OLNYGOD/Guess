package com.example.guess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val secretNumber = SecretNumber()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "secretnumber : " + secretNumber.secret)
    }

    fun check(view : View){
        val  number = ed_number.text.toString().toInt()
        Log.d("MainActivity" , "enter_number : "+number)
        println("enter_number : $number")
        var diff = secretNumber.validate(number)
        var message = "You get it"
        if (diff>0){
            message = "Bigger"
        }else if(diff < 0){
            message = "Smaller"
        }
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show()
        AlertDialog.Builder(this)
            .setTitle("Message")
            .setMessage(message)
            .setPositiveButton("ok", null) //按鈕按下執行
            .show()
    }
}
