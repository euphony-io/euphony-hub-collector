package co.jbear.euphony_collector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import co.jbear.euphony_collector.ui.theme.EuphonyCollectorTheme
import co.jbear.euphony_collector.ui.view.Home

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EuphonyCollectorTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    Home()
                }
            }
        }
    }
}