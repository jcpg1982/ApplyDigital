package pe.master.machines.applyDigital.ui.usesCase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import pe.master.machines.applyDigital.data.dataBase.entities.HitEntity
import pe.master.machines.applyDigital.data.emuns.Action
import pe.master.machines.applyDigital.data.models.response.ResponseDTO
import pe.master.machines.applyDigital.data.repository.RepositoryCloud
import pe.master.machines.applyDigital.data.repository.RepositoryLocal
import retrofit2.Response
import javax.inject.Inject

class HitUsesCase @Inject constructor(
    private var repositoryCloud: RepositoryCloud, private var repositoryLocal: RepositoryLocal
) {

    suspend operator fun invoke(query: String, page: Int): Flow<Response<ResponseDTO>> =
        repositoryCloud.searchByDate(query, page)

    suspend operator fun invoke(): Flow<PagingData<HitEntity>> =
        repositoryLocal.getAllHits()

    suspend operator fun invoke(
        listHits: List<HitEntity>, action: Action
    ): Flow<Unit> = when (action) {
        Action.INSERT -> repositoryLocal.insertListHit(listHits)
        Action.DELETE -> repositoryLocal.deleteHit(listHits[0])
        Action.UPDATE -> repositoryLocal.updateHit(listHits[0])
    }
}