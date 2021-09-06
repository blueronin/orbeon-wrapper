package io.orbeon.wrapper.models.user

import com.google.gson.annotations.SerializedName


data class SimpleTeamMember(
    val id: Int,
    @SerializedName("resource_uri")
    val resourceUri: String? = null,
    val role: MemberRole,
    val team: String,
    @SerializedName("team_name")
    val teamName: String,
    @SerializedName("team_slug")
    val teamSlug: String,
    val user: String,
    @SerializedName("user_email")
    val userEmail: String,

)