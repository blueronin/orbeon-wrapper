package io.orbeon.wrapper.controller

import io.orbeon.wrapper.models.form.Form
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.core.env.Environment
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.client.RestTemplate
import java.net.URI
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

@Controller
class HomeController {
    @Autowired
    private val restTemplate: RestTemplate? = null
    @Autowired
    private val env: Environment? = null

    @GetMapping("/")
    fun home(@RequestParam project: String, request: HttpServletRequest, model: Model): String {
        val session: HttpSession = request.getSession(true)
        session.setAttribute("projectId", project)

        val apiUrl: String = env?.getProperty("app.api-url") as String
        val formsUrl = "$apiUrl/orbeon/fr/service/persistence/form?all-versions=true"

        val responseEntity: ResponseEntity<LinkedHashMap<String, LinkedHashMap<String, Any>>> = restTemplate!!.exchange(
            URI.create(formsUrl),
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<LinkedHashMap<String, LinkedHashMap<String, Any>>>() {}
        )

        val response = responseEntity.body!!
        val formsList: ArrayList<Form> = Form.fromMixedJSON(response["forms"]?.get("form"))
        val groupedForms : HashMap<String, ArrayList<Form>> = HashMap()
        formsList.forEach {
            val key: String = it.application as String
            if (!groupedForms.containsKey(key)) {
                groupedForms[key] = arrayListOf()
            }
            groupedForms[key]!!.add(it)
        }
        model.addAttribute("groupedForms", groupedForms)
        return "index"
    }
}
