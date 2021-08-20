package io.orbeon.wrapper.models.user

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import io.orbeon.wrapper.models.BaseCompanion
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class CurrentUser(
    val id: Number? = null,
    val dateJoined: LocalDateTime? = null,
    val discipline: String? = null,
    val email: String? = null,
    @SerializedName("email_hash")
    val emailHash: String? = null,
    @SerializedName("first_name")
    val firstName: String? = null,
    @SerializedName("full_name")
    val fullName: String? = null,
    @SerializedName("incomplete_profile")
    val incompleteProfile: Boolean = true,
    val initials: String? = null,
    @SerializedName("is_active")
    val isActive: Boolean = false,
    @SerializedName("is_staff")
    val isStaff: Boolean = false,
    @SerializedName("job_title")
    val jobTitle: String? = null,
    val lastActive: LocalDateTime? = null,
    val lastLogin: LocalDateTime? = null,
    @SerializedName("last_name")
    val lastName: String? = null,
    val photo: String? = null,
    val profile: String? = null,
    @SerializedName("public_email")
    val publicEmail: Boolean = false,
    @SerializedName("resource_uri")
    val resourceUri: String? = null,
    val self: Boolean? = null,
    val thumbnail: String? = null,
    val ticket: String? = null,
    val username: String? = null,
) {
    companion object : BaseCompanion<CurrentUser>() {
        private fun toLocalDate(value: String): LocalDateTime {
            val commonFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSxxxxx"
            return LocalDateTime.parse(value, DateTimeFormatter.ofPattern(commonFormat))
        }
        override fun fromJSON(data: Map<String, Any>): CurrentUser {
            val hashMap = data.toMutableMap() as HashMap<String, Any>
            hashMap["dateJoined"] = toLocalDate(data["date_joined"] as String)
            hashMap["lastActive"] = toLocalDate(data["last_active"] as String)
            hashMap["lastLogin"] = toLocalDate(data["last_login"] as String)

            val gson = Gson()
            return gson.fromJson(gson.toJson(hashMap), CurrentUser::class.java)
        }

        override fun fromArray(data: ArrayList<LinkedHashMap<String, String>>): ArrayList<CurrentUser> {
            return arrayListOf(*data.map { this.fromJSON(it) }.toTypedArray())
        }
    }
}