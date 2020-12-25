package id.asistem.pareid.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "child")
data class Child(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")var id: Int?,
    @ColumnInfo(name = "nama")var nama: String?,
    @ColumnInfo(name = "tanggal_lahir") var tanggal_lahir: String?,
    @ColumnInfo(name = "username") var username: String?,
    @ColumnInfo(name = "password") var password: String?
)

