package com.example.ask_nitt

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost( navController = navController , startDestination = Screens.MainScreen.route){
        composable(route = Screens.MainScreen.route){
            DisplayHome(navController)
        }
        composable(
            route = Screens.DashboardScreen.route //+ "/{name}",
            /* arguments = listOf(
                navArgument(name = "name") {
                    type = NavType.StringType
                    defaultValue = "Sukesh"
                    nullable = true
                }
            ) */
        ) {
            dispplayDashboard(navController)
        }
        composable(
            route = Screens.QuestionsScreen.route
        ){
            MyDoubtScreen(navController)
        }
        composable(
            route = Screens.MyAnswerScreen.route
        ) {
            Myanswer(navController)
        }
        composable(
            route = Screens.OtherQAns.route
        ){
            OtherAns(navController)
        }
        composable(
            route = Screens.AnsVoteScreen.route
        ){
            AnswerVote(navController)
        }

    }
}