package pe.master.machines.applyDigital.data.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import pe.master.machines.applyDigital.data.dataBase.dao.DaoHit
import pe.master.machines.applyDigital.data.dataBase.entities.HitEntity

@Database(
    entities = [HitEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DataBase : RoomDatabase() {
    abstract val hitDao: DaoHit
}