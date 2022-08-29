package dev.djakonystar.ruwlar

import androidx.room.Dao
import androidx.room.Query

@Dao
interface RuwlarDao {

    @Query("SELECT * FROM ruwlar WHERE parent_id = :id")
    fun getRuwlarByParentId(id: Int): List<Ruw>

    @Query("SELECT parent_id FROM ruwlar WHERE id = :id")
    fun getParentId(id: Int): Int

    @Query("SELECT * FROM ruwlar WHERE name like :searchValue")
    fun searchRuw(searchValue: String): List<Ruw>
}
