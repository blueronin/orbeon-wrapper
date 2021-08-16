package io.orbeon.wrapper.interfaces

import org.springframework.web.server.ResponseStatusException

interface UserService {
    @Throws(ResponseStatusException::class)
    fun hasAccessToProject(project: String): Boolean
    @Throws(ResponseStatusException::class)
    fun validateProjectParam(project: String?): String
    @Throws(ResponseStatusException::class)
    fun currentUser(): Map<String, Any>?
}