package co.jbear.euphony_collector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import co.jbear.euphony_collector.ui.theme.EuphonyCollectorTheme
import co.jbear.euphony_collector.ui.view.Home
import dagger.hilt.android.AndroidEntryPoint
import euphony.lib.transmitter.EuTxManager

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val txManager by lazy {
        EuTxManager()
    }

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