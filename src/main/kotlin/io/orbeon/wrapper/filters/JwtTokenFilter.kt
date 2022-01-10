package io.orbeon.wrapper.filters

//import org.springframework.http.HttpHeaders
//import org.springframework.security.core.context.SecurityContextHolder
//import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
//import org.springframework.stereotype.Component
//import org.springframework.web.filter.OncePerRequestFilter
//import java.io.IOException
//import javax.servlet.FilterChain
//import javax.servlet.ServletException
//import javax.servlet.http.HttpServletRequest
//import javax.servlet.http.HttpServletResponse
//
//@Component
//class JwtTokenFilter: OncePerRequestFilter() {
//
//    @Throws(ServletException::class, IOException::class)
//    override fun doFilterInternal(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        filterChain: FilterChain
//    ) {
//        // Get authorization header and validate
//        val header = request.getHeader(HttpHeaders.AUTHORIZATION)
//        if (header.isNullOrBlank() || !header.uppercase().startsWith("JWT ")) {
//            filterChain.doFilter(request, response)
//            return
//        }
//
//        val authentication = PreAuthenticatedAuthenticationToken(
//            null,
//            header.split(" ")[1],
//            listOf()
//        )
//
//        SecurityContextHolder.getContext().authentication = authentication
//        filterChain.doFilter(request, response)
//    }
//}
