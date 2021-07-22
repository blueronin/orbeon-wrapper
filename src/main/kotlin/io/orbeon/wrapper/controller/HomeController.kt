package io.orbeon.wrapper.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import javax.servlet.http.HttpServletRequest

@Controller
class HomeController {
    @GetMapping("/")
    fun home(request: HttpServletRequest, model: Model): String {
        return "index"
    }
}
