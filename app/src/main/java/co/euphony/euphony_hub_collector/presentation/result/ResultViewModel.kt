package co.euphony.euphony_hub_collector.presentation.result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.euphony.euphony_hub_collector.data.repository.DataRepository
import co.euphony.euphony_hub_collector.domain.entity.CollectorLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val dataRepository: DataRepository
): ViewModel() {

    private val _collectorLogs = MutableLiveData<List<CollectorLog>>(listOf())
    val collectorLogs get() = _collectorLogs

    init {
        viewModelScope.launch {
            _collectorLogs.postValue(dataRepository.getCollectorLogs())
        }
    }
}