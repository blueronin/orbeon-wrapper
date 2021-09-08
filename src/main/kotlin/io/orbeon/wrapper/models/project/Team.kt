package io.orbeon.wrapper.models.project

import com.google.gson.annotations.SerializedName

data class Team(
    val id: Number? = null,
    @SerializedName("access_requested")
    val accessRequested: Boolean = false,
    val active: Boolean = false,
    val creator: String? = null,
    @SerializedName("current_role")
    val currentRole: String? = null,
    val domain: String? = null,
    @SerializedName("filename_parser")
    val filenameParser: String? = null,
    @SerializedName("image_file")
    val imageFile: String? = null,
    val integrations: ArrayList<String> = arrayListOf(),
    @SerializedName("is_locked")
    val isLocked: Boolean = false,
    val members: ArrayList<Member> = arrayListOf(),
    val name: String? = null,
    @SerializedName("report_header_image")
    val reportHeaderImage: String? = null,
    @SerializedName("resource_uri")
    val resourceUri: String? = null,
    val slug: String? = null,
    @SerializedName("thumbnail_file")
    val thumbnailFile: String? = null,
) {
}