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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
fun OtherAns(navController: NavController) {
    var message by remember { mutableStateOf("...Loading") }
    var otherquestion by remember { mutableStateOf<List<OtherQuestion>>(mutableListOf())}
    LaunchedEffect(Unit) {
        try{
            val Responseo_ans = RetroInstance.api1.getotherQue(Current_User.Rollno).body()
            if(Responseo_ans?.success == true){
                otherquestion = Responseo_ans.Other_data
                message = ""
            }
            else{
                message = "Error in the Server"
            }
        }catch(e: Exception){
            message = "No Internet Connection"
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
                        navController.navigate(Screens.DashboardScreen.route)
                    }
            )
            Spacer(modifier = Modifier.width(25.dp))
            androidx.compose.material3.Text(
                text = "Others Doubt Section",
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 25.sp,
                textAlign = TextAlign.Start
            )

        }
        if (otherquestion.size != 0) {
            Box(
                modifier = Modifier.fillMaxSize()
                    .background(Color(0xFFF5F5F5))
                    .padding(10.dp)
            ) {
                LazyColumn {
                    itemsIndexed(
                        otherquestion
                    ) { index, message ->
                        var tag by remember { mutableStateOf("") }
                        for (i in 0 until message.tags.size) {
                            tag = tag + message.tags[i] + " "
                        }
                        Box(
                            modifier = Modifier.fillMaxWidth()
                                .shadow(8.dp, RoundedCornerShape(12.dp))
                                .background(Color(0xFFE3F2FD))
                                .padding(14.dp)
                                .wrapContentHeight()
                                .clickable{
                                    Current_User.qno = index + 1
                                    Current_User.question = message.QueText
                                    Current_User.q_id = message.q_id
                                    Current_User.otherqn_roll = message.postedrollno
                                    Current_User.otherqn_username = message.postedUsername
                                    navController.navigate(Screens.AnsVoteScreen.route)
                                }
                        ) {
                            Column {
                                Text(
                                    text = "Question ${index + 1}",
                                    color = Color(0xFF0D47A1),
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Start
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = message.QueText,
                                    fontSize = 20.sp,
                                    color = Color(0xFF0D47A1),
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Start
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    text = "Tags: $tag",
                                    color = Color(0xFF1565C0),
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.End
                                )
                                Text(
                                    text = "Date and time: ${message.timestamp[0]} ${message.timestamp[1]}",
                                    color = Color(0xFF757575),
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.End
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(modifier = Modifier.fillMaxWidth()
                                    .padding(end = 10.dp),
                                    horizontalArrangement = Arrangement.End){
                                    Text(
                                        text = "---" + message.postedUsername + "(${message.postedrollno})",
                                        color = Color.Black,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )

                                }

                                Spacer(modifier = Modifier.height(10.dp))


                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        } else if (message == "...Loading" && otherquestion.size == 0) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = message,
                    color = Color.Black,
                    fontSize = 65.sp,
                    fontWeight = FontWeight.Black
                )
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

        } else if (message == "Check the Internet Connection") {
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
                    text = "No Internet Connection",
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    fontSize = 55.sp,
                    fontWeight = FontWeight.ExtraBold
                )

            }
        }
    }
}