package co.jbear.euphony_hub_controller.data.preference

import androidx.datastore.core.Serializer
import co.jbear.euphony_hub_controller.domain.entity.AppPreference
import com.squareup.moshi.Moshi
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
object AppPreferencesSerializer : Serializer<AppPreference> {
    override val defaultValue: AppPreference
        get() = AppPreference()
    private val adapter = Moshi.Builder()
        .build()
        .adapter(AppPreference::class.java)

    override suspend fun readFrom(input: InputStream): AppPreference {
        return try {
            adapter.fromJson(input.readBytes().decodeToString()) ?: defaultValue
        } catch (e: Exception) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: AppPreference, output: OutputStream) {
        val out = adapter.toJson(t)

        output.write(out.encodeToByteArray())
    }
}