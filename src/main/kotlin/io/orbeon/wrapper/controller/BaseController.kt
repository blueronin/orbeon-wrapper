package io.orbeon.wrapper.controller

import io.orbeon.wrapper.interfaces.FormsService
import io.orbeon.wrapper.interfaces.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Controller
import org.springframework.web.client.RestTemplate
import javax.servlet.http.HttpServletRequest

@Controller
class BaseController {
    @Autowired
    val env: Environment? = null

    @Autowired
    val restTemplate: RestTemplate? = null

    @Autowired
    val formsService: FormsService? = null

    @Autowired
    val userService: UserService? = null

    fun validateSession(request: HttpServletRequest, project: String?): String {
        val projectId = userService!!.validateProjectParam(project)
        userService!!.hasAccessToProject(projectId)
        userService!!.currentUser()
        return projectId
    }

}