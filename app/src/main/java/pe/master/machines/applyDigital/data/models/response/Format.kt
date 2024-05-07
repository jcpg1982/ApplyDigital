package pe.master.machines.applyDigital.data.models.response

import com.google.gson.annotations.SerializedName

data class Format(
    @SerializedName("highlighting") val highlighting: Int? = null,
    @SerializedName("total") val total: Int? = null
)