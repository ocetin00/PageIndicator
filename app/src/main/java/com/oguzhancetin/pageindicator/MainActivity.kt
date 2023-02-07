package com.oguzhancetin.pageindicator

import android.content.res.Resources.Theme
import com.oguzhancetin.pageindicator.ui.theme.blue
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oguzhancetin.page_indicator.PageIndicator
import com.oguzhancetin.page_indicator.PageIndicatorState
import com.oguzhancetin.pageindicator.ui.theme.PageIndicatorTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PageIndicatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Home()
                }
            }
        }
    }
}

@Preview
@Composable
fun Home() {
    Column(modifier = Modifier.fillMaxSize()) {
        var scope = rememberCoroutineScope()
        val state = PageIndicatorState(listOf("First", "Second", "Third", "Fourth"))
        PageIndicator(state)

        Box(contentAlignment = Alignment.BottomCenter) {
            Page(state.current.value + 1)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 25.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = { scope.launch { state.onPreviousClick() } }) {
                    Text("Previous")
                }

                if (state.current.value == state.titles.lastIndex) {
                    Button(onClick = { scope.launch { state.onNextClick() } }) {
                        Text("Done")
                    }
                } else {
                    Button(onClick = { scope.launch { state.onNextClick() } }) {
                        Text("Next")
                    }
                }

            }

        }
    }


}

@Composable
fun Page(page: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("form$page", fontSize = 20.sp)
    }
}




