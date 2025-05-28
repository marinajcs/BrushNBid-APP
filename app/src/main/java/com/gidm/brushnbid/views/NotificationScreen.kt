package com.gidm.brushnbid.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gidm.brushnbid.R
import com.gidm.brushnbid.data.Notificacion
import com.gidm.brushnbid.navigation.BottomNavBar
import com.gidm.brushnbid.navigation.BottomNavItem

@Composable
fun NotificationScreen(navController: NavController) {
    val notificaciones = listOf(
        Notificacion(
            icon = Icons.Default.Circle,
            title = "BrushNbid",
            description = "Consejos para transacciones seguras...",
            message = "Marina Carranza, tu seguridad es nuestra prioridad principal. Por eso, nos gustaría compartir algunos consejos para que uses BrushNbid de forma segura.\n" +
                    "\n" +
                    "\uD83D\uDD10 No compartas tu dirección de e-mail, número de teléfono o información de pago con otros miembros. \n" +
                    "\n" +
                    "\uD83D\uDC5B Paga a través de BrushNbid. Cuando usas el botón “Comprar”, te beneficias de las medidas que tenemos implantadas para proteger tus datos personales. \n" +
                    "\n" +
                    "❗ Denuncia cualquier actividad sospechosa que detectes.\n" +
                    "\n" +
                    "\uD83D\uDED1 No abras enlaces, e-mails, documentos adjuntos o mensajes de texto (SMS) sospechosos. \n" +
                    "\n" +
                    "Es posible que haya estafadores que intenten ponerse en contacto con miembros de BrushNbid para solicitar datos personales, como la dirección de e-mail o el número de teléfono, e incluso para hacerse pasar por el equipo de BrushNbid.\n" +
                    "\n" +
                    "Recuerda: BrushNbid nunca te pedirá que completes un pago fuera de la plataforma ni te solicitará información por mensaje privado o email. \n"
        ),
        Notificacion(
            icon = Icons.Default.Shield,
            title = "Confirmación de cuenta",
            description = "Para verificar el número proporcionado...",
            message = ""
        )
    )

    Scaffold(
        containerColor = colorResource(id = R.color.app_background),
        bottomBar = {
            BottomNavBar(
                selectedItem = BottomNavItem.NOTIFICATIONS,
                onHomeClick = { navController.navigate("home") },
                onAuctionsClick = { navController.navigate("auctions") },
                onCreateClick = { navController.navigate("addMenu") },
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
            HeaderImage(
                colorResource(R.color.light_blue),
                R.drawable.mi_buzon,
                "Mi",
                "Buzón"
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(modifier = Modifier.padding(start = 30.dp)) {
                notificaciones.forEachIndexed { index, item ->
                    NotificationCard(item)

                    if (index < notificaciones.lastIndex) {
                        HorizontalDivider(
                            modifier = Modifier
                                .padding(start = 44.dp, top = 4.dp),
                            color = Color.LightGray,
                            thickness = 1.dp
                        )
                    }
                }
            }


        }
    }
}

@Composable
fun NotificationCard(item: Notificacion) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = item.title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = item.description,
                fontSize = 14.sp,
                color = Color.DarkGray
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NotifScreenPreview(){
    val navController = rememberNavController()
    NotificationScreen(navController)
}