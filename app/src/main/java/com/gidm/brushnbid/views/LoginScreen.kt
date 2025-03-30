package com.gidm.brushnbid.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gidm.brushnbid.R

@Composable
fun LoginScreen(
    onCreateAccount: () -> Unit,
    onLogin: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.app_background))
            .padding(12.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Image(
            painter = painterResource(id = R.drawable.login_img),
            contentDescription = "Ilustración login",
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .offset(y = (-40).dp)
        )

        Column (
            modifier = Modifier
                .offset(y = (-16).dp)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "Regístrate o inicia sesión",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "¡Te estamos esperando!",
                fontSize = 25.sp,
                color = Color(0xFFBC5228)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier.offset(y = (-16).dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onCreateAccount,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.main_color)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Crear cuenta", color = Color.White)
                }

                Button(
                    onClick = onLogin,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.main_light_color)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Inicia sesión", color = colorResource(id = R.color.main_color))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onCreateAccount = {},
        onLogin = {}
    )
}

