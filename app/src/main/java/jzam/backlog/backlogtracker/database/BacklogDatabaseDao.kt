package jzam.backlog.backlogtracker.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BacklogDatabaseDao {
    @Insert
    suspend fun insert(item: BacklogItem)

    @Update
    suspend fun update(item: BacklogItem)

    @Query("SELECT * FROM backlog_item_table WHERE itemId = :key")
    suspend fun get(key: Long): BacklogItem

    @Query("DELETE FROM backlog_item_table")
    suspend fun clear()

    @Delete
    suspend fun deleteBacklogItem(item: BacklogItem)

    @Query("SELECT * FROM backlog_item_table ORDER BY sort_value")
    fun getBacklog(): LiveData<List<BacklogItem>>

    @Query("SELECT * FROM backlog_item_table WHERE itemId = :key")
    fun getBacklogItemWithId(key: Long): LiveData<BacklogItem>

    @Query("SELECT COUNT(*) FROM backlog_item_table")
    fun getCount(): Int
}