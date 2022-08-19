package dev.djakonystar.ruwlar

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ruwlar")
data class Ruw(
    @PrimaryKey
    val id: Int,
    val name: String,
    @ColumnInfo(name = "has_children")
    val hasChildren: Int,
    @ColumnInfo(name = "parent_id")
    val parentId: Int
)
