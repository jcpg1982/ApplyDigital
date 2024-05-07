package pe.master.machines.applyDigital.data.dataBase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import pe.master.machines.applyDigital.data.models.response.HighlightResult
import pe.master.machines.applyDigital.data.models.response.Hit
import pe.master.machines.applyDigital.helpers.Utils.objectToString
import pe.master.machines.applyDigital.helpers.Utils.timeElapsed

@Entity
data class HitEntity(
    @PrimaryKey var storyId: Int? = null,
    var highlightResult: String? = null,
    var tags: String? = null,
    var author: String? = null,
    var children: String? = null,
    var commentText: String? = null,
    var createdAt: String? = null,
    var createdAtI: Int? = null,
    var objectID: String? = null,
    var parentId: Int? = null,
    var storyTitle: String? = null,
    var storyUrl: String? = null,
    var updatedAt: String? = null
) {
    constructor(hit: Hit) : this() {
        storyId = hit.storyId
        highlightResult = hit.highlightResult?.objectToString
        tags = hit.tags?.objectToString
        author = hit.author
        children = hit.children?.objectToString
        commentText = hit.commentText
        createdAt = hit.createdAt
        createdAtI = hit.createdAtI
        objectID = hit.objectID
        parentId = hit.parentId
        storyTitle = hit.storyTitle
        storyUrl = hit.storyUrl
        updatedAt = hit.updatedAt
    }

    val timeElapsed: String? get() = createdAt?.let { timeElapsed(it).replace(".0", "") }
}