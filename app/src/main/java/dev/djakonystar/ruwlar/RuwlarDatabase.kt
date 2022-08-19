package dev.djakonystar.ruwlar

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Ruw::class], version = 1)
abstract class RuwlarDatabase: RoomDatabase() {
    companion object {
        private var INSTANCE: RuwlarDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context): RuwlarDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    context,
                    RuwlarDatabase::class.java,
                    "ruwlar.db"
                )
                    .createFromAsset("ruwlar.db")
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = db
                return db
            }
        }
    }

    abstract fun ruwlarDao(): RuwlarDao
}
