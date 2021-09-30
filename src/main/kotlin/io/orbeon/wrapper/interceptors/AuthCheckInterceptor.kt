package io.orbeon.wrapper.interceptors

import org.springframework.http.HttpHeaders
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthCheckInterceptor : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val cookies = request.cookies

        if (cookies != null) {
            for (cookie: Cookie in cookies) {
                if (cookie.name.uppercase() == HttpHeaders.AUTHORIZATION.uppercase() && cookie.value != null) {
                    if (cookie.value.isNotEmpty()) {
                        return true
                    }
                }
            }
        }
        response.sendRedirect("${request.contextPath}/require-auth-token?${request.queryString ?: ""}")
        return false
    }
}