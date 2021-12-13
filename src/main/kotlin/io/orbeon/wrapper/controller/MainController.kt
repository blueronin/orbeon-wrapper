package io.orbeon.wrapper.controller

import io.orbeon.wrapper.models.form.Form
import io.orbeon.wrapper.models.user.CurrentUser
import io.orbeon.wrapper.models.user.MemberRole
import io.orbeon.wrapper.models.user.SimpleTeamMember
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

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
        val projectParam = validateSession(request, project)
        model.addAttribute("projectParam", projectParam)

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
            "/forms/{app}/{formName}/{action}/{id}"
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
        val projectParam = validateSession(request, project)
        model.addAttribute("projectParam", projectParam)

        model.addAttribute("app", app)
        model.addAttribute("form", formName)
        model.addAttribute("action", action ?: "summary")
        model.addAttribute("id", id)
        model.addAttribute("query", request.queryString)

        request.setAttribute("model", model)

        return when {
            model.getAttribute("app") == "orbeon" && model.getAttribute("form") == "builder" -> {
                "builder"
            }
            else -> "runner"
        }
    }

    @GetMapping(
        value = [
            "/forms/share/{app}/{formName}/{action}",
            "/forms/share/{app}/{formName}/{action}/{id}",
        ]
    )
    fun share(
        @PathVariable app: String,
        @PathVariable formName: String,
        @PathVariable(required = false) action: String?,
        @PathVariable(required = false) id: String?,
        request: HttpServletRequest,
        model: Model
    ): String {
        val session: HttpSession = request.getSession(true)
        val user: CurrentUser? = session.getAttribute("user") as? CurrentUser
        // remove from session, will cause 401, because this is not a valid user is our records
        session.invalidate()

        model.addAttribute("app", app)
        model.addAttribute("form", formName)
        model.addAttribute("action", action ?: "summary")
        model.addAttribute("id", id)
        model.addAttribute("isShared", true)
        model.addAttribute("user", user) // not setting to session because it's relied on for authenticated users,
        model.addAttribute("query", request.queryString)

        request.setAttribute("model", model)

        return "share"
    }

    @PostMapping(
        value = [
            "/forms/share/{app}/{formName}/{action}",
            "/forms/share/{app}/{formName}/{action}/{id}",
        ]
    )
    fun shareAuth(
        @PathVariable app: String,
        @PathVariable formName: String,
        @PathVariable(required = false) action: String?,
        @PathVariable(required = false) id: String?,
        request: HttpServletRequest,
        response: HttpServletResponse
    ): String? {
        val user = CurrentUser(
            fullName = request.getParameter("full-name"),
            username = "",
            teamMembership = arrayListOf(
                SimpleTeamMember(
                    id = request.getParameter("project_id")?.toInt() ?: 0,
                    teamName = request.getParameter("organization"),
                    team = "",
                    teamSlug = "",
                    role = MemberRole(name = MemberRole.GUEST, id = 0),
                    user = "",
                    userEmail = ""
                )
            )
        )
        val session: HttpSession = request.getSession(true)
        session.setAttribute("user", user)

        response.sendRedirect("${request.requestURL}?${request.queryString}")
        response.status = HttpStatus.FOUND.value()

        return null
    }
}
