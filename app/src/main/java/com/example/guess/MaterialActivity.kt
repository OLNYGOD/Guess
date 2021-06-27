package com.example.guess

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*
import kotlin.math.log2

class MaterialActivity : AppCompatActivity() {
    private val REQUEST_CODE: Int = 100
    val secretNumber = SecretNumber()
    val TAG = MaterialActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(toolbar)
        Log.d(TAG, "secretnumber : " + secretNumber.secret)
        fab.setOnClickListener { view ->
            replay()
        }
        counter.setText(secretNumber.count.toString())
        val counter = getSharedPreferences("guess", Context.MODE_PRIVATE)
            .getInt("Rec_counter", -1)
        val nickname = getSharedPreferences("guess", Context.MODE_PRIVATE)
            .getString("Rec_nickname", null)
        Log.d(TAG,"data : " + counter +" / nickname : " + nickname)
    }

    private fun replay() {
        AlertDialog.Builder(this)
            .setTitle("Replay game")
            .setMessage("Are you sure")
            .setPositiveButton(getString(R.string.ok), { dialog, which ->
                secretNumber.reset()
                counter.setText(secretNumber.count.toString())
                ed_number.setText("")
                Log.d(TAG, "fabsecretnumber : " + secretNumber.secret)
            })
            .setNegativeButton("Cancel", null)
            .show()
        var counter = counter.setText(secretNumber.count.toString())
    }

    fun check(view: View) {
        val number = ed_number.text.toString().toInt()
        val godnumber = 3
        Log.d(TAG, "enter_number : " + number)
        println("enter_number : $number")
        var diff = secretNumber.validate(number)
        var message = getString(R.string.you_get_it)
        if (diff > 0) {
            message = getString(R.string.bigger)
        } else if (diff < 0) {
            message = getString(R.string.smaller)
        }else if ((diff == 0) &&  secretNumber.count < 3){
            message = getString(R.string.excellent_number_is) + secretNumber.secret
        }
        counter.setText(secretNumber.count.toString())
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), {dialog, which ->
                if(diff == 0 ){
                    val intent = Intent(this,RecordActivity::class.java)
                    intent.putExtra("COUNTER", secretNumber.count)
                    //startActivity(intent) //開啟新的intent
                    startActivityForResult(intent, REQUEST_CODE)
                }
            }) //按鈕按下執行
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val nickname = data?.getStringExtra("nickname")
                Log.d(TAG, "onActivityResult : " + nickname)
                replay()
            }
        }
    }
}