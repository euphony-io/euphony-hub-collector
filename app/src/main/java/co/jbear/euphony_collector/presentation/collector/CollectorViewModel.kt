package co.jbear.euphony_collector.presentation.collector

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import euphony.lib.receiver.EuRxManager
import euphony.lib.transmitter.EuTxManager
import javax.inject.Inject

@HiltViewModel
class CollectorViewModel @Inject constructor() : ViewModel() {

    val listenResult get() = _listenResult
    private val _listenResult = MutableLiveData("test")

    private val txManager: EuTxManager by lazy {
        EuTxManager()
    }
    private val rxManager : EuRxManager by lazy {
        EuRxManager()
    }

    fun startSending(textToSend: String) {
        txManager.euInitTransmit(textToSend)
        txManager.process(-1)
    }

    fun stopSending() {
        txManager.stop()
    }
}