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
    val TAG = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "secretnumber : " + secretNumber.secret)
    }

    fun check(view : View){
        val  number = ed_number.text.toString().toInt()
        Log.d(TAG , "enter_number : "+number)
        println("enter_number : $number")
        var diff = secretNumber.validate(number)
        var message = getString(R.string.you_get_it)
        if (diff>0){
            message = getString(R.string.bigger)
        }else if(diff < 0){
            message = getString(R.string.smaller)
        }
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), null) //按鈕按下執行
            .show()
    }
}
