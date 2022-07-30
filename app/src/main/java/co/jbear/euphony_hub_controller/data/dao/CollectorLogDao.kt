package co.jbear.euphony_hub_controller.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import co.jbear.euphony_hub_controller.domain.entity.CollectorLog

@Dao
interface CollectorLogDao {
    @Insert(onConflict = REPLACE)
    fun insert(collectorLog: CollectorLog)

    @Query("SELECT * FROM collectorLog")
    fun getAll(): List<CollectorLog>

    @Query("DELETE FROM collectorLog WHERE timestamp = :timestamp")
    fun delete(timestamp: Long)
}