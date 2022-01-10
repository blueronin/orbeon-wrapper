package io.orbeon.wrapper.providers

//import org.springframework.security.authentication.AuthenticationProvider
//import org.springframework.security.core.Authentication
//import org.springframework.security.core.AuthenticationException
//import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
//import org.springframework.stereotype.Component
//
//@Component
//class TokenAuthenticationProvider: AuthenticationProvider {
//    @Throws(AuthenticationException::class)
//    override fun authenticate(authentication: Authentication): Authentication {
//        val token: Any? = authentication.credentials
//        println("===================")
//        println(token)
//
//        return authentication
//
////        if (!token.isPresent() || token.get().isEmpty()) {
////            throw BadCredentialsException("Invalid token");
////        }
////        if (!tokenService.contains(token.get())) {
////            throw BadCredentialsException("Invalid token or token expired");
////        }
////        return tokenService.retrieve(token.get());
//    }
//
//    override fun supports(authentication: Class<*>): Boolean {
//        println("33333--------------")
//        println(authentication)
//        println(authentication::class)
//        println(authentication::class.java)
//
//        return PreAuthenticatedAuthenticationToken::class.java.isAssignableFrom(authentication)
//    }
//}