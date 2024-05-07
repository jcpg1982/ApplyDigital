package pe.master.machines.applyDigital.data.models.response

import com.google.gson.annotations.SerializedName

data class HighlightResult(
    @SerializedName("author") val author: Author? = null,
    @SerializedName("comment_text") val commentText: CommentText? = null,
    @SerializedName("story_title") val storyTitle: StoryTitle? = null,
    @SerializedName("story_url") val storyUrl: StoryUrl? = null
)