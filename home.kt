package com.example.ask_nitt


import androidx.compose.foundation.Image
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavController


@Composable

fun DisplayHome(navController: NavController) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    var isSignIn by remember { mutableStateOf(0) }
    var isLogIn by remember { mutableStateOf(0) }
    var shade by remember {mutableStateOf(0.8f)}

    //var isDashb by remember { mutableStateOf(isDashboard) }
    /*LaunchedEffect(Unit){
        try{
            val response6 = RetroInstance.api1.checkaccess().body()
            if(response6!=null && response6.test[0] !="No user"){
                Current_User.Rollno = response6.test[0]
                Current_User.username = response6.test[1]
                navController.navigate(Screens.DashboardScreen.route)
            }
        }
        catch(e: Exception){
            println(e.message)
        }
    }*/

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = shade)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(modifier = Modifier.offset(10.dp, screenHeight * 2 / 5)) {
                Text(
                    text = "Ask Nittians",
                    color = Color.Yellow,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 35.sp
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Ask Nittians\n" +
                        "Your Friendly College Q&A Platform",
                color = Color.Yellow.copy(0.8f),
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(start = 30.dp, end = 30.dp)
                    .border(
                        width = 2.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(start = 10.dp, top = 10.dp)
            ) {
                Text(
                    text = "Authentication",
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    fontSize = 22.sp
                )
                Spacer(modifier = Modifier.height(60.dp))
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.signup),
                        contentDescription = "signup",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(55.dp)
                            .width(105.dp)
                            .offset(30.dp, 60.dp)
                            .clickable {
                                isSignIn = 1

                            }
                    )
                    Spacer(modifier = Modifier.width(25.dp))
                    Image(
                        painter = painterResource(id = R.drawable.login),
                        contentDescription = "login",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(55.dp)
                            .width(105.dp)
                            .offset(20.dp, 60.dp)
                            .clickable {
                                isLogIn = 1

                            }
                    )
                }


            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight / 3)
                    .offset(0.dp, screenHeight * 1 / 5)
                    .padding(start = 30.dp, bottom = 20.dp)
                    .border(
                        width = 2.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)

            ) {
                Text(
                    text = "About:",
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    fontSize = 28.sp
                )
                Text(
                    text = "✔\uFE0F Post doubts anonymously or with your name  \n" +
                            "✔\uFE0F Get quick answers from seniors \n and peers  \n" +
                            "✔\uFE0F Upvote helpful responses  \n" +
                            "✔\uFE0F Earn reputation by answering!\n",
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    modifier = Modifier.offset(40.dp, screenHeight * 1 / 12)
                )
            }
            Spacer(modifier = Modifier.height(185.dp))
            Text(
                text = "Let's clarify our doubt!!",
                color = Color.Yellow,
                textAlign = TextAlign.Center,
                fontSize = 15.sp
            )

        }

    if (isSignIn == 1) {
        shade = 0.35f
        SignIn(navController)
    }
    else if(isLogIn == 1){
        shade = 0.35f
        LogIn(navController)
    }
}