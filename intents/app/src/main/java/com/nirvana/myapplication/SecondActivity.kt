package com.nirvana.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.nirvana.myapplication.ui.theme.MyApplicationTheme


class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SecondActivityScreen(innerPadding)
                }
            }
        }


    }
}

@Composable
fun SecondActivityScreen(innerPadding: PaddingValues) {
    val context = LocalContext.current as? Activity
    BackHandler {
        val resultIntent = Intent().apply {
            putExtra("info", "Second Activity info")
        }
        context?.setResult(Activity.RESULT_OK, resultIntent)
        context?.finish()
    }
}