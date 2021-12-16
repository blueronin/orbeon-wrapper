package io.orbeon.wrapper.entities.keys

import java.io.Serializable

class OrbeonFormDefinitionEntityId(
    private val created: java.sql.Timestamp? = null,
    private val formMetadata: String? = null
) : Serializable {}
