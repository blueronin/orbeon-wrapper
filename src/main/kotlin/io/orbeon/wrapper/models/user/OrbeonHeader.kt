package io.orbeon.wrapper.models.user


data class OrbeonHeader(
    val username: String,
    val groups: ArrayList<String>? = null,
    val roles: ArrayList<Map<String, String>>? = null,
    val organizations: ArrayList<ArrayList<String>>? = null,
)