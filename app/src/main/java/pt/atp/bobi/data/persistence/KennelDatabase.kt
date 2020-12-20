package pt.atp.bobi.data.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.Executors

@Database(entities = [Dog::class], version = 1, exportSchema = false)
abstract class KennelDatabase : RoomDatabase() {

    abstract fun dogDao(): DogDao

    companion object {

        @Volatile
        private var INSTANCE: KennelDatabase? = null

        fun getDatabase(context: Context): KennelDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KennelDatabase::class.java,
                    "dog_database"
                ).build()

                INSTANCE = instance

                instance
            }
        }

        val databaseWriteExecutor = Executors.newFixedThreadPool(2)
    }
}