package io.orbeon.wrapper.entities

import javax.persistence.*

@Entity
@Table(name = "orbeon_seq", schema = "orbeon", catalog = "")
open class OrbeonSeqEntity {
    @get:Id
    @get:Column(name = "val", nullable = false)
    var value : Int? = null


    override fun toString(): String =
        "Entity of type: ${javaClass.name} ( " +
                "val = $value " +
                ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as OrbeonSeqEntity

        if (value != other.value ) return false

        return true
    }

}

