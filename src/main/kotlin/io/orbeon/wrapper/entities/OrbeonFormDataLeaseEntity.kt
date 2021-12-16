package io.orbeon.wrapper.entities

import io.orbeon.wrapper.annotations.Open
import javax.persistence.*

@Open
@Entity
@Table(name = "orbeon_form_data_lease", schema = "orbeon")
open class OrbeonFormDataLeaseEntity {
    @get:Id
    @get:Column(name = "document_id", nullable = false)
    var documentId: String? = null

    @get:Basic
    @get:Column(name = "username", nullable = false)
    var username: String? = null

    @get:Basic
    @get:Column(name = "groupname", nullable = true)
    var groupname: String? = null

    @get:Basic
    @get:Column(name = "expiration", nullable = false)
    var expiration: java.sql.Timestamp? = null


    override fun toString(): String =
        "Entity of type: ${javaClass.name} ( " +
                "documentId = $documentId " +
                "username = $username " +
                "groupname = $groupname " +
                "expiration = $expiration " +
                ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as OrbeonFormDataLeaseEntity

        if (documentId != other.documentId) return false
        if (username != other.username) return false
        if (groupname != other.groupname) return false
        if (expiration != other.expiration) return false

        return true
    }

}

