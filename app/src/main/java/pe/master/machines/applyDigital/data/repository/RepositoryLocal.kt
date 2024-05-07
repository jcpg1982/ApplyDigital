package pe.master.machines.applyDigital.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pe.master.machines.applyDigital.data.dataBase.DataBase
import pe.master.machines.applyDigital.data.dataBase.entities.HitEntity
import javax.inject.Inject

class RepositoryLocal @Inject constructor(dataBase: DataBase) : DataSource.DataSourceLocal {

    private val hitDao = dataBase.hitDao
    private val config = PagingConfig(
        pageSize = 20, prefetchDistance = 1, initialLoadSize = 20, enablePlaceholders = true
    )

    override suspend fun insertListHit(entity: List<HitEntity>): Flow<Unit> = flow {
        val result = hitDao.insert(entity)
        emit(result)
    }

    override suspend fun updateHit(entity: HitEntity): Flow<Unit> = flow {
        val result = hitDao.update(entity)
        emit(result)
    }

    override suspend fun deleteHit(entity: HitEntity): Flow<Unit> = flow {
        val result = hitDao.delete(entity)
        emit(result)
    }

    override suspend fun getAllHits(): Flow<PagingData<HitEntity>> {
        val pager: Pager<Int, HitEntity> = Pager(config) {
            hitDao.getAll()
        }
        return pager.flow
    }
}