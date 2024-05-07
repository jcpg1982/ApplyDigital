package pe.master.machines.applyDigital.data.models.response

import com.google.gson.annotations.SerializedName

data class Fetch(
    @SerializedName("query") val query: Int? = null, @SerializedName("total") val total: Int? = null
)