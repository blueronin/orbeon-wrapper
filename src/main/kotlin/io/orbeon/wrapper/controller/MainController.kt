package io.orbeon.wrapper.controller

import io.orbeon.wrapper.models.form.Form
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import javax.servlet.http.HttpServletRequest

@Controller
class MainController : BaseController() {

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
        val forms = formsService?.combineFormsOnUniqueCanonicalName(formsList)
        model.addAttribute("forms", forms)
        return "index"
    }

    @GetMapping(
        value = [
            "/forms/{app}/{formName}",
            "/forms/{app}/{formName}/{action}",
            "/forms/{app}/{formName}/{action}/{id}",
            "/forms/share/{app}/{formName}/{action}",
            "/forms/share/{app}/{formName}/{action}/{id}",
        ]
    )
    fun appForm(
        @PathVariable app: String,
        @PathVariable formName: String,
        @PathVariable(required = false) action: String?,
        @PathVariable(required = false) id: String?,
        @RequestParam project: String?,
        request: HttpServletRequest,
        model: Model
    ): String {
        val isShared = request.requestURI.startsWith("${request.contextPath}/forms/share")
        if (!isShared) {
            // Only validate/authenticate if it's not a shared link. Users without accounts can access this
            validateSession(request, project)
        }
        var formAction = action
        if (formAction == null) formAction = "summary"

        model.addAttribute("app", app)
        model.addAttribute("form", formName)
        model.addAttribute("action", formAction)
        model.addAttribute("id", id)
        model.addAttribute("isShared", isShared)
        model.addAttribute("query", request.queryString)

        request.setAttribute("model", model)

        return when {
            isShared -> {
                "share"
            }
            model.getAttribute("app") == "orbeon" && model.getAttribute("form") == "builder" -> {
                "builder"
            }
            else -> "runner"
        }
    }
}
