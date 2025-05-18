package com.gidm.brushnbid.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gidm.brushnbid.R
import com.gidm.brushnbid.data.Estado
import com.gidm.brushnbid.data.Obra
import com.gidm.brushnbid.data.ObraSummary
import com.gidm.brushnbid.navigation.BottomNavBar
import com.gidm.brushnbid.navigation.BottomNavItem

@Composable
fun ProfileScreen(navController: NavController) {
    var selectedItem by remember { mutableStateOf(BottomNavItem.NOTIFICATIONS) }

    val imgid = R.drawable.print_art
    val misObras = listOf(
        ObraSummary("Ajedrez", Estado.ACTIVA, imgid),
        ObraSummary("Taza de cerámica", Estado.ACTIVA, imgid),
        ObraSummary("Jarrón de cerámica", Estado.ACTIVA, imgid),
        ObraSummary("Platos de cerámica", Estado.VENDIDA, imgid)
    )

    Scaffold(
        containerColor = colorResource(id = R.color.app_background),
        bottomBar = {
            BottomNavBar(
                selectedItem = BottomNavItem.PROFILE,
                onHomeClick = { navController.navigate("home") },
                onAuctionsClick = { navController.navigate("auctions") },
                onCreateClick = { /* lógica para crear */ },
                onNotificationsClick = { navController.navigate("notifications") },
                onProfileClick = { navController.navigate("profile") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(colorResource(R.color.app_background))

        ) {
            HeaderProfile(
                colorResource(R.color.light_blue),
                R.drawable.profile_pic,
                "Marinajcs",
                "Marina Jun Carranza Sánchez"
            )

            Spacer(modifier = Modifier.padding(9.dp))

            Text(
                text = "Mis obras",
                fontSize = 20.sp,
                lineHeight = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 25.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 100.dp)
            ) {
                items(misObras) { obra ->
                    ObraCard(obra)
                }
            }

        }
    }
}

@Composable
fun ObraCard(obra: ObraSummary) {
    Column(
        modifier = Modifier
            .width(160.dp)
    ) {
        Image(
            painter = painterResource(id = obra.imagen),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(190.dp)
                .clip(MaterialTheme.shapes.medium)
        )
        Text(
            text = obra.titulo,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.padding(all = 2.dp)
        )
        Text(
            text = obra.estado.name,
            fontSize = 12.sp,
            color = if (obra.estado == Estado.VENDIDA) Color.Gray else colorResource(R.color.main_color)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){
    val navController = rememberNavController()
    ProfileScreen(navController)
}