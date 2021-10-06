package io.orbeon.wrapper.services

import io.orbeon.wrapper.config.ValuesConfig
import io.orbeon.wrapper.interfaces.UserService
import io.orbeon.wrapper.models.project.Project
import io.orbeon.wrapper.models.user.CurrentUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.core.env.Environment
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.server.ResponseStatusException
import java.net.URI
import javax.servlet.http.HttpSession

@Service
class UserServiceImpl : UserService {
    @Autowired
    private val restTemplate: RestTemplate? = null

    @Autowired
    private val env: Environment? = null

    @Autowired
    private val session: HttpSession? = null

    @Throws(ResponseStatusException::class)
    override fun validateProjectParam(project: String?): String {
        var projectId = project
        if (projectId == null) {
            projectId = session!!.getAttribute("projectId") as String?
        }
        if (projectId == null) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "project is missing in the query param")
        }
        session!!.setAttribute("projectId", projectId)
        return projectId
    }

    override fun currentUser(): CurrentUser? {
        if (session!!.getAttribute("user") != null) {
            return session.getAttribute("user") as CurrentUser
        }
        val apiUrl: String = env?.getProperty(ValuesConfig.API_URL_ENV_NAME) as String
        val userUrl = "$apiUrl/users/self/"
        val response = restTemplate!!.exchange(
            URI.create(userUrl),
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<Map<String, Any>>() {}
        )
        if (response.statusCodeValue == 200 && response.hasBody()) {
            val user = CurrentUser.fromJSON(response.body!!)
            session.setAttribute("user", user)
            return user
        }
        return null
    }

    @Throws(ResponseStatusException::class)
    override fun hasAccessToProject(project: String): Boolean {
        val apiUrl: String = env?.getProperty(ValuesConfig.API_URL_ENV_NAME) as String
        val authUrl = "$apiUrl/projects/$project/"

        try {
            val response = restTemplate!!.exchange(
                URI.create(authUrl),
                HttpMethod.GET,
                null,
                object : ParameterizedTypeReference<Map<String, Any>>() {}
            )

            if (response.statusCodeValue == 200 && response.hasBody()) {
                val prt = Project.fromJSON(response.body!!)
                session!!.setAttribute("project", prt)
                return true
            }
            throw ResponseStatusException(response.statusCode, response.body.toString())
        } catch (e: HttpClientErrorException) {
            if (e.statusCode == HttpStatus.NOT_FOUND) {
                throw ResponseStatusException(e.statusCode, "Project `$project` not found")
            }
            throw ResponseStatusException(e.statusCode, e.message)
        }
    }
}