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
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

/**
 * Created by ocetin00 on 6.02.2023
 */
@Preview
@Composable
fun Home() {
    val state = PageIndicatorState(listOf("First", "Second", "Third", "Fourth"))
    Screen(state)
}

@Composable
fun Screen(state: PageIndicatorState) {
    Column(modifier = Modifier.fillMaxSize()) {
        PageIndicator(state)
        Box(contentAlignment = Alignment.BottomCenter) {
            Page(state.current)

            if (state.current != 0)
                Button(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(15.dp),
                    onClick = { state.onPreviousClick() }) {
                    Text("Previous")
                }

            if (state.current != state.titles.lastIndex && state.current != state.titles.size)
                Button(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(15.dp),
                    onClick = { state.onNextClick() }) {
                    Text("Next")
                }

            else
                Button(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(15.dp),
                    onClick = { state.onNextClick() }) {
                    Text("Done")
                }

        }
    }
}

class PageIndicatorState(
    val titles: List<String> = listOf("First", "Second", "Third", "Fourth"),
) {
    var current by mutableStateOf(0)

    /* companion object {
         */
    /**
     * The default [Saver] implementation for [TopAppBarState].
     *//*
        val Saver: Saver<PageIndicatorState, *> = listSaver(
            save = { listOf(it.current) },
            restore = {

                PageIndicatorState(
                    initialHeightOffsetLimit = it[0],
                    initialHeightOffset = it[1],
                    initialContentOffset = it[2]
                )
            }
        )
    }*/

    fun onNextClick() {
        if (current < titles.size) current++
        Log.d("current", current.toString())
    }

    fun onPreviousClick() {
        if (current > 0) current--
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

                    val modifier = if (pageIndicatorState.current >= index) {
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

                            if (pageIndicatorState.current > index) {
                                Icon(
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    imageVector = Icons.Filled.Check,
                                    contentDescription = "done"
                                )
                            } else {
                                Text(
                                    "${index + 1}",
                                    color =
                                    if (pageIndicatorState.current == index) {
                                        MaterialTheme.colorScheme.onPrimary
                                    } else {
                                        Color.Gray
                                    }
                                )
                            }

                        }

                    }
                    AnimatedVisibility(pageIndicatorState.current == index) {

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
            targetValue = (pageIndicatorState.current.plus(1).toFloat()
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