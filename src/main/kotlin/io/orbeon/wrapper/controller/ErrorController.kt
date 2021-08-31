package io.orbeon.wrapper.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.servlet.error.ErrorAttributes
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.util.Assert
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.context.request.WebRequest
import javax.servlet.RequestDispatcher
import javax.servlet.http.HttpServletRequest


@Controller
class CustomErrorController : ErrorController {
    // ErrorAttributes object is used to save all error attributes value.
    private var errorAttributes: ErrorAttributes? = null

    @Autowired
    fun customErrorController(errorAttributes: ErrorAttributes?) {
        Assert.notNull(errorAttributes, "ErrorAttributes must not be null")
        this.errorAttributes = errorAttributes
    }

    @RequestMapping("/error")
    fun handleError(request: HttpServletRequest, webRequest: WebRequest, model: Model): String {
        val status: Any? = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)

        if (status != null) {
            // Get error status code.
            val statusCode: Int = status.toString().toInt()
            // Get error message.
            val message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE) as String?
            // Get exception object.
            val exception = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION) as Exception?
            // Get error stack trace map object.
            val body = errorAttributes!!.getErrorAttributes(
                webRequest,
                ErrorAttributeOptions.of(ErrorAttributeOptions.Include.STACK_TRACE)
            )

            model.addAttribute("errorMessage", message)
            model.addAttribute("statusCode", statusCode)
            model.addAttribute("exception", exception)
            model.addAttribute("trace", body?.get("trace"))
        }

        return "errors/error"
    }
}