package com.gidm.brushnbid.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gidm.brushnbid.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.items
import com.gidm.brushnbid.controllers.SubastaController
import com.gidm.brushnbid.data.PujaInfo
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import com.gidm.brushnbid.util.formatFecha
import com.gidm.brushnbid.util.parseUtcToLocal


@Composable
fun HistorialScreen(
    subastaId: Int,
    onBack: () -> Unit
) {
    var pujas by remember { mutableStateOf(listOf<PujaInfo>()) }
    val subastaController = remember { SubastaController() }

    LaunchedEffect(Unit) {
        subastaController.getHistorialPujas(
            subastaId,
            onSuccess = { lista ->
                pujas = lista
            },
            onError = { }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.app_background))
            .padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver",
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onBack() }
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Historial de pujas",
            fontSize = 20.sp,
            lineHeight = 22.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 8.dp, bottom = 12.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(pujas) { puja ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = puja.username,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                lineHeight = 18.sp
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = formatFecha(parseUtcToLocal(puja.fecha).toString()),
                                fontSize = 12.sp,
                                lineHeight = 14.sp,
                                color = Color.Gray
                            )
                        }
                        Text(
                            text = "${puja.cantidad}€",
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            lineHeight = 18.sp,
                            color = colorResource(id = R.color.main_color)
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HistorialScreenPreview() {
    HistorialScreen(
        1,
        onBack = {}
    )
}



