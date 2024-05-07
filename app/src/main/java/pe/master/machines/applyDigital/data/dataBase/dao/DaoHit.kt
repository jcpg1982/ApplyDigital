package pe.master.machines.applyDigital.data.dataBase.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pe.master.machines.applyDigital.data.dataBase.entities.HitEntity

@Dao
interface DaoHit {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<HitEntity>)

    @Update
    suspend fun update(entity: HitEntity)

    @Delete
    suspend fun delete(entity: HitEntity)

    @Query("SELECT * FROM HitEntity ORDER BY storyId ASC")
    fun getAll(): PagingSource<Int, HitEntity>
}