package io.orbeon.wrapper.entities.keys

import java.io.Serializable

class OrbeonIControlTextEntityId(
    private val dataId: Int? = null,
    private val control: String? = null,
    private val value: String? = null
) : Serializable {}
