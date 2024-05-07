package pe.master.machines.applyDigital.data.models.response

import com.google.gson.annotations.SerializedName

data class Request(
    @SerializedName("roundTrip") val roundTrip: Int? = null
)