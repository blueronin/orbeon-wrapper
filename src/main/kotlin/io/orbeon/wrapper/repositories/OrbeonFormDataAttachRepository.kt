package io.orbeon.wrapper.repositories

import io.orbeon.wrapper.entities.OrbeonFormDataAttachEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrbeonFormDataAttachRepository: JpaRepository<OrbeonFormDataAttachEntity, Int> {
    fun findAllByDraftIsAndDeletedIsAndAppIsIgnoreCase(draft: String, deleted: String, app: String?): List<OrbeonFormDataAttachEntity>
}