package co.jbear.euphony_hub_controller.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.jbear.euphony_hub_controller.data.dao.CollectorLogDao
import co.jbear.euphony_hub_controller.domain.entity.CollectorLog

@Database(
    entities = [CollectorLog::class],
    version = 1,
)
abstract class LocalDatabase: RoomDatabase() {
    abstract fun collectorLogDao(): CollectorLogDao

    companion object {
        fun getDatabase(context: Context): LocalDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                LocalDatabase::class.java,
                "collector_log.db"
            ).build()
        }
    }
}