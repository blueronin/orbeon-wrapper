package io.orbeon.wrapper.services

import io.orbeon.wrapper.config.ValuesConfig
import io.orbeon.wrapper.interfaces.FormsService
import io.orbeon.wrapper.models.form.Form
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CachePut
import org.springframework.core.ParameterizedTypeReference
import org.springframework.core.env.Environment
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.URI

@Service
class FormsServiceImpl : FormsService {
    @Autowired
    private val restTemplate: RestTemplate? = null

    @Autowired
    private val env: Environment? = null

    override fun groupForms(forms: ArrayList<Form>): HashMap<String, ArrayList<Form>> {
        val groupedForms: HashMap<String, ArrayList<Form>> = HashMap()
        forms.forEach {
            val key: String = it.application as String
            if (!groupedForms.containsKey(key)) {
                groupedForms[key] = arrayListOf()
            }
            groupedForms[key]!!.add(it)
        }
        return groupedForms
    }

    @CachePut(ValuesConfig.ORBEON_CACHE_NAME)
    override fun fetchAll(): ResponseEntity<LinkedHashMap<String, LinkedHashMap<String, Any>>> {
        val apiUrl: String = env?.getProperty(ValuesConfig.API_URL_ENV_NAME) as String
        val formsUrl = "$apiUrl/orbeon/fr/service/persistence/form?all-versions=true"

        return restTemplate!!.exchange(
            URI.create(formsUrl),
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<LinkedHashMap<String, LinkedHashMap<String, Any>>>() {}
        )
    }
}