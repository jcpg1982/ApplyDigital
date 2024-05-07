package pe.master.machines.applyDigital.data.models.response

import com.google.gson.annotations.SerializedName

data class ProcessingTimingsMS(
    @SerializedName("_request") val request: Request? = null,
    @SerializedName("afterFetch") val afterFetch: AfterFetch? = null,
    @SerializedName("fetch") val fetch: Fetch? = null,
    @SerializedName("total") val total: Int? = null
)