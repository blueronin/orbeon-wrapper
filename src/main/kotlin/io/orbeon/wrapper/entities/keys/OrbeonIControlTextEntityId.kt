package io.orbeon.wrapper.entities.keys

import java.io.Serializable

class OrbeonIControlTextEntityId(
    private val dataId: Int? = null,
    private val control: String? = null,
    private val value: String? = null
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
