package com.gidm.brushnbid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.gidm.brushnbid.ui.theme.BrushNBidTheme
import com.gidm.brushnbid.views.FirstStartScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BrushNBidTheme {
                BrushNBidApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BrushNBidPreview() {
    FirstStartScreen{}
}