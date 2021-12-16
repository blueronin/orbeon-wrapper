package io.orbeon.wrapper.entities.keys

import java.io.Serializable

class OrbeonFormDataAttachEntityId(
    private val documentId: String? = null,
    private val fileName: String? = null
) : Serializable {}
