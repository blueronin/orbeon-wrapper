package io.orbeon.wrapper.entities.keys

import java.io.Serializable

class OrbeonICurrentEntityId(
    private val dataId: Int? = null,
    private val created: java.sql.Timestamp? = null,
    private val documentId: String? = null
) : Serializable {}
