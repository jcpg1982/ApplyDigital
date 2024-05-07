package pe.master.machines.applyDigital.data.models.response

import com.google.gson.annotations.SerializedName

data class StoryTitle(
    @SerializedName("matchLevel") val matchLevel: String? = null,
    @SerializedName("matchedWords") val matchedWords: List<Any>? = null,
    @SerializedName("value") val value: String? = null
)