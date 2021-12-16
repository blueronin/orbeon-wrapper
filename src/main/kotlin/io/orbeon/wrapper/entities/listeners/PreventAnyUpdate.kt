package io.orbeon.wrapper.entities.listeners

import javax.persistence.PrePersist
import javax.persistence.PreRemove
import javax.persistence.PreUpdate


class PreventAnyUpdate {
    @PrePersist
    fun onPrePersist(o: Any?) {
        throw IllegalStateException("JPA is trying to persist an entity of type " + (o?.javaClass ?: "null"))
    }

    @PreUpdate
    fun onPreUpdate(o: Any?) {
        throw IllegalStateException("JPA is trying to update an entity of type " + (o?.javaClass ?: "null"))
    }

    @PreRemove
    fun onPreRemove(o: Any?) {
        throw IllegalStateException("JPA is trying to remove an entity of type " + (o?.javaClass ?: "null"))
    }
}