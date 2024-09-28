package com.nirvana.myapplication

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument

fun NavGraphBuilder.AppRouteNav(navController: NavController){
    navigation(startDestination = Screens.ARoute.route, route = Screens.AppRoute.route) {
        composable(route = Screens.ARoute.route) {
            ScreenA(navController)
        }
        composable(
            route = "${Screens.BRoute.route}/{name}/{age}",
            arguments = listOf(
                navArgument(name = "name") { type = NavType.StringType },
                navArgument(name = "age") { type = NavType.StringType }
            )) { backStackEnter ->
            ScreenB(
                navController,
                name = backStackEnter.arguments?.getString("name"),
                age = backStackEnter.arguments?.getString("age")
            )
        }
        composable(route = Screens.CRoute.route) {
            ScreenC(navController)
        }
    }
}