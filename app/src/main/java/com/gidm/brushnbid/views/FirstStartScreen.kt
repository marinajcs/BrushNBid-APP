package com.gidm.brushnbid.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gidm.brushnbid.R

@Composable
fun FirstStartScreen(onContinue: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.app_background))
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Subasta,\npuja y gana:",
                fontSize = 55.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                lineHeight = 58.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "da valor a tu arte y llévatelo al mejor\npostor",
                fontSize = 30.sp,
                color = colorResource(id = R.color.main_color),
                lineHeight = 33.sp
            )
            Spacer(modifier = Modifier.height(24.dp))

            Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                    Tag("Ilustraciones", colorResource(id = R.color.main_color))
                    Tag("Pinturas", colorResource(id = R.color.main_light_color))
                }
                Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                    Tag("Esculturas", colorResource(id = R.color.main_light_color))
                    Tag("NFTs de Arte digital", colorResource(id = R.color.main_color))
                }
                Row {
                    Tag("Fotografías exclusivas", colorResource(id = R.color.main_color))
                }
            }
        }

        Button(
            onClick = onContinue,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Descubre obras únicas", color = Color.White)
        }
    }
}

@Composable
fun Tag(text: String, bgColor: Color) {
    val textColor = if (bgColor == colorResource(id = R.color.main_color)) Color.White else colorResource(id = R.color.main_color)

    Box(
        modifier = Modifier
            .background(bgColor, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FirstStartScreenPreview() {
    FirstStartScreen{}
}