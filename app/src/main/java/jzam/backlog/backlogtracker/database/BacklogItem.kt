package jzam.backlog.backlogtracker.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
 * Represents an item on the backlog
 */
@Entity(tableName = "backlog_item_table")
data class BacklogItem(
    @PrimaryKey(autoGenerate = true)
    var itemId: Long = 0L,

    @ColumnInfo(name = "name")
    var name: String = "[NoNameEntered]",

    //TODO Look into enumerating
    @ColumnInfo(name = "type")
    var type: String = "game",

    @ColumnInfo(name = "sort_value")
    var sortValue: Int = 1,

    //TODO Enumerate this too
    @ColumnInfo(name = "status")
    var status: String = "unstarted",

    @ColumnInfo(name = "progress_done")
    var progressDone: Int = 0,

    @ColumnInfo(name = "progress_total")
    var progressTotal: Int = 1,

    @ColumnInfo(name = "platform")
    var platform: String = "[Platform]",

    @ColumnInfo(name = "genre")
    var genre: String = "[Genre]")
