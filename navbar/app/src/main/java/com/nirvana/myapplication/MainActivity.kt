package com.nirvana.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nirvana.myapplication.ui.theme.MyApplicationTheme

sealed class Screens(val route: String) {
    object ARoute: Screens(route = "A")
    object BRoute: Screens(route = "B")
    object CRoute: Screens(route = "C")

    object Route1: Screens(route = "1")
    object Route2: Screens(route = "2")
    object Route3: Screens(route = "3")

    object AppRoute: Screens(route = "App")
    object OtherRoute: Screens(route = "OtherRoute")
    object Start: Screens(route = "Start")
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            MyApplicationTheme {
                NavHost()
            }
        }
    }
}


@Composable
fun NavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.Start.route) {
        composable(route = Screens.Start.route) {
            Start(navController = navController)
        }
        AppRouteNav(navController= navController)
        OtherRouteNav(navController = navController)
    }
}

@Composable
fun Start(navController: NavController) {
    Column {
        Button(onClick = { navController.navigate(Screens.AppRoute.route) }) {
            Text(text = "Home")
        }
        Button(onClick = { navController.navigate(Screens.OtherRoute.route) }) {
            Text(text = "Home")
        }
    }
}

@Composable
fun ScreenA(navController: NavController) {
    val name = "Nirvana"
    val age = "12"
    Box(Modifier.fillMaxSize()) {
        Button(onClick = {
            navController.navigate("B/$name/$age")
        }, modifier = Modifier.align(Alignment.Center)) {
            Text(text = "To Screen B")
        }
    }

}

@Composable
fun ScreenB(navController: NavController, name: String?, age: String?) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = "$name and $age")
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp))
        Button(onClick = {
            navController.navigate("C")
        }) {
            Text(text = "To Screen C")
        }
    }
}

@Composable
fun ScreenC(navController: NavController) {
    Box(Modifier.fillMaxSize()) {
        Button(onClick = {
            navController.navigate("A")  {
                popUpTo("A") { inclusive = true }
            }

        }, modifier = Modifier.align(Alignment.Center)) {
            Text(text = "Back to Screen A")
        }
    }
}



@Composable
fun Screen1(navController: NavController) {
    val name = "Nirvana"
    val age = "12"
    Box(Modifier.fillMaxSize()) {
        Button(onClick = {
            navController.navigate("2/$name/$age")
        }, modifier = Modifier.align(Alignment.Center)) {
            Text(text = "To Screen 1")
        }
    }

}

@Composable
fun Screen2(navController: NavController, name: String?, age: String?) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = "$name and $age")
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp))
        Button(onClick = {
            navController.navigate("3")
        }) {
            Text(text = "To Screen 3")
        }
    }
}

@Composable
fun Screen3(navController: NavController) {
    Box(Modifier.fillMaxSize()) {
        Button(onClick = {
            navController.navigate("1")  {
                popUpTo("1") { inclusive = true }
            }

        }, modifier = Modifier.align(Alignment.Center)) {
            Text(text = "Back to Screen 1")
        }
    }
}