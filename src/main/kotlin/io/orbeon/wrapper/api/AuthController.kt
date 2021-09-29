package io.orbeon.wrapper.api

import io.orbeon.wrapper.config.ValuesConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.core.env.Environment
import org.springframework.http.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.server.ResponseStatusException
import java.net.URI
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
class AuthController {
    @Autowired
    private val restTemplate: RestTemplate? = null

    @Autowired
    private val env: Environment? = null

    @GetMapping("/api/token/verify")
    fun verifyToken (request: HttpServletRequest, response: HttpServletResponse): LinkedHashMap<String, Any>? {
        val authHeader: String = request.getHeader(HttpHeaders.AUTHORIZATION)
            ?: throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing auth token")

        val apiUrl: String = env?.getProperty(ValuesConfig.API_URL_ENV_NAME) as String
        val verifyUserUrl = "$apiUrl/orbeon-auth/verify"

        try {
            val headers = HttpHeaders()
            headers.contentType = MediaType.APPLICATION_JSON
            headers.add(HttpHeaders.AUTHORIZATION, authHeader)
            val requestEntity: HttpEntity<String> = HttpEntity<String>("parameters", headers)

            val rsp = restTemplate!!.exchange(
                URI.create(verifyUserUrl),
                HttpMethod.GET,
                requestEntity,
                object : ParameterizedTypeReference<LinkedHashMap<String, Any>>() {}
            )
            if (rsp.statusCode == HttpStatus.OK && rsp.hasBody()) {
                val header = authHeader.split(" ")[1]
                val cookie = Cookie(HttpHeaders.AUTHORIZATION.uppercase(), header)
                cookie.path = "/orbeon-wrapper"
                cookie.isHttpOnly = true
                cookie.secure = true

                response.addCookie(cookie)
                return rsp.body
            }
            throw ResponseStatusException(rsp.statusCode, "Unable to verify User")
        } catch (e: HttpClientErrorException) {
            throw ResponseStatusException(e.statusCode, e.message)
        }
    }
}