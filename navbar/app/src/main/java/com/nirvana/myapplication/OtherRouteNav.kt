package com.nirvana.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument


fun NavGraphBuilder.OtherRouteNav(navController: NavController) {
    navigation(startDestination = Screens.Route1.route, route = Screens.OtherRoute.route) {
        composable(route = Screens.Route1.route) {
            Screen1(navController)
        }
        composable(
            route = "${Screens.Route2.route}/{name}/{age}",
            arguments = listOf(
                navArgument(name = "name") { type = NavType.StringType },
                navArgument(name = "age") { type = NavType.StringType }
            )) { backStackEnter ->
            Screen2(
                navController,
                name = backStackEnter.arguments?.getString("name"),
                age = backStackEnter.arguments?.getString("age")
            )
        }
        composable(route = Screens.Route3.route) {
            Screen3(navController)
        }
    }
}