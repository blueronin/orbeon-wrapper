package io.orbeon.wrapper.controller

import io.orbeon.wrapper.config.ValuesConfig
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.server.ResponseStatusException
import java.net.URI
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/token")
class TokenController : BaseController() {
    @GetMapping("/required")
    fun requireAuthToken(@RequestParam project: String?, request: HttpServletRequest, model: Model): String {
        if (project != null && project != "null") {
            model.addAttribute("projectParam", project)
        }
        return "errors/token-required"
    }

    @GetMapping("/verify")
    fun verifyToken(
        @RequestParam token: String,
        @RequestParam project: String,
        request: HttpServletRequest,
        response: HttpServletResponse,
        model: Model
    ): String? {
        val apiUrl: String = env?.getProperty(ValuesConfig.API_URL_ENV_NAME) as String
        val verifyUserUrl = "$apiUrl/orbeon-auth/verify"

        try {
            val headers = HttpHeaders()
            headers.add(HttpHeaders.AUTHORIZATION, "OAuth $token")
            val requestEntity: HttpEntity<String> = HttpEntity<String>("parameters", headers)

            val rsp = restTemplate!!.exchange(
                URI.create(verifyUserUrl),
                HttpMethod.GET,
                requestEntity,
                object : ParameterizedTypeReference<LinkedHashMap<String, Any>>() {}
            )
            if (rsp.statusCode == HttpStatus.OK && rsp.hasBody()) {
                val isSecure: String? = env!!.getProperty("server.servlet.session.cookie.secure")
                val contextPath: String? = env!!.getProperty("server.servlet.context-path")

                val cookie = Cookie(HttpHeaders.AUTHORIZATION.uppercase(), token)
                cookie.path = contextPath
                cookie.isHttpOnly = true
                cookie.secure = isSecure.toBoolean()

                response.addCookie(cookie)
                model.addAttribute("projectParam", project)
                return "errors/redirect-home"
            }
            throw ResponseStatusException(rsp.statusCode, "Unable to verify User")
        } catch (e: HttpClientErrorException) {
            throw ResponseStatusException(e.statusCode, e.message)
        }
    }

    @GetMapping("/clear")
    fun clearSession(request: HttpServletRequest, response: HttpServletResponse): String? {
        request.getSession(false)?.invalidate()
        return "errors/token-required"
    }
}