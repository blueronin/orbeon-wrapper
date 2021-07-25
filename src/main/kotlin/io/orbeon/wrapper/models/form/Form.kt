package io.orbeon.wrapper.models.form

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.collections.ArrayList


data class Form(
    val operation: String? = "",
    val application: String? = "",
    val name: String? = "",
    val version: String? = null,
    val lastModifiedTime: LocalDateTime? = null,
    val wizardMode: String? = null,
    val dataMigration: String? = null,
    val updatedWithVersion: ArrayList<String?>? = arrayListOf(),
    val wizard: Boolean? = false,
    val title: ArrayList<TranslateText> = arrayListOf(),
    val description: ArrayList<TranslateText> = arrayListOf(),
    val libraryVersions: Map<String, String>? = null,
    val migration: ArrayList<MigrationText> = arrayListOf(),
    val logo: Logo? = null,
    val email: ArrayList<Email> = arrayListOf(),
) {
    val canonicalName: String
        get() = "${this.application}:${this.name}"

    companion object : Base<Form>() {
        override fun fromJSON(data: Map<String, Any>?): Form {
            @Suppress("UNCHECKED_CAST")
            return Form(
                operation = data?.get("@operations") as? String,
                application = data?.get("application-name") as? String,
                name = data?.get("form-name") as? String,
                version = data?.get("form-version") as? String,
                lastModifiedTime = LocalDateTime.parse(
                    data?.get("last-modified-time") as? String,
                    DateTimeFormatter.ISO_DATE_TIME
                ),
                wizardMode = data?.get("wizard-mode") as? String,
                dataMigration = data?.get("data-migration") as? String,
                updatedWithVersion = data?.get("updated-with-version") as? ArrayList<String?>,
                wizard = data?.get("wizard") as? Boolean,
                title = TranslateText.fromMixedJSON(data?.get("title")),
                description = TranslateText.fromMixedJSON(data?.get("description")),
                libraryVersions = data?.get("library-versions") as? LinkedHashMap<String, String>,
                migration = MigrationText.fromMixedJSON(data?.get("library-versions")),
                logo = Logo.fromJSON(data?.get("logo") as? LinkedHashMap<String, String>),
                email = Email.fromMixedJSON(data?.get("email"))
            )
        }

        override fun fromArray(data: ArrayList<LinkedHashMap<String, String>>?): ArrayList<Form> {
            if (data == null) {
                return arrayListOf()
            }
            return arrayListOf(*data.map { this.fromJSON(it) }.toTypedArray())
        }
    }
}