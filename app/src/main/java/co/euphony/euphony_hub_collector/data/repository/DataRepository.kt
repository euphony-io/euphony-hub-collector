package co.euphony.euphony_hub_collector.data.repository

import co.euphony.euphony_hub_collector.domain.entity.CollectorLog

interface DataRepository {

    suspend fun insertCollectorLog(collectorLog: CollectorLog)
    suspend fun getCollectorLogs(): List<CollectorLog>
    suspend fun deleteCollectorLog(timestamp: Long)
}