package io.orbeon.wrapper.entities.keys

import java.io.Serializable

class OrbeonFormDataAttachEntityId(
    private val documentId: String? = null,
    private val fileName: String? = null
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
