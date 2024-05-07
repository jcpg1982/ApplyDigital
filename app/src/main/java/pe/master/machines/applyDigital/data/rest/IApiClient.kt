package pe.master.machines.applyDigital.data.rest

import pe.master.machines.applyDigital.BuildConfig
import pe.master.machines.applyDigital.data.models.response.ResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiClient {

    @GET(BuildConfig.SEARCH_BY_DATE)
    suspend fun searchByDate(@Query("query") query: String,@Query("page") page:Int): Response<ResponseDTO>
}