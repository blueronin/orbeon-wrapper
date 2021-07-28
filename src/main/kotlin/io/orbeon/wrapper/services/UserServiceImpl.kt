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

    @Throws(ResponseStatusException::class)
    override fun validateProjectParam(project: String?, session: HttpSession): String {
        var projectId = project
        if (projectId == null) {
            projectId = session.getAttribute("projectId") as String?
        }
        if (projectId == null) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "project is missing in the query param")
        }
        session.setAttribute("projectId", projectId)
        return projectId
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