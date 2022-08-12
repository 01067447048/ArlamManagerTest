package com.jaehyeon.arlammanagertest

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.jaehyeon.arlammanagertest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()
    private val manager: AlarmManager by lazy {
        getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }
    private lateinit var pIntent: PendingIntent

    @SuppressLint("ShortAlarm", "UnspecifiedImmutableFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = model

        model.state.observe(this) { state ->

            when(state) {
                is ButtonState.Action1H -> {
                    pIntent = Intent(this, AlarmBroadCastReceiver::class.java).apply {
                        putExtra("data", "1H")
                    }.let {
                            PendingIntent.getBroadcast(applicationContext, 0, it,  PendingIntent.FLAG_IMMUTABLE)
                    }
                    manager.setRepeating(AlarmManager.RTC_WAKEUP, state.time.timeInMillis, AlarmManager.INTERVAL_HOUR, pIntent)
                }
                is ButtonState.Action1M -> {
                    pIntent = Intent(this, AlarmBroadCastReceiver::class.java).apply {
                        putExtra("data", "1M")
                    }.let {
                        PendingIntent.getBroadcast(applicationContext, 0, it,  PendingIntent.FLAG_IMMUTABLE)
                    }
                    manager.setRepeating(AlarmManager.RTC_WAKEUP, state.time.timeInMillis, 60 * 1000L, pIntent)
                }
                is ButtonState.Action30S -> {
                    pIntent = Intent(this, AlarmBroadCastReceiver::class.java).apply {
                        putExtra("data", "30S")
                    }.let {
                        PendingIntent.getBroadcast(applicationContext, 0, it,  PendingIntent.FLAG_IMMUTABLE)
                    }
                    manager.setRepeating(AlarmManager.RTC_WAKEUP, state.time.timeInMillis, 30 * 1000L, pIntent)
                }

                ButtonState.ActionCancel -> {
                    manager.cancel(pIntent)
                }

                else -> Unit
            }
        }
    }

}