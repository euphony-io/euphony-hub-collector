package co.jbear.euphony_collector.presentation.collector

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import euphony.lib.receiver.AcousticSensor
import euphony.lib.receiver.EuRxManager
import euphony.lib.transmitter.EuTxManager
import javax.inject.Inject

@HiltViewModel
class CollectorViewModel @Inject constructor() : ViewModel() {

    private val _isSpeaking = MutableLiveData(false)
    val isSpeaking get() = _isSpeaking

    private val _isListening = MutableLiveData(false)
    val isListening get() = _isListening

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

    fun speak(textToSend: String) {
        if (isSpeaking.value == false) {
            _listenResult.postValue("")
            _speakRequest.postValue(textToSend)
            _isSpeaking.postValue(true)

            txManager.euInitTransmit(textToSend)
            txManager.process(-1)
        } else {
            _isSpeaking.postValue(false)
            txManager.stop()
        }
    }

    fun listen() {
        if (isListening.value == true) {
            rxManager.finish()
            _isListening.postValue(false)
        } else {
            rxManager.listen()
            _isProcessing.postValue(true)
            _isListening.postValue(true)
            rxManager.acousticSensor = AcousticSensor {
                _listenResult.postValue(it)
                _isListening.postValue(false)
                _isSpeaking.postValue(false)
                _isProcessing.postValue(false)
                txManager.stop()
            }
        }
    }

    fun getResult(): String {
        if (speakRequest.value.equals(listenResult.value)) {
            return "SUCCESS"
        }
        return "FAIL"
    }

    companion object {
        private const val TAG = "CollectorViewModel"
    }
}