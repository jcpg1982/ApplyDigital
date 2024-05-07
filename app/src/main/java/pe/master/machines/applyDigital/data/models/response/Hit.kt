package pe.master.machines.applyDigital.data.models.response

import com.google.gson.annotations.SerializedName

data class Hit(
    @SerializedName("_highlightResult") val highlightResult: HighlightResult? = null,
    @SerializedName("_tags") val tags: List<String>? = null,
    @SerializedName("author") val author: String? = null,
    @SerializedName("children") val children: List<Int>? = null,
    @SerializedName("comment_text") val commentText: String? = null,
    @SerializedName("created_at") val createdAt: String? = null,
    @SerializedName("created_at_i") val createdAtI: Int? = null,
    @SerializedName("objectID") val objectID: String? = null,
    @SerializedName("parent_id") val parentId: Int? = null,
    @SerializedName("story_id") val storyId: Int? = null,
    @SerializedName("story_title") val storyTitle: String? = null,
    @SerializedName("story_url") val storyUrl: String? = null,
    @SerializedName("updated_at") val updatedAt: String? = null
)