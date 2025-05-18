package com.gidm.brushnbid.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gidm.brushnbid.R

@Composable
fun HeaderImage(color: Color, imgId: Int, txt1: String, txt2: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(colorResource(R.color.app_background))
    ) {
        Box(
            modifier = Modifier
                .height(170.dp)
                .fillMaxWidth(0.9f) // hueco a la derecha
                .background(
                    color = color,
                    shape = RoundedCornerShape(topEnd = 100.dp, bottomEnd = 100.dp)
                )
                .align(alignment = Alignment.BottomStart)

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.
                        fillMaxHeight()
                        .padding(top = 10.dp)
                ) {
                    Text(
                        text = txt1,
                        color = Color.White,
                        fontSize = 24.sp,
                        lineHeight = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = txt2,
                        color = Color.White,
                        fontSize = 24.sp,
                        lineHeight = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Image(
                    painter = painterResource(id = imgId),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(500.dp)
                        .width(500.dp)
                        .offset(y = (-15).dp)
                )
            }
        }
    }
}

@Composable
fun HeaderProfile(color: Color, imgId: Int, username: String, fullname: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(colorResource(R.color.app_background))
    ) {
        Box(
            modifier = Modifier
                .height(170.dp)
                .fillMaxWidth(0.9f)
                .background(
                    color = color,
                    shape = RoundedCornerShape(topEnd = 100.dp, bottomEnd = 100.dp)
                )
                .align(alignment = Alignment.BottomStart)

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .width(160.dp)
                        .fillMaxHeight()
                        .padding(top = 10.dp)
                ) {
                    Text(
                        text = username,
                        color = Color.White,
                        fontSize = 24.sp,
                        lineHeight = 26.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(
                        text = fullname,
                        color = Color.White,
                        fontSize = 14.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                }

                Image(
                    painter = painterResource(id = imgId),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderImagePreview() {
    val color = colorResource(R.color.main_color)
    HeaderImage(color, R.drawable.mis_subastas, "Mis", "Subastas")
}

@Preview(showBackground = true)
@Composable
fun HeaderProfilePreview() {
    val color = colorResource(R.color.light_blue)
    HeaderProfile(color, R.drawable.profile_pic, "Marinajcs", "Marina Jun Carranza Sánchez")
}
