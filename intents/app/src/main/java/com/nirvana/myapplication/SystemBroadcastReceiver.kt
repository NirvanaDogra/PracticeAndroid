package com.nirvana.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class SystemBroadcastReceiver() : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let{
            if(intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
                Toast.makeText(it, "this is a broadcast ${Intent.ACTION_AIRPLANE_MODE_CHANGED}", Toast.LENGTH_LONG).show()
            }
        }
    }
}