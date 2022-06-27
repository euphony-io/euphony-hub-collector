package co.jbear.euphony_hub.data.repository

import android.content.Context
import androidx.datastore.dataStore
import co.jbear.euphony_hub.data.preference.AppPreferencesSerializer
import co.jbear.euphony_hub.domain.entity.AppPreference

class PreferenceRepository(context: Context) {
    private val Context.protoDataStore by dataStore(
        fileName = "euphony-setting.json",
        serializer = AppPreferencesSerializer,
    )

    private val appDataStore = context.protoDataStore
    val data = appDataStore.data

    suspend fun update(newData: AppPreference) {
        appDataStore.updateData { newData }
    }
}