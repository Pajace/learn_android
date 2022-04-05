package com.google.pajace.servicetest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

  lateinit var downloadBinder: MyService.DownloadBinder

  private val connection =
      object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
          downloadBinder = service as MyService.DownloadBinder
          downloadBinder.startDownload()
          downloadBinder.getProgress()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
          TODO("Not yet implemented")
        }
      }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    findViewById<Button>(R.id.startServiceBtn).setOnClickListener {
      val intent = Intent(this, MyService::class.java)
      startService(intent)
    }
    findViewById<Button>(R.id.stopServiceBtn).setOnClickListener {
      val intent = Intent(this, MyService::class.java)
      stopService(intent)
    }

    findViewById<Button>(R.id.bindServiceBtn).setOnClickListener {
      val intent = Intent(this, MyService::class.java)
      bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }
    findViewById<Button>(R.id.unBindServiceBtn).setOnClickListener { unbindService(connection) }
  }
}
