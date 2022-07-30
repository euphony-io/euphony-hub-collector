package co.jbear.euphony_hub_controller.presentation.setting

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.jbear.euphony_hub_controller.data.repository.PreferenceRepository
import co.jbear.euphony_hub_controller.domain.entity.AppPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    val preferenceRepository: PreferenceRepository
) : ViewModel() {

    var preference by mutableStateOf(AppPreference())
        private set

    init {
        preferenceRepository.data.onEach {
            preference = it
            Log.i(TAG, "init preference: $it")
        }.launchIn(viewModelScope)
    }

    inline fun updatePreference(crossinline body: (AppPreference) -> AppPreference) {
        viewModelScope.launch {
            val data = body(preference)

            preferenceRepository.update(data)
        }
    }

    companion object {
        private const val TAG = "SettingViewModel"
    }
}