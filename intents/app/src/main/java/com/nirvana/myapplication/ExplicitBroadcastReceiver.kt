package com.nirvana.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ExplicitBroadcastReceiver(): BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            val message = it.getStringExtra("info")
            Toast.makeText(context, "Received message: $message", Toast.LENGTH_LONG).show()
        }
    }
}
