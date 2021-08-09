package io.orbeon.wrapper.controller

import io.orbeon.wrapper.interfaces.FormsService
import io.orbeon.wrapper.interfaces.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

@Controller
class BaseController {
    @Autowired
    val formsService: FormsService? = null
    @Autowired
    val userService: UserService? = null

    fun validateSession(request: HttpServletRequest, project: String?): String {
        val session: HttpSession = request.getSession(true)
        val projectId = userService!!.validateProjectParam(project, session)
        userService!!.hasAccessToProject(projectId)
        return projectId
    }

}