package id.asistem.pareid.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usage")
data class Usage(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int?,
    @ColumnInfo(name = "hour") var hour: Float?,
    @ColumnInfo(name = "app") var app: String?,
    @ColumnInfo(name = "image") var image: Int?,
//    @ColumnInfo(name = "background") var background: Int?,
    @ColumnInfo(name = "batas") var batas: Int?,
    @ColumnInfo(name = "usage_data") var usage_data: Float?
    )
