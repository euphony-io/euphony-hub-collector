package co.jbear.euphony_collector.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import co.jbear.euphony_collector.presentation.setting.SettingViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination(route = "home/setting_view")
fun SettingView(
    viewModel: SettingViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("setting")
    }
}
