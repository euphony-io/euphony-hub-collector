package co.euphony.euphony_hub_collector.presentation.collector

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.euphony.rx.AcousticSensor
import co.euphony.rx.EuRxManager
import co.euphony.tx.EuTxManager
import co.euphony.euphony_hub_collector.data.repository.PreferenceRepository
import co.euphony.euphony_hub_collector.domain.entity.AppPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CollectorViewModel @Inject constructor(
    val preferenceRepository: PreferenceRepository
) : ViewModel() {

    var preference by mutableStateOf(AppPreference())
        private set

    private val _isProcessing = MutableLiveData(false)
    val isProcessing get() = _isProcessing

    private val _speakRequest = MutableLiveData("")
    val speakRequest get() = _speakRequest

    private val _listenResult = MutableLiveData("")
    val listenResult get() = _listenResult

    private val txManager: EuTxManager by lazy {
        EuTxManager()
    }
    private val rxManager : EuRxManager by lazy {
        EuRxManager()
    }

    init {
        preferenceRepository.data.onEach {
            preference = it
            Log.i(TAG, "init preference: $it")
        }.launchIn(viewModelScope)
    }

    fun process(textToSend: String) {
        _listenResult.postValue("")
        _speakRequest.postValue(textToSend)
        _isProcessing.postValue(true)

        rxManager.listen()
        txManager.code = textToSend
        txManager.play(-1)

        rxManager.acousticSensor = AcousticSensor {
            stop()
            _listenResult.postValue(it)
            _isProcessing.postValue(false)
        }
    }

    fun stop() {
        _isProcessing.postValue(false)
        txManager.stop()
        rxManager.finish()
    }


    fun isSuccess(): Boolean {
        if (speakRequest.value.equals(listenResult.value)) {
            return true
        }
        return false
    }

    companion object {
        private const val TAG = "CollectorViewModel"
    }
}