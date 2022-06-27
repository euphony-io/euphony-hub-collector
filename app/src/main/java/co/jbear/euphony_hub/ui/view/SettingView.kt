package co.jbear.euphony_hub.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import co.jbear.euphony_hub.data.repository.PreferenceRepository
import co.jbear.euphony_hub.presentation.setting.SettingViewModel
import co.jbear.euphony_hub.ui.theme.EuphonyHubTheme
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination(route = "home/setting_view")
fun SettingView(
    viewModel: SettingViewModel = hiltViewModel()
) {
    SettingItemList(viewModel = viewModel)
}

@Preview(showBackground = true)
@Composable
fun SettingPreview() {
    EuphonyHubTheme {
        SettingView(
            viewModel = SettingViewModel(PreferenceRepository(LocalContext.current))
        )
    }
}
