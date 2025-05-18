package com.gidm.brushnbid.data

import androidx.compose.ui.graphics.vector.ImageVector

data class Notificacion(
    val icon: ImageVector,
    val title: String,
    val description: String,
    val message: String
)
