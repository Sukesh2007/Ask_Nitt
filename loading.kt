package com.example.ask_nitt

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun load(mod: Modifier,text: String){
    var startAngle: Float by remember { mutableStateOf(0f) }
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    LaunchedEffect(Unit) {
        while(true){
            startAngle = startAngle + 5f
            delay(10L)
        }
    }
    Column(modifier = mod.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Canvas(
            modifier = androidx.compose.ui.Modifier
        ) {
            drawArc(
                color = Color.Blue,
                startAngle = startAngle,
                sweepAngle = 80f,
                useCenter = false,
                size = Size(100f, 100f),
                topLeft = _root_ide_package_.androidx.compose.ui.geometry.Offset(-20f , -120f),
                style = Stroke(
                    width = 3.dp.toPx()
                )

            )
            drawArc(
                color = Color.Blue,
                startAngle = startAngle + 100f,
                sweepAngle = 80f,
                useCenter = false,
                size = Size(100f, 100f),
                topLeft = _root_ide_package_.androidx.compose.ui.geometry.Offset(-20f , -120f),
                style = Stroke(
                    width = 3.dp.toPx()
                )
            )
            drawArc(
                color = Color.Blue,
                startAngle = startAngle + 190f,
                sweepAngle = 80f,
                useCenter = false,
                size = Size(100f, 100f),
                topLeft = _root_ide_package_.androidx.compose.ui.geometry.Offset(-20f , -120f),
                style = Stroke(
                    width = 3.dp.toPx()
                )
            )
            drawArc(
                color = Color.Blue,
                startAngle = startAngle + 290f,
                sweepAngle = 80f,
                useCenter = false,
                size = Size(100f, 100f),
                topLeft = _root_ide_package_.androidx.compose.ui.geometry.Offset(-20f , -120f),
                style = Stroke(
                    width = 3.dp.toPx()
                )
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = text,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp)
    }
}