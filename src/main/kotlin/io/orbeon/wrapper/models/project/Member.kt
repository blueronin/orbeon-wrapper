package io.orbeon.wrapper.models.project

import com.google.gson.annotations.SerializedName
import io.orbeon.wrapper.models.user.CurrentUser

data class Member(
    val id: Number? = null,
    val project: String? = null,
    @SerializedName("resource_uri")
    val resourceUri: String? = null,
    val role: String? = null,
    val username: String? = null,
    val user: CurrentUser? = null
) {
}