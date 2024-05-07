package pe.master.machines.applyDigital.data.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import pe.master.machines.applyDigital.data.dataBase.entities.HitEntity
import pe.master.machines.applyDigital.data.models.response.ResponseDTO
import retrofit2.Response

class DataSource {

    interface DataSourceLocal {
        //hit
        suspend fun insertListHit(entity: List<HitEntity>): Flow<Unit>
        suspend fun updateHit(entity: HitEntity): Flow<Unit>
        suspend fun deleteHit(entity: HitEntity): Flow<Unit>
        suspend fun getAllHits(): Flow<PagingData<HitEntity>>
    }

    interface DataSourceCloud {
        suspend fun searchByDate(query: String, page: Int): Flow<Response<ResponseDTO>>
    }
}