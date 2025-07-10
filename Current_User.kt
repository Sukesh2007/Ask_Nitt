package com.example.ask_nitt

import androidx.compose.runtime.LaunchedEffect

object Current_User {
    var username: String = ""
    var Rollno: String = ""
    var qno: Int = 1
    var question: String = ""
    var q_id: String = ""
    var otherqn_roll: String = ""
    var otherqn_username: String = ""
    var answerId = mutableListOf<String>()


}

sealed class Screens(val route:String) {
    object MainScreen: Screens("main_screen")
    object DashboardScreen: Screens("detail_screen")
    object QuestionsScreen: Screens("myquestion_screen")
    object MyAnswerScreen: Screens("answer_screen")
    object OtherQAns: Screens("other_qans")
    object AnsVoteScreen: Screens("ans_vote")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {
                    arg ->
                append("/$arg")
            }
        }
    }
}