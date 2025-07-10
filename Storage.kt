package com.example.ask_nitt

data class Users(
    val username: String,
    val password: String,
    val rollno: String
)

data class Status(
    val state: String
)

data class Login(
    val rollno: String,
    val password: String
)

data class Token(
    val authenticate:String
)

data class Dashboard(
    val check: Boolean,
    val name: String

)

data class LogOut(
    val rollno: String
)

data class OutStatus(
    val status: String,
    val rollno: String
)


data class Rescheck(
    val test: List<String>
)

data class Question(
    val quetext: String,
    val tags: String,
    val username: String,
    val rollno: String
)

data class IsSent(
    val success: Boolean,
    val message: String
)

data class MyQuestions(
    val success: Boolean,
    val questions: List<QFormat>
)

data class QFormat(
    val QueText: String,
    val tags: List<String>,
    val timestamp: List<String>,
    val q_id: String
)

data class Answers(
    val success: Boolean,
    val answers: PostedAns
)

data class Answerss(
    val success: Boolean,
    val answers: List<PostedAns>
)

data class PostedAns(
    val text: String,
    val username: String,
    val rollno: String,
    val upvotes: Int,
    val downvotes: Int,
    val _id: String
)

data class OtherQuestion(
    val QueText: String,
    val tags: List<String>,
    val timestamp: List<String>,
    val q_id: String,
    val postedUsername: String,
    val postedrollno: String
)

data class Welcome(
    val success: Boolean,
    val Other_data: List<OtherQuestion>
)
data class PostAns(
    val text:String,
    val username:String,
    val rollno:String
)
data class Vote(
    val roll : String,
    val type: String
)
//for response check class isSent

data class CheckVotes(
    val type: String,
    val message: String
)

