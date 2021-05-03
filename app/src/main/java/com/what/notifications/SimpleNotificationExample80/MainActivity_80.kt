package com.what.notifications.SimpleNotificationExample80

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.databinding.DataBindingUtil
import com.what.notifications.R
import com.what.notifications.databinding.ActivityMain80Binding

class MainActivity_80 : AppCompatActivity() {
    //알림을 위한 채널 id 정의
    private val channelID = "com.what.notifications.SimpleNotificationExample80.channel1"
    //알림 관리자 인스턴스 정의(알림 채널과 알림 인스턴스를 만드는데 필요)
    private var notificationManager:NotificationManager? = null
    lateinit var binding:ActivityMain80Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main_80)

        //알림관리자 인스턴스를 가져옴
        notificationManager = getSystemService( Context.NOTIFICATION_SERVICE) as NotificationManager
        //알림채널에는 ID, 채널 이름 및 채널 설명이 있다


        createNotificationChannel(channelID,"DemoChannel","this is a demo")


        binding.button.setOnClickListener {
            displayNotification()
        }
    }

    private fun displayNotification(){
        val notificationId = 45
        val notification = NotificationCompat.Builder(this@MainActivity_80, channelID)
            .setContentTitle("Demo Title")
            .setContentText("This is a semo notification")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        notificationManager?.notify(notificationId, notification)
    }

    private fun createNotificationChannel(id: String, name: String, channeIDescription: String){
        //SDK버전 확인(낮으면 기능작동X, oreo이상부터 작동)
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id, name, importance).apply {
                description = channeIDescription
            }
            notificationManager?.createNotificationChannel(channel)

        }
    }
}