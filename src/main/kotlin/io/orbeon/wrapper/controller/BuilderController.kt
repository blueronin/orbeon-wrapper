package io.orbeon.wrapper.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.servlet.http.HttpServletRequest

@RequestMapping("/forms/builder")
@Controller
class BuilderController : BaseController() {

    @GetMapping
    fun index(@RequestParam project: String?, request: HttpServletRequest): String {
        validateSession(request, project)
        return "builder"
    }
}
