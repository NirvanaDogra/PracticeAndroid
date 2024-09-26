package com.nirvana.myapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.nirvana.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT)

        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Text(text = sharedText ?: "No text shared")
                        MainScreen()
                    }
                }
            }
        }
    }
}
@Composable
fun MainScreen() {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            Toast.makeText(context, data?.getStringExtra("info"), Toast.LENGTH_LONG).show()
        }
    }
    Row() {
        Button(onClick = {
            val intent = Intent(context, SecondActivity::class.java)
            context.startActivity(intent)
        }) {
            Text("explicit intent (with return)")
        }
        Button(onClick = {
            val intent = Intent(context, SecondActivity::class.java)
            launcher.launch(intent)
        }) {
            Text("explicit intent (with return)")
        }
    }

    var imageUri: Uri? by remember { mutableStateOf(null) }
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            imageUri = uri
        }
    )
    Button(onClick = {
        imagePickerLauncher.launch("image/*")
    }) {
        Text("Select an image")
    }
    imageUri?.let { uri ->
        Image(
            painter = rememberAsyncImagePainter(uri),
            contentDescription = "Selected Image",
            modifier = Modifier
                .size(200.dp)
                .padding(8.dp)
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}