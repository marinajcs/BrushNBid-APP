package com.gidm.brushnbid.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gidm.brushnbid.R
import com.gidm.brushnbid.data.ObraSummary
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.tooling.preview.Preview
import com.gidm.brushnbid.data.Estado

@Composable
fun SelectObraScreen(
    onBack: () -> Unit,
    onObraSelected: (ObraSummary) -> Unit = {}
) {

    val imgid = R.drawable.print_art
    val misObras = listOf(
        ObraSummary(1, "Ajedrez", Estado.ACTIVA, imgid),
        ObraSummary(2, "Taza de cerámica", Estado.ACTIVA, imgid),
        ObraSummary(3, "Jarrón de cerámica", Estado.ACTIVA, imgid)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.app_background))
            .padding(16.dp)
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
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Seleccione una obra",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(36.dp))

        Text(
            text = "Mis obras",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 8.dp, bottom = 12.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(misObras) { obra ->
                ObraCardItem(obra = obra) {
                    onObraSelected(obra)
                }
            }
        }
    }
}

@Composable
fun ObraCardItem(obra: ObraSummary, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = obra.imagen),
            contentDescription = obra.titulo,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp))
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = obra.titulo,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        Text(
            text = obra.estado.name,
            color = colorResource(R.color.main_color),
            fontSize = 12.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SelectObraScreenPreview() {
    SelectObraScreen(onBack = {})
}
