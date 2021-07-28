package io.orbeon.wrapper.controller

import io.orbeon.wrapper.interfaces.FormsService
import io.orbeon.wrapper.interfaces.UserService
import io.orbeon.wrapper.models.form.Form
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

@Controller
class HomeController {
    @Autowired
    private val formsService: FormsService? = null
    @Autowired
    private val userService: UserService? = null

    @GetMapping
    fun index(request: HttpServletRequest): String {
        val query = request.queryString
        if (query != null) {
            return "redirect:/forms?${query}"
        }
        return "redirect:/forms"
    }

    @GetMapping("/forms")
    fun home(@RequestParam project: String?, request: HttpServletRequest, model: Model): String {
        validateSession(request, project)

        val responseEntity = formsService?.fetchAll()
        val response = responseEntity?.body!!
        val formsList = Form.fromMixedJSON(response["forms"]?.get("form"))
        val groupedForms = formsService?.groupForms(formsList)
        model.addAttribute("groupedForms", groupedForms)
        return "index"
    }

    @GetMapping(value = ["/forms/{app}/{formName}/{action}", "/forms/{app}/{formName}"])
    fun appForm(
        @PathVariable app: String,
        @PathVariable formName: String,
        @PathVariable(required = false) action: String? = "new",
        @RequestParam project: String?,
        request: HttpServletRequest,
        model: Model
    ): String {
        validateSession(request, project)

        model.addAttribute("app", app)
        model.addAttribute("form", formName)
        model.addAttribute("action", action)

        request.setAttribute("model", model)
        return home(null, request, model)
    }

    private fun validateSession(request: HttpServletRequest, project: String?): String {
        val session: HttpSession = request.getSession(true)
        val projectId = userService?.validateProjectParam(project, session)!!
        userService.hasAccessToProject(projectId)
        return projectId
    }
}
