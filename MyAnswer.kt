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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Myanswer(navController: NavController) {
    var answer by remember { mutableStateOf<List<PostedAns>>(mutableListOf()) }
    var message by remember { mutableStateOf("...Loading") }
    LaunchedEffect(Unit) {
        try {
            val Responseans = RetroInstance.api1.getanswer(Current_User.q_id).body()
            if (Responseans?.success == true) {
                answer = Responseans.answers
                message = ""
            } else {
                message = "Error in the Server"
            }
        } catch (e: Exception) {
            message = "Check Internet Connection"
        }
    }
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
                        navController.navigate(Screens.QuestionsScreen.route)
                    }
            )
            Spacer(modifier = Modifier.width(25.dp))
            androidx.compose.material3.Text(
                text = "Question " + Current_User.qno.toString(),
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 35.sp,
                textAlign = TextAlign.Start
            )

        }
        Spacer(modifier = Modifier.height(3.dp))
        Box(
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight()
                .background(
                    color = Color(0xFFFFFBEA),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(16.dp)
        ) {
            Text(
                text = Current_User.question,
                fontSize = 17.sp,
                lineHeight = 22.sp,
                color = Color(0xFF78350F),
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.height(9.dp))
        if (message == "") {
            LazyColumn {
                itemsIndexed(
                    answer
                ) { index, ans ->
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
                                        text = "\uD83D\uDC4D ${ans.upvotes}   \uD83D\uDC4E ${ans.downvotes}",
                                        color = Color.DarkGray,
                                        fontSize = 12.sp,
                                        fontStyle = FontStyle.Normal,
                                        textAlign = TextAlign.End
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                }
            }
        } else if (message == "Error in the Server") {
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
