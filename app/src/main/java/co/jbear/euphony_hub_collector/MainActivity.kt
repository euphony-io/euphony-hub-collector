package co.jbear.euphony_hub_collector

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.core.app.ActivityCompat
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissions(permissions)

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

    private fun requestPermissions(permissions: Array<String>) {
        val denied = permissions.count { checkSelfPermission(it) == PackageManager.PERMISSION_DENIED }
        if (denied > 0) {
            ActivityCompat.requestPermissions(this, permissions, 0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0) {
            val denied = permissions.count { checkSelfPermission(it) == PackageManager.PERMISSION_DENIED }
            if (denied > 0) {
                Toast.makeText(applicationContext, "You should get permissions to use euphony-hub", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }
}