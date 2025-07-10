package com.example.ask_nitt

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button

import androidx.compose.material.Scaffold // ✅ Material 2
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.FilledTonalButton

import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalConfiguration
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavController
import androidx.compose.material3.ButtonDefaults

@Composable
fun dispplayDashboard(navController: NavController){
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    var message by remember { mutableStateOf("...loading")}
    var color by remember { mutableStateOf(Color.Black)}
    var isLogOut by remember { mutableStateOf(0) }
    val scaffoldState = rememberScaffoldState()
    var question by remember { mutableStateOf("")}
    var isSend by remember { mutableStateOf(0)}
    val scope = rememberCoroutineScope()
    var tag by remember {mutableStateOf("")}
    var type by remember {mutableStateOf(0)}

    if((isLogOut == 0 || message == "Error in the server" || message == "Check Internet Connection" || message == "...loading")) {


        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState
        ) {paddingValues ->

            LaunchedEffect(type) {

                    scope.launch {
                        when (type) {
                            1 -> scaffoldState.snackbarHostState.showSnackbar("Sent Successfully")
                            2 -> scaffoldState.snackbarHostState.showSnackbar("Error in the server")
                            3 -> scaffoldState.snackbarHostState.showSnackbar("Check Internet Connection")
                        }
                        type = 0
                    }

            }
            Column(
                modifier = Modifier.fillMaxSize().background(Color.Cyan)
                    .padding(start = 20.dp , top = 40.dp, bottom = 20.dp)
            ) {
                Text(
                    text = "Profile",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(3.dp))
                Box(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(8.dp),
                            clip = false
                        )
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                        .height(235.dp)
                        .padding(start = 12.dp, top = 12.dp)
                )
                {
                    Column {
                        Text(
                            text = "Name:     ${Current_User.username}",
                            color = Color.Black,
                            fontSize = 27.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Start
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Roll_no:   ${Current_User.Rollno}",
                            color = Color.Black,
                            fontSize = 27.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Start
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Row {
                            /*Image(
                                painter = painterResource(id = R.drawable.logout),
                                contentScale = ContentScale.Crop,
                                contentDescription = "Log Out",
                                modifier = Modifier.size(45.dp)
                                    .clickable{
                                        isLogOut = 1
                                        message = "...loading"
                                        color = Color.Black
                                    }
                            )*/
                            FilledTonalButton(
                                onClick = {
                                    isLogOut = 1
                                    message = "...loading"
                                    color = Color.Black
                                },
                                colors  = ButtonDefaults.filledTonalButtonColors(
                                    containerColor = Color(0xFF607D8B), // deep gray-blue
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(20.dp)
                            ) {
                                Text(text = "Log Out")
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                        }
                        Spacer(modifier = Modifier.height(25.dp))
                        if (isLogOut == 1) {
                            if(message == "" || message == "Check Internet Connection"){
                                load(Modifier , "...logging Out")
                            }
                            else {
                                Text(
                                    text = message,
                                    color = color,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Normal,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Features",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(3.dp))
                Box(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(8.dp),
                            clip = false
                        )
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                        .height(130.dp)
                        .padding(start = 12.dp, top = 12.dp)
                ){
                    Row{
                        Column{
                            Image(
                                painter = painterResource( id = R.drawable.doubt),
                                contentScale = ContentScale.Crop,
                                contentDescription = "My Doubt",
                                modifier = Modifier.size(45.dp)
                                    .clickable{
                                        navController.navigate(Screens.QuestionsScreen.route)
                                    }
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = "My Doubt Section",
                                color = Color.Black,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                        Spacer(modifier = Modifier.width(130.dp))
                        Column{
                            Image(
                                painter = painterResource( id = R.drawable.clarify),
                                contentScale = ContentScale.Crop,
                                contentDescription = "Clarify",
                                modifier = Modifier.size(45.dp)
                                    .clickable{
                                        navController.navigate(Screens.OtherQAns.route)
                                    }
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = "Clarify doubts",
                                color = Color.Black,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Post your doubt",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(3.dp))
                Box(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(8.dp),
                            clip = false
                        )
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(start = 12.dp, top = 12.dp,end = 12.dp)
                ){
                    Column {
                        Text(
                            text = "Question",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 27.sp
                        )
                        Spacer(modifier= Modifier.height(3.dp))
                        CustomMultiLineHintTextField(
                            value = question,
                            onValueChanged = { question = it },
                            hintText = "Type your doubt\n And Clarify it",
                            modifier = Modifier.fillMaxWidth()
                                .height(120.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.LightGray)
                                .padding(16.dp)
                        )
                        Text(
                            text = "Tag",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 27.sp
                        )
                        Spacer(modifier= Modifier.height(3.dp))
                        CustomMultiLineHintTextField(
                            value = tag,
                            onValueChanged = { tag = it },
                            hintText = "Type your tags here\n With comma seperation",
                            modifier = Modifier.fillMaxWidth()
                                .height(65.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.LightGray)
                                .padding(16.dp),
                            maxLines = 2
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        /*Button(
                            onClick = {
                                scope.launch{
                                    when{
                                        question == "" -> scaffoldState.snackbarHostState.showSnackbar("Enter the question")
                                        tag == "" -> scaffoldState.snackbarHostState.showSnackbar("Enter the tag")
                                        else -> isSend = 1
                                    }
                                }
                            }
                        ) {
                            Text("Send")
                        }*/
                        FilledTonalButton(
                            onClick = { scope.launch{
                                when{
                                    question == "" -> scaffoldState.snackbarHostState.showSnackbar("Enter the question")
                                    tag == "" -> scaffoldState.snackbarHostState.showSnackbar("Enter the tag")
                                    else -> isSend = 1
                                }
                            } },
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = Color(0xFFFB8C00),
                                contentColor = Color.White
                            )
                        ) {
                            Text("Send")
                        }


                    }
                }
            }

        }
    }
    if(isLogOut == 1){
        LaunchedEffect(Unit) {
            try{
                val response = RetroInstance.api1.loggingout(LogOut(Current_User.Rollno)).body()
                if(response?.status == "logout"){
                    message = ""
                    navController.navigate(Screens.MainScreen.route)

                }
                else{
                    message = "Error in the server"
                    color = Color.Red
                }
            }
            catch(e: Exception){
                message = "Check Internet Connection"
                color = Color.Red

            }
        }
        if(message == "Check Internet Connection" || message == "Error in the server"){
            isLogOut = 0
            navController.navigate(Screens.MainScreen.route)
        }


    }
    else if(isSend == 1){
        LaunchedEffect(Unit) {
            try{
                val SentResponse = RetroInstance.api1.postQuestion(Question(question , tag ,
                    Current_User.username , Current_User.Rollno)).body()
                if(SentResponse?.success == true){
                    type = 1
                }
                else{
                    type = 2
                }
            }catch(e: Exception){
                type = 3
            }
            question = ""
            tag = ""
            isSend = 0
        }
    }
}

/*
Image(
                painter = painterResource(id = R.drawable.logout),
                contentScale = ContentScale.Crop,
                contentDescription = "Log Out",
                modifier = Modifier.size(45.dp)
                    .offset(screenWidth - 135.dp, 30.dp)
                    .clickable {
                        isLogOut = 1
                    }
            )

            Text(
                text = "LogOut",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier.offset(screenWidth - 70.dp, 30.dp)
            )
            if (isLogOut == 1) {
                Text(
                    text = message,
                    color = color,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                        .offset(0.dp, screenHeight - 40.dp)
                )
            }
*/