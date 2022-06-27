package co.jbear.euphony_hub.data.repository

import co.jbear.euphony_hub.domain.entity.CollectorLog

class FakeDataRepository : DataRepository {

    val list = mutableListOf<CollectorLog>()
    private val mockData = CollectorLog.getMockCollectorLog()

    init {
        val currentTime = System.currentTimeMillis() / 1000
        for(i in 0..10) {
            list.add(mockData.copy(timestamp = currentTime + i))
        }
    }

    override suspend fun insertCollectorLog(collectorLog: CollectorLog) {
        list.add(collectorLog)
    }

    override suspend fun getCollectorLogs(): List<CollectorLog> {
        return list
    }

    override suspend fun deleteCollectorLog(timestamp: Long) {
        list.removeIf { it.timestamp == timestamp }
    }
}