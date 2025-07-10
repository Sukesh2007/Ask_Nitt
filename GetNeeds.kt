package com.example.ask_nitt

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

interface PostRegister {
    @POST("/register")
    suspend fun postUser(@Body user: Users): Response<Status>

    @PUT("/login")
    suspend fun createaccess(@Body login: Login): Response<Token>

    @GET("/dashboard")
    suspend fun dashboardAccess(@Header("Authorization") token:String): Response<Dashboard>

    @PUT("/logout")
    suspend fun loggingout(@Body roll: LogOut): Response<OutStatus>

    @GET("/check")
    suspend fun checkaccess(): Response<Rescheck>

    @POST("/question")
    suspend fun postQuestion(@Body question: Question): Response<IsSent>

    val rollno: String
        get() = Current_User.Rollno

    @GET("/questions/{rollno}")
    suspend fun getquestion( @Path("rollno") rollno:String ): Response<MyQuestions>

    @GET("/question/answer/{id}")
    suspend fun getanswer( @Path("id") id:String): Response<Answerss>

    @GET("other/question/{rollno}")
    suspend fun getotherQue(@Path("rollno") rollno:String): Response<Welcome>

    @PUT("/answer/{id}")
    suspend fun postanswer(@Path("id") id:String , @Body answer:PostAns): Response<Answers>

    @PUT("/vote/{a_id}/{q_id}")
    suspend fun postvote(@Path("a_id") a_id: String , @Path("q_id") q_id:String, @Body vote: Vote): Response<IsSent>

    @GET("/voted/answer/{id}/{roll}")
    suspend fun checkvotes(@Path("id") id: String , @Path("roll") roll: String): Response<CheckVotes>
}