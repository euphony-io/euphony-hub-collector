package co.euphony.euphony_hub_collector.data.repository

import android.content.Context
import androidx.datastore.dataStore
import co.euphony.euphony_hub_collector.data.preference.AppPreferencesSerializer
import co.euphony.euphony_hub_collector.domain.entity.AppPreference
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

private val Context.protoDataStore by dataStore(
    fileName = "euphony-setting.json",
    serializer = AppPreferencesSerializer,
)

@ActivityRetainedScoped
class PreferenceRepository @Inject constructor(@ApplicationContext context: Context) {

    private val appDataStore = context.protoDataStore
    val data = appDataStore.data

    suspend fun update(newData: AppPreference) {
        appDataStore.updateData { newData }
    }
}