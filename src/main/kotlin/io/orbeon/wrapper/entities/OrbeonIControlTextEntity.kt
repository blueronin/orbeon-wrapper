package io.orbeon.wrapper.entities

import io.orbeon.wrapper.annotations.Open
import io.orbeon.wrapper.entities.keys.OrbeonIControlTextEntityId
import io.orbeon.wrapper.entities.listeners.PreventAnyUpdate
import javax.persistence.*

@Open
@EntityListeners(PreventAnyUpdate::class)
@IdClass(OrbeonIControlTextEntityId::class)
@Entity
@Table(name = "orbeon_i_control_text", schema = "orbeon")
class OrbeonIControlTextEntity {
    @Id
    @Basic
    @Column(name = "data_id", nullable = false, insertable = false, updatable = false)
    var dataId: Int? = null

    @Basic
    @Column(name = "pos", nullable = false)
    var pos: Int? = null

    @Id
    @Basic
    @Column(name = "control", nullable = false)
    var control: String? = null

    @Id
    @Basic
    @Column(name = "val", nullable = false)
    var value : String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_id", referencedColumnName = "id")
    var refOrbeonFormDataEntity: OrbeonFormDataEntity? = null

    override fun toString(): String =
        "Entity of type: ${javaClass.name} ( " +
                "dataId = $dataId " +
                "pos = $pos " +
                "control = $control " +
                "val = $value " +
                ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as OrbeonIControlTextEntity

        if (dataId != other.dataId) return false
        if (pos != other.pos) return false
        if (control != other.control) return false
        if (value != other.value) return false

        return true
    }

}

