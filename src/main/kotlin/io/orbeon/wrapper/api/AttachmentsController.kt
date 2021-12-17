package io.orbeon.wrapper.api

import io.orbeon.wrapper.controller.BaseController
import io.orbeon.wrapper.entities.OrbeonFormDataAttachEntity
import io.orbeon.wrapper.services.OrbeonFormDataAttachService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(path = ["/api/attachments"])
class AttachmentsController: BaseController() {

    @Autowired
    private val orbeonFormDataAttachService: OrbeonFormDataAttachService? = null

    @GetMapping
    fun all(request: HttpServletRequest): List<OrbeonFormDataAttachEntity> {
        var team = request.getParameter("team-name")
        team = team?.replace(Regex("[_ -]+"), "")

        return orbeonFormDataAttachService!!.getAllActive(app = team)
    }
}
