package io.orbeon.wrapper.entities

import io.orbeon.wrapper.annotations.Open
import io.orbeon.wrapper.entities.listeners.PreventAnyUpdate
import javax.persistence.*

@Open
@EntityListeners(PreventAnyUpdate::class)
@Entity
@Table(name = "orbeon_organization", schema = "orbeon")
class OrbeonOrganizationEntity {
    @Id
    @Basic
    @Column(name = "id", nullable = false)
    var id: Int? = null

    @Basic
    @Column(name = "depth", nullable = false)
    var depth: Int? = null

    @Basic
    @Column(name = "pos", nullable = false)
    var pos: Int? = null

    @Basic
    @Column(name = "name", nullable = false)
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

