package com.cartoon.boruto.presentation.componants

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cartoon.boruto.R
import com.cartoon.boruto.ui.theme.EXTRA_SMALL_PADDING
import com.cartoon.boruto.ui.theme.LightGray
import com.cartoon.boruto.ui.theme.StarColor

@Composable
fun RatingWidget(
    modifier: Modifier,
    rating: Double,
    scaleFactor: Float = 3f,
    spaceBetween : Dp = EXTRA_SMALL_PADDING
) {
    val result = calculateStar(rating = rating)
    
    val starPathString = stringResource(id = R.string.star_path)
    //path string convert to path object
    val starPath = remember {
        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val startPathBounds = remember {
        starPath.getBounds()
    }
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spaceBetween)
    ){
        result["filledStar"]?.let {
            repeat(it){
                FilledStar(starPath = starPath, starPathBound = startPathBounds, scaleFactor = scaleFactor)
            }
        }
        result["halfFilledStar"]?.let {
            repeat(it){
                HalfFilledStar(starPath = starPath, starPathBound = startPathBounds, scaleFactor = scaleFactor)
            }
        }
        result["emptyStar"]?.let {
            repeat(it){
                EmptyStar(starPath = starPath, starPathBound = startPathBounds, scaleFactor = scaleFactor)
            }
        }
    }
}

@Composable
fun FilledStar(
    starPath: Path,
    starPathBound: Rect,
    scaleFactor: Float
) {
    Canvas(modifier = Modifier.size(24.dp)) {
        val canvasSize = size

        scale(scale = scaleFactor) {
            val pathHeight = starPathBound.height
            val pathWidth = starPathBound.width

            val left = (canvasSize.width / 2f) - (pathWidth / 1.7f)
            val top = (canvasSize.height / 2f) - (pathHeight / 1.7f)

            translate(left = left, top = top) {
                drawPath(
                    path = starPath,
                    color = StarColor
                )
            }
        }

    }
}

@Composable
fun HalfFilledStar(
    starPath: Path,
    starPathBound: Rect,
    scaleFactor: Float
) {
    Canvas(modifier = Modifier.size(24.dp)) {
        val canvasSize = size

        scale(scale = scaleFactor) {
            val pathHeight = starPathBound.height
            val pathWidth = starPathBound.width

            val left = (canvasSize.width / 2f) - (pathWidth / 1.7f)
            val top = (canvasSize.height / 2f) - (pathHeight / 1.7f)

            translate(left = left, top = top) {
                drawPath(
                    path = starPath,
                    color = LightGray.copy(0.5f)
                )
                clipPath(path = starPath) {
                    drawRect(
                        color = StarColor,
                        size = Size(
                            width = starPathBound.maxDimension / 1.7f,
                            height = starPathBound.maxDimension * scaleFactor
                        )
                    )
                }
            }
        }

    }
}

@Composable
fun EmptyStar(
    starPath: Path,
    starPathBound: Rect,
    scaleFactor: Float
) {
    Canvas(modifier = Modifier.size(24.dp)) {
        val canvasSize = size

        scale(scale = scaleFactor) {
            val pathHeight = starPathBound.height
            val pathWidth = starPathBound.width

            val left = (canvasSize.width / 2f) - (pathWidth / 1.7f)
            val top = (canvasSize.height / 2f) - (pathHeight / 1.7f)

            translate(left = left, top = top) {
                drawPath(
                    path = starPath,
                    color = LightGray.copy(0.5f)
                )
            }
        }

    }
}

@Composable
fun calculateStar(rating: Double): Map<String, Int> {
    val maxStars by remember {
        mutableStateOf(5)
    }
    var filledStars by remember { mutableStateOf(0) }
    var halfFilledStar by remember { mutableStateOf(0) }
    var emptyStar by remember { mutableStateOf(0) }
    LaunchedEffect(key1 = rating) {
        val (firstNumber, lastNumber) = rating.toString()
            .split(".")
            .map { it.toInt() }
        if (firstNumber in 0..5 && lastNumber in 0..9) {
            filledStars = firstNumber
            if (lastNumber in 1..5) {
                halfFilledStar++
            }
            if (lastNumber in 6..9) {
                filledStars++
            }
            if (firstNumber == 5 && lastNumber > 0) {
                emptyStar = 5
                filledStars = 0
                halfFilledStar = 0
            }
        } else {
            Log.d("RatingWidget", "Invalid rating number.")
        }
    }
    emptyStar = maxStars - (filledStars + halfFilledStar)
    return  mapOf(
        "filledStar" to filledStars,
        "halfFilledStar" to halfFilledStar,
        "emptyStar" to emptyStar
    )
}

@Composable
@Preview(showBackground = true)
fun FilledStarPreview() {
    val starPathString = stringResource(id = R.string.star_path)
    //path string convert to path object
    val starPath = remember {
        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val startPathBounds = remember {
        starPath.getBounds()
    }
    FilledStar(starPath = starPath, starPathBound = startPathBounds, scaleFactor = 3f)
}

@Composable
@Preview(showBackground = true)
fun HalfFilledStarPreview() {
    val starPathString = stringResource(id = R.string.star_path)
    //path string convert to path object
    val starPath = remember {
        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val startPathBounds = remember {
        starPath.getBounds()
    }
    HalfFilledStar(starPath = starPath, starPathBound = startPathBounds, scaleFactor = 3f)
}

@Composable
@Preview(showBackground = true)
fun EmptyStarPreview() {
    val starPathString = stringResource(id = R.string.star_path)
    //path string convert to path object
    val starPath = remember {
        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val startPathBounds = remember {
        starPath.getBounds()
    }
    EmptyStar(starPath = starPath, starPathBound = startPathBounds, scaleFactor = 3f)
}