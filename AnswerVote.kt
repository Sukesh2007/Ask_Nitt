package com.example.ask_nitt

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontStyle



@Composable
fun AnswerVote(navController: NavController) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    var answer by remember { mutableStateOf("") }
    var ansStatus = remember { mutableStateListOf<PostedAns>() }
    var message1 by remember { mutableStateOf("") }
    var isSend by remember { mutableStateOf(0) }
    var message by remember { mutableStateOf("...Loading") }
    val scaffoldState = rememberScaffoldState()



    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {paddingValues ->
        LaunchedEffect(Unit){
            try {
                val Respans = RetroInstance.api1.getanswer(Current_User.q_id).body()
                if(Respans?.success == true){
                    ansStatus.addAll(Respans.answers)
                    message1 = ""
                }
                else{
                    message1 = "Error in the server"
                }
            }catch(e: Exception){
                message1 = "Check Internet Connection"
            }
        }
        LaunchedEffect(isSend) {
            if(isSend == 1) {
                try {
                    val Resans = RetroInstance.api1.postanswer(
                        Current_User.q_id, PostAns(
                            answer,
                            Current_User.username, Current_User.Rollno
                        )
                    ).body()
                    if (Resans?.success == true) {
                        ansStatus.add(Resans.answers)
                        message == "Successful"
                    } else {
                        message = "Error in the Server"
                    }
                } catch (e: Exception) {
                    message = "Check network Connection"
                }
                when {
                    message == "Error in the Server" -> scaffoldState.snackbarHostState.showSnackbar(
                        "Error in the server"
                    )

                    message == "Check network Connection" -> scaffoldState.snackbarHostState.showSnackbar(
                        "Check Internet Connection"
                    )
                }
            }
            isSend = 0
        }

        if(message1 == "") {
            Column(
                modifier = Modifier.fillMaxSize()
                    .background(Color.White.copy(0.7f))
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .height(90.dp)
                        .background(color = Color.Black.copy(0.75f))
                        .padding(start = 10.dp, top = 34.dp)

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.back),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Back button",
                        modifier = Modifier.size(45.dp)
                            .clickable {
                                navController.navigate(Screens.OtherQAns.route)
                            }
                    )
                    Spacer(modifier = Modifier.width(25.dp))
                    androidx.compose.material3.Text(
                        text = "Answer and Vote",
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Start
                    )

                }

                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                        .height(screenHeight - 200.dp)
                ) {
                    itemsIndexed(
                        ansStatus
                    ) { index, ans ->
                        var isVoted by remember { mutableStateOf("")}
                        var isSave by remember {mutableStateOf(0)}
                        var typeofvote by remember {mutableStateOf("")}
                        val answerId = ans._id
                        LaunchedEffect(answerId) {
                            try {
                                val response = RetroInstance.api1.checkvotes(
                                    answerId,
                                    Current_User.Rollno
                                ).body()
                                typeofvote = when(response?.message){
                                    "up" -> "up"
                                    "down"->"down"
                                    else -> ""
                                }
                            }catch(e:Exception){
                                typeofvote = "${e.message}"
                            }
                        }

                        Box(
                            modifier = Modifier.padding(start = 50.dp, top = 7.dp,end = 10.dp)
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .background(
                                    color = Color(0xFFF0F9FF),
                                    shape = RoundedCornerShape(10.dp),
                                )
                                .border(
                                    width = 2.dp,
                                    color = Color(0xFF0284C7),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .padding(16.dp)
                        ) {
                            Column {
                                Text(
                                    text = ans.text,
                                    color = Color(0xFF0F172A),
                                    fontSize = 16.sp,
                                    lineHeight = 22.sp
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Row {
                                    /*Column(modifier = Modifier.fillMaxWidth()
                                .wrapContentHeight(),
                                horizontalAlignment = Alignment.End){
                                    Image(
                                        painter = painterResource(id = R.drawable.)
                                    )
                            }*/
                                    Column(
                                        modifier = Modifier.fillMaxWidth()
                                            .wrapContentHeight(),
                                        horizontalAlignment = Alignment.End
                                    ) {
                                        Text(
                                            text = "---  ${ans.username}(${ans.rollno})",
                                            textAlign = TextAlign.End,
                                            fontSize = 14.sp,
                                            fontStyle = FontStyle.Italic,
                                            color = Color(0xFF334155) // soft slate gray
                                        )

                                        Spacer(modifier = Modifier.height(5.dp))


                                        Text(
                                            text = "\uD83D\uDC4D ${ans.upvotes + if(typeofvote == "up") 1 else 0}   \uD83D\uDC4E ${ans.downvotes + if(typeofvote == "down") 1 else 0}",
                                            color = Color.DarkGray,
                                            fontSize = 12.sp,
                                            fontStyle = FontStyle.Normal,
                                            textAlign = TextAlign.End
                                        )
                                        Spacer(modifier = Modifier.height(1.dp))
                                        Text(
                                            text = if(typeofvote == "up")
                                            {"Up voted"}
                                            else if(typeofvote == "down")
                                            {"Down Voted"}
                                            else{
                                                ""
                                            },
                                            color = Color(0xFF808080),
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            textAlign = TextAlign.End
                                        )
                                        Spacer(modifier = Modifier.height(6.dp))



                                            if(typeofvote != "up" && typeofvote != "down") {
                                                Row(
                                                    modifier = Modifier.fillMaxWidth()
                                                        .height(60.dp)
                                                        .padding(10.dp)
                                                        .background(
                                                            color = Color(0xFFB2FFB2),
                                                            shape = RoundedCornerShape(8.dp)
                                                        )
                                                        .border(
                                                            width = 2.dp,
                                                            shape = RoundedCornerShape(8.dp),
                                                            color = Color.Black,
                                                        ).padding(12.dp),
                                                    horizontalArrangement = Arrangement.SpaceAround
                                                ) {
                                                    if (isVoted == "" && typeofvote == "") {
                                                        Text(
                                                            text = "UpVote",
                                                            color = Color.DarkGray,
                                                            fontSize = 15.sp,
                                                            fontWeight = FontWeight.Bold,
                                                            textAlign = TextAlign.Start,
                                                            modifier = Modifier.clickable {

                                                                isVoted = "up"
                                                            }
                                                        )

                                                        Text(
                                                            text = "DownVote",
                                                            color = Color.DarkGray,
                                                            fontSize = 15.sp,
                                                            fontWeight = FontWeight.Bold,
                                                            textAlign = TextAlign.End,
                                                            modifier = Modifier.clickable {

                                                                isVoted = "down"
                                                            }
                                                        )
                                                    } else if ((typeofvote == "" || typeofvote == "Check Internet" || typeofvote == "error") && isVoted != "") {
                                                        Text(
                                                            text = "Save Vote",
                                                            color = Color.DarkGray,
                                                            fontSize = 15.sp,
                                                            fontWeight = FontWeight.Bold,
                                                            textAlign = TextAlign.End,
                                                            modifier = Modifier.clickable {

                                                                isSave = 1
                                                            }
                                                        )
                                                        Text(
                                                            text = "Cancel Vote",
                                                            color = Color.DarkGray,
                                                            fontSize = 15.sp,
                                                            fontWeight = FontWeight.Bold,
                                                            textAlign = TextAlign.End,
                                                            modifier = Modifier.clickable {
                                                                isVoted = ""
                                                            }
                                                        )
                                                    }
                                                }
                                            }

                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        if(isSave == 1){
                            LaunchedEffect(Unit) {
                                try{
                                    val response = RetroInstance.api1.postvote(answerId , Current_User.q_id , Vote(
                                        Current_User.Rollno , isVoted)).body()
                                    if(response?.success == true ){
                                        typeofvote = isVoted
                                    }
                                    else{
                                        typeofvote = "error"
                                        isVoted = ""
                                    }
                                }catch(e: Exception){
                                    typeofvote = "Check Internet"
                                    isVoted = ""
                                }
                                isSave = 0
                            }

                        }
                    }

                }
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .height(120.dp).background(Color.White)
                        .padding(top = 10.dp, end = 10.dp, bottom = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row {
                        CustomMultiLineHintTextField(
                            value = answer,
                            onValueChanged = { answer = it },
                            hintText = "Type your answer",
                            maxLines = 2,
                            modifier = Modifier.width(screenWidth - 100.dp)
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFFFF0E0))
                                .padding(16.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(
                            painter = painterResource(id = R.drawable.send),
                            contentScale = ContentScale.Crop,
                            contentDescription = "Send button",
                            modifier = Modifier.size(45.dp)
                                .offset(0.dp, 30.dp)
                                .clickable {
                                    isSend = 1
                                }
                        )
                    }
                }
            }
        }
        else if(message1 == "Error in the server"){
            Column(
                modifier = Modifier.fillMaxSize()
                    .background(Color.White.copy(0.7f)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = painterResource(id = R.drawable.error),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Error in the server",
                    modifier = Modifier.size(70.dp)
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Error in the server",
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    fontSize = 55.sp,
                    fontWeight = FontWeight.ExtraBold
                )

            }
        }

    }

}