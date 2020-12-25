package id.asistem.pareid.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import id.asistem.pareid.data.db.entities.Usage

@Dao
interface UsageDao {
    @Insert
    fun insert(usage: Usage)

    @Query("SELECT * FROM usage")
    fun getAllUsage() :List<Usage>

    @Query("DELETE FROM usage")
    fun deleteAllUsage()

    @Query("DELETE FROM usage WHERE id = :id")
    fun deleteById(id: Int)

    @Query("SELECT * FROM usage WHERE id = :id")
    fun getUsageByid(id: Int) : List<Usage>

    @Query("UPDATE usage SET batas = :batas WHERE app = :app")
    fun updateHour(batas: Int, app: String)
}