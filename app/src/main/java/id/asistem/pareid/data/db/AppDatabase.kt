package id.asistem.pareid.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.asistem.pareid.data.db.dao.ChildDao
import id.asistem.pareid.data.db.dao.UsageDao
import id.asistem.pareid.data.db.entities.Child
import id.asistem.pareid.data.db.entities.Usage

@Database(
    entities = [Child::class, Usage::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun getChildDao(): ChildDao
    abstract fun getUsageDao(): UsageDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: builDatabase(context).also {
                instance = it
            }
        }

        private fun builDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "MyDatabase.PareId"
        ).allowMainThreadQueries().build()
    }
}