package com.fededri.kmmdemo.android.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.fededri.kmmdemo.android.R
import com.fededri.kmmdemo.models.Movie

@Composable
fun MovieView(movie: Movie) {
    Card(
        shape = MaterialTheme.shapes.medium,
        backgroundColor = Color.Red.copy(alpha = 0.5f),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.padding(horizontal = 4.dp)) {
                Spacer(Modifier.padding(4.dp))
                Image(
                    painter = rememberImagePainter(data = movie.posterPath!!,
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

            Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
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
            }
        }
    }
}