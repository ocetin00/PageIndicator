package com.oguzhancetin.page_indicator


import android.util.Log

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by ocetin00 on 6.02.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Home() {
    val state = rememberPageIndicatorState(listOf("First", "Second", "Third", "Fourth"))
    HomeContent(state)
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PageIndicatorPreview(
    pageIndicatorState: PageIndicatorState = rememberPageIndicatorState(
        listOf(
            "First",
            "Second",
            "Third",
            "Fourth"
        )
    )

) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Row() {
            PageIndicator(
                pageIndicatorState = pageIndicatorState,
                indicatorColor = MaterialTheme.colorScheme.primary,
                onIndicatorColor = MaterialTheme.colorScheme.onPrimary,
            )
        }
        Row() {
            Button(onClick = { pageIndicatorState.onPrevious() }) {
                Text("Previous")
            }
            Button(onClick = { pageIndicatorState.onNext() }) {
                Text("Next")
            }

        }

        Text("current page: ${pageIndicatorState.current + 1}")
    }

}

@Composable
fun HomeContent(
    state: PageIndicatorState
) {
    Column(modifier = Modifier.fillMaxSize()) {
        PageIndicator(state)
        Box(contentAlignment = Alignment.BottomCenter) {
            Page(state.current)

            if (state.current != 0)
                Button(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(15.dp),
                    onClick = { state.onPrevious() }) {
                    Text("Previous")
                }

            if (state.current != state.titles.lastIndex && state.current != state.titles.size)
                Button(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(15.dp),
                    onClick = { state.onNext() }) {
                    Text("Next")
                }
            else
                Button(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(15.dp),
                    onClick = { state.onNext() }) {
                    Text("Done")
                }

        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun rememberPageIndicatorState(
    titles: List<String> = listOf("First", "Second", "Third", "Fourth"),
): PageIndicatorState {
    return rememberSaveable(saver = PageIndicatorState.Saver) {
        PageIndicatorState(
            titles
        )
    }
}


class PageIndicatorState(
    val titles: List<String> = listOf("First", "Second", "Third", "Fourth")

) {
    var current by mutableStateOf(0)

    //save data for configuration changes
    companion object {
        val Saver: Saver<PageIndicatorState, *> = mapSaver(
            save = { pageIndicatorState ->
                mapOf(
                    "titles" to pageIndicatorState.titles,
                    "current" to pageIndicatorState.current,

                    )
            },
            restore = {
                PageIndicatorState(
                    titles = it["titles"] as List<String>,
                ).also { pageIndicatorState -> pageIndicatorState.current = it["current"] as Int }
            }
        )
    }


    fun onNext() {
        if (current < titles.size) current++ //prevent negative
        Log.d("current", current.toString())
    }

    fun onPrevious() {
        if (current > 0) current--
    }
}

@Composable
fun PageIndicator(
    pageIndicatorState: PageIndicatorState,
    indicatorColor: Color = MaterialTheme.colorScheme.primary,
    onIndicatorColor: Color = MaterialTheme.colorScheme.onPrimary,
    textColor: Color = Color.Black.copy(alpha = 0.7f)
) {
    Column {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                space = 15.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = onIndicatorColor)
                .padding(vertical = 20.dp, horizontal = 15.dp)

        ) {

            pageIndicatorState.titles.forEachIndexed { index, title ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    val modifier = if (pageIndicatorState.current >= index) {
                        Modifier
                            .size(40.dp)
                            .clip(shape = MaterialTheme.shapes.extraLarge)
                            .background(indicatorColor)
                    } else {
                        Modifier
                            .size(40.dp)
                            .clip(shape = MaterialTheme.shapes.extraLarge)
                            .background(onIndicatorColor)

                    }

                    Surface(
                        shape = MaterialTheme.shapes.extraLarge,
                        shadowElevation = 9.dp, // play with the elevation values
                    ) {
                        Box(
                            modifier = modifier,
                            contentAlignment = Alignment.Center
                        ) {

                            if (pageIndicatorState.current > index) {
                                Icon(
                                    tint = onIndicatorColor,
                                    imageVector = Icons.Filled.Check,
                                    contentDescription = "done"
                                )
                            } else {
                                Text(
                                    "${index + 1}",
                                    color =
                                    if (pageIndicatorState.current == index) {
                                        onIndicatorColor
                                    } else {
                                        textColor
                                    }
                                )
                            }

                        }

                    }
                    AnimatedVisibility(pageIndicatorState.current == index) {

                        Text(
                            modifier = Modifier.padding(start = 12.dp),
                            text = title,
                            color = textColor,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.width(35.dp))
                    }
                }
            }


        }


        PageIndicatorProgressBar(pageIndicatorState, indicatorColor)
    }
}

@Composable
private fun PageIndicatorProgressBar(
    pageIndicatorState: PageIndicatorState,
    indicatorColor: Color
) {
    val percentage by animateFloatAsState(
        targetValue = (pageIndicatorState.current.plus(1).toFloat()
            .div(pageIndicatorState.titles.size))
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(3.dp)
            .background(Color.LightGray)
    ) {
        Box(
            modifier = Modifier
                .height(3.dp)
                .background(indicatorColor)
                .fillMaxWidth(fraction = percentage)
        ) {}
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