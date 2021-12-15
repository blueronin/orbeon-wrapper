package io.orbeon.wrapper.entities

import javax.persistence.*

@Entity
@Table(name = "orbeon_organization", schema = "orbeon", catalog = "")
open class OrbeonOrganizationEntity {
    @get:Id
    @get:Basic
    @get:Column(name = "id", nullable = false)
    var id: Int? = null

    @get:Basic
    @get:Column(name = "depth", nullable = false)
    var depth: Int? = null

    @get:Basic
    @get:Column(name = "pos", nullable = false)
    var pos: Int? = null

    @get:Basic
    @get:Column(name = "name", nullable = false)
    var name: String? = null


    override fun toString(): String =
        "Entity of type: ${javaClass.name} ( " +
                "id = $id " +
                "depth = $depth " +
                "pos = $pos " +
                "name = $name " +
                ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as OrbeonOrganizationEntity

        if (id != other.id) return false
        if (depth != other.depth) return false
        if (pos != other.pos) return false
        if (name != other.name) return false

        return true
    }

}

