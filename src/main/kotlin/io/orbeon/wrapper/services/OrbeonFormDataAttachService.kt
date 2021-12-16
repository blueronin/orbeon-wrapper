package io.orbeon.wrapper.services

import io.orbeon.wrapper.entities.OrbeonFormDataAttachEntity
import io.orbeon.wrapper.repositories.OrbeonFormDataAttachRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class OrbeonFormDataAttachService {
    @Autowired
    private val repository: OrbeonFormDataAttachRepository? = null

    /**
     * Method to return the list of all the attachments in the system. Need to use pagination
     * @return list of attachments
     */
    @Override
    fun getAll(): List<OrbeonFormDataAttachEntity> {
        return repository!!.findAll()
    }
}