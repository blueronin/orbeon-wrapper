package io.orbeon.wrapper.config

import io.orbeon.wrapper.interceptors.AuthCheckInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(AuthCheckInterceptor())
            .excludePathPatterns(
                "/error",
                "/require-auth-token",
                "/js/**",
                "/css/**",
            )
    }
}