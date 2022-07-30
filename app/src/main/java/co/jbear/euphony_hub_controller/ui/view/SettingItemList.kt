package co.jbear.euphony_hub_controller.ui.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import co.jbear.euphony_hub_controller.data.repository.PreferenceRepository
import co.jbear.euphony_hub_controller.domain.entity.BaseType
import co.jbear.euphony_hub_controller.domain.entity.CharsetType
import co.jbear.euphony_hub_controller.domain.entity.FileType
import co.jbear.euphony_hub_controller.domain.entity.ModulationType
import co.jbear.euphony_hub_controller.presentation.setting.SettingViewModel
import co.jbear.euphony_hub_controller.ui.components.DropDownPreference
import co.jbear.euphony_hub_controller.ui.components.RegularPreference
import co.jbear.euphony_hub_controller.ui.components.SettingCategory
import co.jbear.euphony_hub_controller.ui.theme.EuphonyHubTheme

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun SettingItemList(
    viewModel: SettingViewModel
) {
    val preference = viewModel.preference

    val modulationType = remember {
        listOf(
            ModulationType.FSK to ModulationType.FSK.name,
            ModulationType.ASK to ModulationType.ASK.name,
            ModulationType.CPFSK to ModulationType.CPFSK.name
        )
    }
    val baseType = remember {
        listOf(
            BaseType.BASE16 to "Base16",
            BaseType.BASE2 to "Base2"
        )
    }
    val charsetType = remember {
        listOf(
            CharsetType.ASCII to CharsetType.ASCII.name,
            CharsetType.UTF8 to CharsetType.UTF8.name
        )
    }
    val fileType = remember {
        listOf(
            FileType.PCM to "PCM",
            FileType.WAV to "WAV"
        )
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item { SettingCategory(text = "Audio Configuration") }
        item {
            RegularPreference(
                title = "Sample Rate",
                subtitle = viewModel.preference.sampleRate.toString(),
                onClick = {},
                enabled = false
            )
        }

        item { Divider() }
        item {
            DropDownPreference(
                title = "Modulation Type",
                items = modulationType,
                selectedItem = viewModel.preference.modulationType,
                onItemSelected = { modulationType ->
                    viewModel.updatePreference { it.copy(modulationType = modulationType) }
                }
            )
        }

        item { Divider() }
        item {
            DropDownPreference(
                title = "Base Type",
                items = baseType,
                selectedItem = viewModel.preference.baseType,
                onItemSelected = { baseType ->
                    viewModel.updatePreference { it.copy(baseType = baseType) }
                }
            )
        }

        item { Divider() }
        item {
            DropDownPreference(
                title = "Charset Type",
                items = charsetType,
                selectedItem = viewModel.preference.charsetType,
                onItemSelected = { charsetType ->
                    viewModel.updatePreference { it.copy(charsetType = charsetType) }
                }
            )
        }

        item { Divider() }
        item {
            DropDownPreference(
                title = "File Type",
                items = fileType,
                selectedItem = viewModel.preference.fileType,
                onItemSelected = { fileType ->
                    viewModel.updatePreference { it.copy(fileType = fileType) }
                }
            )
        }

        item { Divider() }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingItemListPreview() {
    EuphonyHubTheme {
        SettingItemList(viewModel = SettingViewModel(PreferenceRepository(LocalContext.current)))
    }
}