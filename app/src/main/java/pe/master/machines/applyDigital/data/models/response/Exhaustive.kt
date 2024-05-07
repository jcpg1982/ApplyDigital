package pe.master.machines.applyDigital.data.models.response

import com.google.gson.annotations.SerializedName

data class Exhaustive(
    @SerializedName("nbHits") val nbHits: Boolean? = null,
    @SerializedName("typo") val typo: Boolean? = null
)