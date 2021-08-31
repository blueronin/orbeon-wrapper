package io.orbeon.wrapper.config

import org.orbeon.oxf.fr.embedding.servlet.ServletFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.ServletContextInitializer
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import java.util.*
import javax.servlet.DispatcherType
import javax.servlet.FilterRegistration
import javax.servlet.ServletContext

@Configuration
class ServletConfig : ServletContextInitializer {
    @Autowired
    private val env: Environment? = null

    override fun onStartup(servletContext: ServletContext?) {
        if (servletContext == null) return

        val orbeonFormRunnerFilter: FilterRegistration.Dynamic = servletContext.addFilter(
            "orbeon-form-runner-filter",
            ServletFilter::class.java
        )
        orbeonFormRunnerFilter.setInitParameter(
            "form-runner-url",
            env?.getProperty(ValuesConfig.ORBEON_URL_ENV_NAME)
        )
        orbeonFormRunnerFilter.setInitParameter(
            "orbeon-prefix",
            "/orbeon"
        )
        orbeonFormRunnerFilter.addMappingForUrlPatterns(
            EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD),
            false,
            "*.jsp"
        )
        orbeonFormRunnerFilter.addMappingForUrlPatterns(
            EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD),
            false,
            "/orbeon/*"
        )
    }
}
