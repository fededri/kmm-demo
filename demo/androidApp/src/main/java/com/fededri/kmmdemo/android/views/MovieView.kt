package com.fededri.kmmdemo.android.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.fededri.kmmdemo.android.R
import com.fededri.kmmdemo.models.Movie

@Composable
fun MovieView(movie: Movie, onMovieClick: ((Movie) -> Unit)? = null) {
    Card(
        shape = MaterialTheme.shapes.medium,
        backgroundColor = Color(0xFFFF5722).copy(alpha = 0.7f),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable {
                onMovieClick?.invoke(movie)
            }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.padding(horizontal = 4.dp)) {
                Spacer(Modifier.padding(4.dp))
                Image(
                    painter = rememberImagePainter(data = movie.posterPath.orEmpty(),
                        builder = {
                            transformations(CircleCropTransformation())
                            crossfade(true)
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .padding(vertical = 4.dp))
                Spacer(Modifier.padding(4.dp))
            }

            Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)) {
                Text(
                    text = movie.name,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = movie.overview,
                    color = Color.White,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                ConstraintLayout(scoreConstraintSet(), modifier = Modifier.padding(vertical = 8.dp)) {
                    Text(
                        text = "Score:",
                        color = Color.White,
                        modifier = Modifier.layoutId("scoreLabel")
                    )

                    Text(
                        text = movie.voteAverage.toString(),
                        color = Color.White,
                        modifier = Modifier.layoutId("score")
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = null,
                        modifier = Modifier
                            .layoutId("star"),
                        tint = Color.Yellow
                    )
                }
            }
        }
    }
}

private fun scoreConstraintSet() : ConstraintSet {
    return ConstraintSet {
        val scoreLabel = createRefFor("scoreLabel")
        val scorePoints = createRefFor("score")
        val starImage = createRefFor("star")

        constrain(scoreLabel) {
            start.linkTo(parent.start)
        }

        constrain(scorePoints) {
            start.linkTo(scoreLabel.end, margin = 4.dp)
        }

        constrain(starImage) {
            start.linkTo(scorePoints.end, margin = 4.dp)
            height = Dimension.fillToConstraints
            top.linkTo(scoreLabel.top)
            bottom.linkTo(scoreLabel.bottom)
        }
    }
}

@Preview(widthDp = 400, heightDp = 120)
@Composable
fun MoviePreview(){
    MovieView(movie = Movie())
}