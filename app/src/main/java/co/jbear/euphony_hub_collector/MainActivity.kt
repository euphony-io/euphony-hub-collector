package co.jbear.euphony_hub_collector

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import co.jbear.euphony_hub_collector.data.repository.PreferenceRepository
import co.jbear.euphony_hub_collector.ui.theme.EuphonyHubCollectorTheme
import co.jbear.euphony_hub_collector.ui.view.Home
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferenceRepository: PreferenceRepository

    private val permissions: Array<String> = arrayOf(
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    private val requestPermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (!permissions.entries.all { it.value }) {
                Toast.makeText(applicationContext, "You should get permissions to use euphony-hub-collector", Toast.LENGTH_LONG).show()
                finish()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissionsLauncher.launch(permissions)

        setContent {
            EuphonyHubCollectorTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    Home()
                }
            }
        }
    }
}