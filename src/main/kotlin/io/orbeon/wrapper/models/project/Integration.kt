package io.orbeon.wrapper.models.project

import com.google.gson.annotations.SerializedName

data class Integration(
    val id: Number? = null,
    val active: Boolean = false,
    val app: String? = null,
    @SerializedName("is_authorized")
    val isAuthorized: Boolean = false,
    @SerializedName("resource_uri")
    val resourceUri: String? = null,
)
