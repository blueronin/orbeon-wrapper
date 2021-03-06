package io.orbeon.wrapper.models.user

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import io.orbeon.wrapper.models.BaseCompanion
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

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
    @SerializedName("phone_number")
    val phoneNumber: String? = null,
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
    @SerializedName("team_membership")
    val teamMembership: ArrayList<SimpleTeamMember> = arrayListOf(),
) {
    @Suppress("MemberVisibilityCanBePrivate")
    fun toOrbeonHeader(teamSlug: String?): OrbeonHeader {
        val user = this

        val groups = arrayListOf<String>()
        val roles = arrayListOf<Map<String, String>>()
        val organizations = arrayListOf<ArrayList<String>>()

        user.teamMembership.forEach {
            // Create role names with their organizations
            if (it.teamSlug == teamSlug) {
                // Only add role for the current project team
                val role = HashMap<String, String>()
                // Using team/organization names as roles instead, this will enable us restrict the builder to apps only
                // available to this specific organization
                role["name"] = it.teamSlug
                // role["name"] = it.role.name
                role["organization"] = it.teamSlug
                roles.add(role)

                // Create organization hierarchy
                val orgHierarchy = arrayListOf(it.teamSlug)
                organizations.add(orgHierarchy)
            }
        }

        return OrbeonHeader(username = user.username!!, groups = groups, roles = roles, organizations = organizations)
    }

    fun toOrbeonHeaderString(teamSlug: String?): String {
        val orbeonHeader = this.toOrbeonHeader(teamSlug)
        return Gson().toJson(orbeonHeader)
    }

    fun toJson(): String {
        return Gson().toJson(this)
    }

    fun memberRole(teamSlug: String?): String? {
        val team: SimpleTeamMember? = this.teamMembership.firstOrNull { it.teamSlug == teamSlug  }
        return team?.role?.name
    }

    fun isAdmin(teamSlug: String?): Boolean {
        return this.memberRole(teamSlug) == MemberRole.ADMIN
    }

    fun isProjectManager(teamSlug: String?): Boolean {
        return this.memberRole(teamSlug) == MemberRole.PROJECT_MANAGER
    }

    companion object : BaseCompanion<CurrentUser>() {
        private fun toLocalDate(value: String?): LocalDateTime? {
            if (value == null) {
                return value
            }
            return try {
                val commonFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSxxxxx"
                LocalDateTime.parse(value, DateTimeFormatter.ofPattern(commonFormat))
            } catch (e: DateTimeParseException) {
                println(e)
                null
            }
        }

        @Suppress("UNCHECKED_CAST")
        override fun fromJSON(data: Map<String, Any>): CurrentUser {
            val hashMap = data.toMutableMap() as HashMap<String, Any?>
            hashMap["dateJoined"] = toLocalDate(data["date_joined"] as String?)
            hashMap["lastActive"] = toLocalDate(data["last_active"] as String?)
            hashMap["lastLogin"] = toLocalDate(data["last_login"] as String?)

            val gson = Gson()
            return gson.fromJson(gson.toJson(hashMap), CurrentUser::class.java)
        }

        override fun fromArray(data: ArrayList<LinkedHashMap<String, String>>): ArrayList<CurrentUser> {
            return arrayListOf(*data.map { this.fromJSON(it) }.toTypedArray())
        }
    }
}