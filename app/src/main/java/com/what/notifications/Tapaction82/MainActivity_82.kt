package com.what.notifications.Tapaction82

import android.app.RemoteInput
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.what.notifications.R

class MainActivity_82 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_82)
        receiveInput()
    }

    private fun receiveInput(){
        val KEY_REPLY = "key_reply"
        val intent = this.intent
        val remoteInput = RemoteInput.getResultsFromIntent(intent)
        if (remoteInput!=null){
            val inputString = remoteInput.getCharSequence(KEY_REPLY).toString()
            val result_text_view = findViewById<TextView>(R.id.result_text_view)
            result_text_view.text = inputString
        }
    }
}