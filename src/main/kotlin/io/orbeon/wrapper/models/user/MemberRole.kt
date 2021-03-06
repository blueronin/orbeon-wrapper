package io.orbeon.wrapper.models.user

import com.google.gson.annotations.SerializedName


data class MemberRole(
    val id: Int,
    val name: String,
    val permissions: ArrayList<Permission> = arrayListOf(),
    @SerializedName("resource_uri")
    val resourceUri: String? = null
) {
    companion object {
        const val ADMIN = "admin"
        const val PROJECT_MANAGER = "project_manager"
        const val USER = "user"
        const val GUEST = "guest"
    }
}