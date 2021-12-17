package io.orbeon.wrapper.entities.keys

import java.io.Serializable

class OrbeonICurrentEntityId(
    private val dataId: Int? = null,
    private val created: java.sql.Timestamp? = null,
    private val documentId: String? = null
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
