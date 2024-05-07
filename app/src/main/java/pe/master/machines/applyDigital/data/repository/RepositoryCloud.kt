package pe.master.machines.applyDigital.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pe.master.machines.applyDigital.data.models.response.ResponseDTO
import pe.master.machines.applyDigital.data.rest.IApiClient
import retrofit2.Response
import javax.inject.Inject

class RepositoryCloud @Inject constructor(private val iApiClient: IApiClient) :
    DataSource.DataSourceCloud {

    override suspend fun searchByDate(query: String, page: Int): Flow<Response<ResponseDTO>> =
        flow {
            val result = iApiClient.searchByDate(query, page)
            emit(result)
        }
}