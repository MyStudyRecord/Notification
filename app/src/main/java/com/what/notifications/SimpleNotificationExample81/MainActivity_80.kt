package com.what.notifications.SimpleNotificationExample81

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.databinding.DataBindingUtil
import com.what.notifications.ActionButtons83.DetailsActivity
import com.what.notifications.ActionButtons83.SettingsActivity
import com.what.notifications.R
import com.what.notifications.Tapaction82.MainActivity_82
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

        //메세지 아이콘을 눌렀을때 이동
        val tapResultIntent =  Intent(this, MainActivity_82::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            tapResultIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )



        //action button 1 ----------------------------------------------------------------
        val intent2Intent =  Intent(this, DetailsActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent2: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent2Intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val action2 : NotificationCompat.Action =
            NotificationCompat.Action.Builder(0,"Details",pendingIntent2).build()

        //action button 2 ----------------------------------------------------------------
        val intent3Intent =  Intent(this, SettingsActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent3: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent3Intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val action3 : NotificationCompat.Action =
            NotificationCompat.Action.Builder(0,"Settings",pendingIntent3).build()








        val notification = NotificationCompat.Builder(this@MainActivity_80, channelID)
            .setContentTitle("Demo Title")
            .setContentText("This is a semo notification")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .addAction(action2)
            .addAction(action3)
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