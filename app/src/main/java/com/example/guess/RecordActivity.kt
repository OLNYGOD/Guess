package com.example.guess

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        val count = intent.getIntExtra("COUNTER",-1)
        counter.setText(count.toString())
        //onclicklistener
        save.setOnClickListener {view ->
            val nick = nickname.text.toString()
            //儲存資料使用getsharedpreferences
            getSharedPreferences("guess", Context.MODE_PRIVATE)
                .edit()
                .putString("Rec_nickname", nick)
                .putInt("Rec_counter", count)
                //.commit()    //立即儲存
                .apply()  //有空才儲存
        }
    }
}
