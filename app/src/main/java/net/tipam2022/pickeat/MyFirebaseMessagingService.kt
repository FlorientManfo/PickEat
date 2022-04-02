package net.tipam2022.pickeat

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.text.CaseMap
import android.os.Build
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val  channelid="notififcation_channel"
const val  channelName="net.tipam2022.pickeat"
class MyFirebaseMessagingService :FirebaseMessagingService(){
    var test = 0

    //generate notification
    //attach the notificatin create whith the custom layout

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if(remoteMessage.getNotification() != null){
            generateNotification(remoteMessage.notification!!.title!!,remoteMessage.notification!!.body!!)
        }
    }

    //@RequiresApi(Build.VERSION_CODES.O)
    fun  getRemoteView(title: String,message: String):RemoteViews{
        val remoteViews = RemoteViews("net.tipam2022.pickeat",R.layout.notification)
        remoteViews.setTextViewText(R.id.title,title)
        remoteViews.setTextViewText(R.id.message,message)
        remoteViews.setImageViewResource(R.id.app_logo,R.drawable.takos)

        return  remoteViews
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun generateNotification(title: String, message:String){

        var intent = Intent(this,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        var pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT)

        //channel id , channel name
        var builder : Notification.Builder=Notification.Builder(applicationContext, channelid)
            .setSmallIcon(R.drawable.takos)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000,1000,1000,1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

       builder = builder.setContent(getRemoteView(title,message))

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(channelid, channelName,NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)

        }
        notificationManager.notify(0,builder.build())
    }


}