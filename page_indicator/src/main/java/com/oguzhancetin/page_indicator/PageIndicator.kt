package com.oguzhancetin.page_indicator


import android.util.Log

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
import kotlinx.coroutines.launch

/**
 * Created by ocetin00 on 6.02.2023
 */
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


@Stable
class PageIndicatorState(
    val titles: List<String> = listOf("First", "Second", "Third", "Fourth","Fifth")
) {
    var current = mutableStateOf(0)

    fun onNextClick() {
        if (current.value < titles.size) current.value++
        Log.d("c", current.value.toString())
    }

    fun onPreviousClick() {
        if (current.value > 0) current.value--
    }
}

@Composable
fun PageIndicator(pageIndicatorState: PageIndicatorState) {
    Column {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                space = 15.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(vertical = 20.dp, horizontal = 15.dp)

        ) {

            pageIndicatorState.titles.forEachIndexed { index, title ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    val modifier = if (pageIndicatorState.current.value >= index) {
                        Modifier
                            .size(40.dp)
                            .clip(shape = MaterialTheme.shapes.extraLarge)
                            .background(MaterialTheme.colorScheme.primary)
                    } else {
                        Modifier
                            .size(40.dp)
                            .clip(shape = MaterialTheme.shapes.extraLarge)
                            .background(Color.White)

                    }
                    Surface(
                        shape = MaterialTheme.shapes.extraLarge,
                        shadowElevation = 9.dp, // play with the elevation values
                    ) {
                        Box(
                            modifier = modifier,
                            contentAlignment = Alignment.Center
                        ) {

                            if (pageIndicatorState.current.value > index) {
                                Icon(
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    imageVector = Icons.Filled.Check,
                                    contentDescription = "done"
                                )
                            } else {
                                Text(
                                    "${index + 1}",
                                    color =
                                    if (pageIndicatorState.current.value == index) {
                                        MaterialTheme.colorScheme.onPrimary
                                    } else {
                                        Color.Gray
                                    }
                                )
                            }

                        }

                    }
                    AnimatedVisibility(pageIndicatorState.current.value == index) {

                        Text(
                            modifier = Modifier.padding(start = 12.dp),
                            text = title,
                            color = Color.Black.copy(alpha = 0.7f),
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.width(35.dp))
                    }
                }
            }


        }


        val percentage by animateFloatAsState(
            targetValue = (pageIndicatorState.current.value.plus(1).toFloat()
                .div(pageIndicatorState.titles.size))
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(Color.Gray)
        ) {
            Box(
                modifier = Modifier
                    .height(3.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxWidth(fraction = percentage)
            ) {}
        }
    }
}


@Preview
@Composable
fun PageIndicatorPreview() {
    val state = PageIndicatorState(listOf("First", "Second", "Third", "Fourth"))
    PageIndicator(state)
}
