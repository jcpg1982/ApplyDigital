package pe.master.machines.applyDigital.data.models.response

import com.google.gson.annotations.SerializedName

data class ResponseDTO(
    @SerializedName("exhaustive") val exhaustive: Exhaustive? = null,
    @SerializedName("exhaustiveNbHits") val exhaustiveNbHits: Boolean? = null,
    @SerializedName("exhaustiveTypo") val exhaustiveTypo: Boolean? = null,
    @SerializedName("hits") val hits: List<Hit>? = null,
    @SerializedName("hitsPerPage") val hitsPerPage: Int? = null,
    @SerializedName("nbHits") val nbHits: Int? = null,
    @SerializedName("nbPages") val nbPages: Int? = null,
    @SerializedName("page") val page: Int? = null,
    @SerializedName("params") val params: String? = null,
    @SerializedName("processingTimeMS") val processingTimeMS: Int? = null,
    @SerializedName("processingTimingsMS") val processingTimingsMS: ProcessingTimingsMS? = null,
    @SerializedName("query") val query: String? = null,
    @SerializedName("serverTimeMS") val serverTimeMS: Int? = null
)