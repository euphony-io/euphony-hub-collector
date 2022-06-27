package co.jbear.euphony_hub.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CollectorLog (
    @PrimaryKey
    val timestamp: Long,

    @ColumnInfo(name = "modulation_type")
    val modulationType: ModulationType,

    @ColumnInfo(name = "base_type")
    val baseType: BaseType,

    @ColumnInfo(name = "charset_type")
    val charsetType: CharsetType,

    @ColumnInfo(name = "file_type")
    val fileType: FileType,

    @ColumnInfo(name = "file_path")
    val filePath: String,

    @ColumnInfo(name = "data")
    val data: String
) {
    companion object {
        fun getMockCollectorLog() = CollectorLog(
            timestamp = System.currentTimeMillis(),
            modulationType = ModulationType.FSK,
            baseType = BaseType.BASE16,
            charsetType = CharsetType.ASCII,
            fileType = FileType.PCM,
            filePath = "",
            data = "hello"
        )
    }
}