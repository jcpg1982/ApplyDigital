package pe.master.machines.applyDigital.data.models.response

import com.google.gson.annotations.SerializedName

data class AfterFetch(
    @SerializedName("format") val format: Format? = null
)