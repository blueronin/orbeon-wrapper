package io.orbeon.wrapper.config

//import io.orbeon.wrapper.filters.JwtTokenFilter
//import io.orbeon.wrapper.providers.TokenAuthenticationProvider
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.authentication.AuthenticationManager
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
//import org.springframework.security.config.http.SessionCreationPolicy
//import org.springframework.security.core.AuthenticationException
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
//import org.springframework.web.cors.CorsConfiguration
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource
//import org.springframework.web.filter.CorsFilter
//import javax.servlet.http.HttpServletRequest
//import javax.servlet.http.HttpServletResponse
//
//
//@Configuration
//@EnableWebSecurity
//class WebSecurityConfig(
//    private val jwtTokenFilter: JwtTokenFilter
//) : WebSecurityConfigurerAdapter() {
//
//    @Bean
//    @Throws(Exception::class)
//    override fun authenticationManagerBean(): AuthenticationManager {
//        return super.authenticationManagerBean()
//    }
//
//    @Throws(Exception::class)
//    override fun configure(auth: AuthenticationManagerBuilder) {
//        auth.authenticationProvider(TokenAuthenticationProvider())
//    }
//
//    @Throws(Exception::class)
//    override fun configure(http: HttpSecurity) {
//        var httpSecurity = http
//        // Enable CORS and disable CSRF
//        httpSecurity = httpSecurity.cors().and().csrf().disable()
//
//        // Set session management to stateless
//        httpSecurity = httpSecurity
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//
//        // Set unauthorized requests exception handler
//        httpSecurity = httpSecurity
//            .exceptionHandling()
//            .authenticationEntryPoint { _: HttpServletRequest?, response: HttpServletResponse, ex: AuthenticationException ->
//                response.sendError(
//                    HttpServletResponse.SC_UNAUTHORIZED,
//                    ex.message
//                )
//            }
//            .and()
//
//        // Set permissions on endpoints
//        httpSecurity.authorizeRequests()
//            // Our API endpoints
//            .antMatchers("/api/**").authenticated()
//            .anyRequest().permitAll()
//
//        // Add JWT token filter
//        httpSecurity.addFilterBefore(
//            jwtTokenFilter,
//            UsernamePasswordAuthenticationFilter::class.java
//        )
//    }
//
//    // Used by spring security if CORS is enabled.
//    @Bean
//    fun corsFilter(): CorsFilter? {
//        val source = UrlBasedCorsConfigurationSource()
//        val config = CorsConfiguration()
//        config.allowCredentials = true
//        config.addAllowedOrigin("*")
//        config.addAllowedHeader("*")
//        config.addAllowedMethod("*")
//        source.registerCorsConfiguration("/api/**", config)
//        return CorsFilter(source)
//    }
//}