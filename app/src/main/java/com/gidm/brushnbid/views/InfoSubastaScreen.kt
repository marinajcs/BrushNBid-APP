package com.gidm.brushnbid.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gidm.brushnbid.R
import com.gidm.brushnbid.navigation.BottomNavBar
import com.gidm.brushnbid.navigation.BottomNavItem
import com.gidm.brushnbid.controllers.SubastaController
import com.gidm.brushnbid.data.SubastaInfo
import java.time.ZonedDateTime
import java.time.ZoneId
import java.time.LocalDateTime
import android.os.CountDownTimer
import androidx.compose.runtime.*
import coil.compose.AsyncImage
import java.io.File
import java.time.Duration
import java.util.Locale

@Composable
fun InfoSubastaScreen(
    subastaId: Int,
    onBack: () -> Unit,
    navController: NavController
) {
    val subastaController = remember { SubastaController() }
    var subasta by remember { mutableStateOf<SubastaInfo?>(null) }
    var tiempoRestante by remember { mutableStateOf(Duration.ZERO) }
    var cantidadPuja by remember { mutableStateOf("") }
    val allFieldsFilled = cantidadPuja.trim().isNotEmpty()

    LaunchedEffect(subastaId) {
        subastaController.getSubastaInfoById(
            id = subastaId,
            onSuccess = { info ->
                subasta = info

                // Si hay fecha de fin, inicia temporizador
                info.fechaFin?.let { fechaFinUtc ->
                    val localFechaFin = parseUtcToLocal(fechaFinUtc)
                    val ahora = LocalDateTime.now()
                    val duracion = Duration.between(ahora, localFechaFin)
                    object : CountDownTimer(duracion.toMillis(), 1000) {
                        override fun onTick(millisUntilFinished: Long) {
                            tiempoRestante = Duration.ofMillis(millisUntilFinished)
                        }

                        override fun onFinish() {
                            tiempoRestante = Duration.ZERO
                        }
                    }.start()
                }
            },
            onError = {
                // Manejar error
            }
        )
    }

    val horas = tiempoRestante.toHours()
    val minutos = tiempoRestante.toMinutes() % 60
    val segundos = tiempoRestante.seconds % 60

    Scaffold(
        containerColor = colorResource(id = R.color.app_background),
        bottomBar = {
            BottomNavBar(
                selectedItem = BottomNavItem.HOME,
                onHomeClick = { navController.navigate("home") },
                onAuctionsClick = { navController.navigate("auctions") },
                onCreateClick = { navController.navigate("addMenu") },
                onNotificationsClick = { navController.navigate("notifications") },
                onProfileClick = { navController.navigate("profile") }
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(colorResource(id = R.color.app_background))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { onBack() }
                )

                Spacer(modifier = Modifier.height(12.dp))

                if (subasta?.image?.isNotBlank() == true && File(subasta!!.image).exists()) {
                    AsyncImage(
                        model = File(subasta!!.image),
                        contentDescription = "Imagen de obra",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .align(Alignment.CenterHorizontally)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = subasta?.obraNombre ?: "Ajedrez de cerámica",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(
                    text = subasta?.vendedor ?: "Autoría",
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.main_color)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Información de la obra
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .border(
                            1.dp,
                            colorResource(id = R.color.main_color),
                            RoundedCornerShape(12.dp)
                        )
                        .clickable {
                            navController.navigate("infoObra/${subasta?.obraId}")
                        }
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Información de la obra",
                        fontSize = 14.sp,
                        color = Color.Black,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowRight,
                        contentDescription = "Más información",
                        tint = colorResource(id = R.color.main_color)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Botones de puja
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.main_color)),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
                        Text(
                            text = subasta?.pujaActual ?: "Puja actual",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }

                    OutlinedTextField(
                        value = cantidadPuja,
                        onValueChange = {
                            if (it.matches(Regex("^\\d{0,7}(\\.\\d{0,2})?$"))) {
                                cantidadPuja = it
                            }
                        },
                        label = {
                            Text("¿Cuánto pujar?", fontSize = 16.sp)
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        textStyle = LocalTextStyle.current.copy(
                            fontSize = 16.sp,
                            lineHeight = 18.sp
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = colorResource(id = R.color.main_color),
                            unfocusedBorderColor = colorResource(id = R.color.main_color),
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        )
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Temporizador
                if (subasta?.fechaFin != null) {
                    Text(
                        text = String.format(
                            Locale.getDefault(),
                            "%02d:%02d:%02d",
                            horas,
                            minutos,
                            segundos
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        fontSize = 16.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = colorResource(id = R.color.main_color)
                    )
                } else {
                    Text(
                        text = "Sin fecha de finalización",
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        fontSize = 16.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    onClick = { /* pujar */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (allFieldsFilled) Color.Black else colorResource(id = R.color.dark_gray)
                    ),
                    enabled = allFieldsFilled
                ) {
                    Text(
                        "Pujar",
                        color = if (allFieldsFilled) Color.White else Color.Black,
                        fontSize = 16.sp,
                        lineHeight = 18.sp
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfoSubastaScreenPreview() {
    val navController = rememberNavController()
    InfoSubastaScreen(1, onBack = {}, navController = navController)
}

fun parseUtcToLocal(utcString: String): LocalDateTime {
    val zonedUtc = ZonedDateTime.parse(utcString)
    return zonedUtc.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime()
}

