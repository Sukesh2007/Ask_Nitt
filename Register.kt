package com.example.ask_nitt

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.changedToUp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SignIn(navController: NavController){
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    var isClose by remember {mutableStateOf(0)}
    var regname by remember {mutableStateOf("")}
    var regpassword by remember {mutableStateOf("")}
    var registering by remember { mutableStateOf(0)}
    var rno by remember { mutableStateOf("")}
    var message by remember{ mutableStateOf("")}
    var color by remember { mutableStateOf<Color>(Color.White) }




    if(isClose == 0){
        Box(modifier = Modifier.fillMaxSize()
            .pointerInput(Unit){
                awaitPointerEventScope {
                    while(true){
                        val event = awaitPointerEvent()
                        val down = event.changes.firstOrNull() ?: continue
                        val position = down.position
                        val xDp = with(density) { position.x.toDp() }
                        val yDp = with(density) { position.y.toDp() }
                        if(down.changedToUp()) {
                            if(!(xDp in 20.dp..screenWidth-20.dp && yDp in screenWidth*2/7-60.dp..screenWidth*2/7 + 460.dp)){
                                isClose = 1
                            }
                        }
                    }
                }
            }
            ) {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .height(360.dp)
                    .padding(start = 20.dp, end = 20.dp)
                    .offset(0.dp, screenHeight * 2 / 7 - 60.dp)
                    .padding(10.dp)
                    .background(color = Color.Black,
                        shape = RoundedCornerShape(12.dp))
                    .border(
                        width = 2.dp,
                        color = Color.Yellow,
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                Column{
                    Row(modifier = Modifier.height(60.dp)
                        .padding(start = 10.dp,top = 8.dp , end = 10.dp)) {
                        Text(
                            text = "Username:",
                            color = Color.White,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Normal,
                            fontSize = 25.sp
                            )
                        Spacer(modifier = Modifier.width(7.dp))
                        TextField(
                            value = regname,
                            label = {Text("Enter name")},
                            onValueChange = {regname = it},
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                                .padding(start = 10.dp , end = 10.dp)
                        )
                    }
                    Spacer(modifier =Modifier.height(10.dp))
                    Row(modifier = Modifier.height(60.dp)
                        .padding(start = 10.dp, end = 10.dp)) {
                        Text(
                            text = "Password:",
                            color = Color.White,
                            fontSize = 25.sp,
                            fontWeight = Normal,
                            textAlign = TextAlign.Start
                        )
                        Spacer(modifier = Modifier.width(7.dp))
                        TextField(
                            value = regpassword,
                            label = { Text("Enter password")},
                            onValueChange = { regpassword = it},
                            singleLine = true,
                            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                                .fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(modifier =Modifier.height(60.dp)
                        .padding(start = 39.dp, end = 10.dp)){
                        Text(
                            text = "Roll no:",
                            color = Color.White,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Start
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        TextField(
                            value = rno,
                            label = { Text("Enter 9 digits")},
                            onValueChange = { rno = it},
                            singleLine = true,
                            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                                .fillMaxWidth()
                        )
                    }
                    OutlinedButton(
                        onClick = {
                              registering = 1
                        },
                                modifier = Modifier.padding(start = 20.dp)

                    ){
                        Text(
                            text = "Register",
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = message,
                            color = color,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start
                        )

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "  ☢\uFE0F close: Tap outside!",
                        color = Color.Yellow,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp
                    )
                }
            }
        }
        if(registering == 1){

                LaunchedEffect(Unit) {
                    try{
                        val response1 = RetroInstance.api1.postUser(Users(regname , regpassword , rno)).body()
                        if(response1?.state == "User Added"){
                            message = "$rno added successfully"
                            color = Color.Yellow

                        }
                        else{
                            message = response1?.state.toString()
                            color = Color.Red
                        }
                    }
                    catch(e: Exception){
                        message = "${e.message}"
                        color = Color.White
                    }
                    regname = ""
                    regpassword = ""
                    rno = ""
                    if(rno == "") {
                        registering = 0
                    }

                }

        }


    }
    else{
        DisplayHome(navController)
    }

}