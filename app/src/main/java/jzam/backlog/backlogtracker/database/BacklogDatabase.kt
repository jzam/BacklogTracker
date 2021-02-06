package jzam.backlog.backlogtracker.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * A database that stores backlog information.
 */
@Database(entities = [BacklogItem::class], version = 1, exportSchema = false)
abstract class BacklogDatabase : RoomDatabase() {

    abstract val backlogDatabaseDao: BacklogDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: BacklogDatabase? = null

        fun getInstance(context: Context): BacklogDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BacklogDatabase::class.java,
                        "backlog_history_database"
                    )
                        //TODO look into changing this
                        .fallbackToDestructiveMigration()
                        .build()
                    Log.i("I/BD", "Built new database")
                    INSTANCE = instance
                }

                Log.i("I/BD", "DB already exists")
                return instance
            }
        }
    }
}
