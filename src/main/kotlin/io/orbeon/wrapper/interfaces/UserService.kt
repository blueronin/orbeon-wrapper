package io.orbeon.wrapper.interfaces

import io.orbeon.wrapper.models.user.CurrentUser
import org.springframework.web.server.ResponseStatusException

interface UserService {
    @Throws(ResponseStatusException::class)
    fun hasAccessToProject(project: String): Boolean

    @Throws(ResponseStatusException::class)
    fun validateProjectParam(project: String?): String

    @Throws(ResponseStatusException::class)
    fun currentUser(): CurrentUser?
}