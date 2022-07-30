package co.jbear.euphony_hub_collector.domain.entity

import com.squareup.moshi.Json

data class AppPreference(

    @field:Json(name = "sample_rate")
    val sampleRate: Int = 44100,

    @field:Json(name = "modulation_type")
    val modulationType: ModulationType = ModulationType.FSK,

    @field:Json(name = "base_type")
    val baseType: BaseType = BaseType.BASE16,

    @field:Json(name = "charset_type")
    val charsetType: CharsetType = CharsetType.ASCII,

    @field:Json(name = "file_type")
    val fileType: FileType = FileType.PCM
)