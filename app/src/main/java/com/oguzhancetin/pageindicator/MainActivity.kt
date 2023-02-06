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
    Column {
        val state = PageIndicatorState(listOf("First", "Second", "Third", "Fourth"))
        PageIndicator(state)
        var scope = rememberCoroutineScope()
        Button(onClick = { scope.launch { state.onNextClick() } }) {
            Text("next")
        }
        Button(onClick = { scope.launch { state.onPreviousClick() } }) {
            Text("previos")
        }

    }
}




