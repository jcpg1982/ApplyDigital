package pe.master.machines.applyDigital.data.models.response

import com.google.gson.annotations.SerializedName

data class CommentText(
    @SerializedName("fullyHighlighted") val fullyHighlighted: Boolean? = null,
    @SerializedName("matchLevel") val matchLevel: String? = null,
    @SerializedName("matchedWords") val matchedWords: List<String>? = null,
    @SerializedName("value") val value: String? = null
)