package co.jbear.euphony_hub.data.repository

import co.jbear.euphony_hub.domain.entity.CollectorLog

interface DataRepository {

    suspend fun insertCollectorLog(collectorLog: CollectorLog)
    suspend fun getCollectorLogs(): List<CollectorLog>
    suspend fun deleteCollectorLog(timestamp: Long)
}