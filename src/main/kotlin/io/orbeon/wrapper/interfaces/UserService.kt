package io.orbeon.wrapper.interfaces

import org.springframework.web.server.ResponseStatusException
import javax.servlet.http.HttpSession

interface UserService {
    @Throws(ResponseStatusException::class)
    fun hasAccessToProject(project: String): Boolean
    @Throws(ResponseStatusException::class)
    fun validateProjectParam(project: String?, session: HttpSession): String
}