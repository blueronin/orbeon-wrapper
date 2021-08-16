package io.orbeon.wrapper.services

import io.orbeon.wrapper.config.ValuesConfig
import io.orbeon.wrapper.interfaces.UserService
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
class UserServiceImpl: UserService {
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

    override fun currentUser(): Map<String, Any>? {
        if (session!!.getAttribute("user") != null) {
            @Suppress("UNCHECKED_CAST")
            return session.getAttribute("user") as Map<String, Any>?
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
            session.setAttribute("user", response.body)
            @Suppress("UNCHECKED_CAST")
            return session.getAttribute("user") as Map<String, Any>
        }
        return null
    }

    @Throws(ResponseStatusException::class)
    override fun hasAccessToProject(project: String): Boolean {
        // TODO("Not yet implemented: URL used does not exist")
        val apiUrl: String = env?.getProperty(ValuesConfig.API_URL_ENV_NAME) as String
        val authUrl = "$apiUrl/orbeon/user/auth/$project/"

        try {
            val allowed = restTemplate!!.exchange(
                URI.create(authUrl),
                HttpMethod.GET,
                null,
                object : ParameterizedTypeReference<Boolean>() {}
            )

            if (allowed.body == true) {
                return true
            }
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not allowed access to project")
        } catch (e: HttpClientErrorException) {
            println(e.message)
            // TODO("Temporarily return true until endpoint is implemented. Now we run into all errors like 404,")
            return true
        }
    }
}