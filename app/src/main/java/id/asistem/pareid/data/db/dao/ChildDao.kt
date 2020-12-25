package id.asistem.pareid.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import id.asistem.pareid.data.db.entities.Child

@Dao
interface ChildDao {

    @Insert
    fun insert(child: Child)

    @Query("SELECT * FROM child")
    fun getAllChild() :List<Child>

    @Query("DELETE FROM child")
    fun deleteAllChild()

    @Query("DELETE FROM child WHERE id = :id")
    fun deleteById(id: Int)

    @Query("SELECT * FROM child WHERE id = :id")
    fun getChildByid(id: Int) : List<Child>

    @Query("UPDATE child SET nama = :nama, tanggal_lahir = :tanggal_lahir, username = :username, password = :password WHERE id = :id")
    fun updateChildById(nama: String, tanggal_lahir: String, username: String, password: String, id: Int)
}