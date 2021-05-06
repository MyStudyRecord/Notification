package com.what.notifications.Tapaction82

import android.app.NotificationManager
import android.app.RemoteInput
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.NotificationCompat
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

            val channelID = "com.what.notifications.SimpleNotificationExample80.channel1"
            val notificationId = 45

            val repliedNotification = NotificationCompat.Builder(this, channelID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentText("Your reply received")
                .build()

            //알림관리자 인스턴스 가져옴, 시스템 서비스 사용
            val notificationManager:NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            //마지막으로 notificationManager인스턴스를 사용하여 알림
            notificationManager.notify(notificationId, repliedNotification)
        }
    }
}