package io.orbeon.wrapper.models.project

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import io.orbeon.wrapper.models.BaseCompanion

data class Project(
    val id: Number? = null,
    val active: Boolean = false,
    @SerializedName("current_role")
    val currentRole: String? = null,
    val description: String? = null,
    @SerializedName("document_count")
    val documentCount: Number = 0,
    @SerializedName("edit_other_users_issues")
    val editOtherUsersIssues: Boolean = false,
    @SerializedName("image_file")
    val imageFile: String? = null,
    @SerializedName("is_demo")
    val isDemo: Boolean = false,
    @SerializedName("issues_high")
    val issuesHigh: Number = 0,
    @SerializedName("issues_low")
    val issuesLow: Number = 0,
    @SerializedName("issues_medium")
    val issuesMedium: Number = 0,
    val members: ArrayList<Member> = arrayListOf(),
    val name: String? = null,
    @SerializedName("project_bucket")
    val projectBucket: String? = null,
    @SerializedName("resource_uri")
    val resourceUri: String? = null,
    val team: Team? = null,
    @SerializedName("thumbnail_file")
    val thumbnailFile: String? = null,
    val uuid: String? = null,
    val watchers: ArrayList<String> = arrayListOf(),
    val watching: Boolean = false
) {
    companion object : BaseCompanion<Project>() {
        override fun fromJSON(data: Map<String, Any>): Project {
            val gson = Gson()
            return gson.fromJson(gson.toJson(data), Project::class.java)
        }

        override fun fromArray(data: ArrayList<LinkedHashMap<String, String>>): ArrayList<Project> {
            return arrayListOf(*data.map { this.fromJSON(it) }.toTypedArray())
        }
    }
}